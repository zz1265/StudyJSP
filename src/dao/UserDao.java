package dao;

import com.sun.org.apache.regexp.internal.RE;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Archibald on 12/14/2016.
 */
public class UserDao {
    BaseDao baseDao=null;
    public UserDao(){
        baseDao=new BaseDao();
        baseDao.loadDriverOracle();
    }
    public ArrayList<User> getUserByPage(int page,int line) throws SQLException {
        ArrayList<User> arrayList=new ArrayList<>();
        baseDao.createConnection("con");
        Connection connection=baseDao.getConnection("con");
        String sql="select *from(select t.*,rownum r from zzuser t where rownum<=?) m where m.r>?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,page*line);
        preparedStatement.setInt(2,(page-1)*line);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            String phone=resultSet.getString(1);
            String password=resultSet.getString(2);
            int status=resultSet.getInt(3);
            String email=resultSet.getString(4);
            arrayList.add(new User(phone,password,status,email));
        }
        baseDao.clear("con");
        return arrayList;
    }
    public int getAllPage(int line) throws SQLException {
        baseDao.createConnection("con");
        Connection connection=baseDao.getConnection("con");
        String sql="select count(zzphone) from zzuser";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        int allpage=0;
        int allline=0;
        if (resultSet.next()){
            allline=resultSet.getInt(1);
        }
        baseDao.clear("con");
        allpage=allline/line;
        if(allline%line>0){
            allpage++;
        }
        return allpage;
    }
}
