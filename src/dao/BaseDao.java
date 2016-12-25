package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Archibald on 11/23/2016.
 */
public class BaseDao {
    private HashMap<String,MyConnection> connectionHashMap;
    public void loadDriverOracle(){
        try {
            Class.forName(DaoOrder.oracleDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public BaseDao(){
        connectionHashMap=new HashMap<>();
    }
    public void createConnection(String name) throws SQLException {
        if(connectionHashMap.containsKey(name)){
            return;
        }else {
            MyConnection myConnection=new MyConnection();
            myConnection.connection= DriverManager.getConnection(DaoOrder.oracleUrl,DaoOrder.oracleUser,DaoOrder.oraclePassword);
            connectionHashMap.put(name,myConnection);
        }
    }
    public Connection getConnection(String name) throws SQLException {
        if(connectionHashMap.containsKey(name)){
            return connectionHashMap.get(name).connection;
        }else {
            return null;
        }
    }
    public void close(String name) throws SQLException {
        if(connectionHashMap.containsKey(name)){
            Connection connection=connectionHashMap.get(name).connection;
            if(connection!=null){
                connection.close();
                connection=null;
            }
            connectionHashMap.get(name).isUsing=false;
        }
    }
    public void close() throws SQLException {
        Iterator<String> iterator=connectionHashMap.keySet().iterator();
        while (iterator.hasNext()){
            String name=iterator.next();
            Connection connection=connectionHashMap.get(name).connection;
            if(connection!=null){
                connection.close();
                connection=null;
            }
           setUsing(name,false);
        }
    }
    public void clear() throws SQLException {
        close();
        connectionHashMap.clear();
    }
    public void clear(String name) throws SQLException {
        close(name);
        connectionHashMap.remove(name);
    }
    public void deposit(String name,int interval){
        if(!connectionHashMap.containsKey(name)){
            return;
        }
        if(!connectionHashMap.get(name).isUsing){
            return;
        }
        if(connectionHashMap.get(name).isDeposit){
            return;
        }
        connectionHashMap.get(name).isDeposit=true;
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (true){
                        if(!connectionHashMap.get(name).isUsing){
                            close(name);
                            break;
                        }
                        setUsing(name,false);
                        Thread.sleep(interval*1000);
                    }
                }catch (Exception e){

                }

            }
        });
        thread.start();
    }
    public void setUsing(String name,boolean isUsing){
        if(!connectionHashMap.containsKey(name)){
            return;
        }
        connectionHashMap.get(name).isUsing=isUsing;
    }
    public boolean test(String name){
        if(connectionHashMap.get(name).connection==null){
            return false;
        }
        return true;
    }
    class MyConnection{
        public boolean isUsing=false;
        public boolean isDeposit=false;
        public Connection connection=null;
    }
}
