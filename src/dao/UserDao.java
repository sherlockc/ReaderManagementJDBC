package dao;

import entity.Reader;
import entity.User;
import tools.JDBCConnection;

import java.sql.*;

public class UserDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public UserDao()
    {
    }

    //add user
    public boolean addUser(User user) {
        boolean bool = false;
        int rs;
        try
        {
            connection = JDBCConnection.getConn();
            String sql = "INSERT INTO user VALUES(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2,user.getPassword());
            rs = preparedStatement.executeUpdate();

            if(rs > 0)
                bool = true;

            preparedStatement.close();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JDBCConnection.closeConn(connection);
        }

        return bool;
    }

    //delete user
    public boolean deleteUser(String name)
    {
        boolean bool = false;
        int rs;

        try
        {
            connection = JDBCConnection.getConn();
            String sql = "DELETE FROM user WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            rs = preparedStatement.executeUpdate();

            if(rs > 0)
                bool = true;

            preparedStatement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JDBCConnection.closeConn(connection);
        }

        return bool;
    }

    //find user
    public User findUser(String name)
    {
        User user = new User();
        try
        {
            connection = JDBCConnection.getConn();
            String sql = "SELECT * FROM user WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setName(resultSet.getString(1));
            user.setPassword(resultSet.getString(2));

            preparedStatement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JDBCConnection.closeConn(connection);
        }

        return user;
    }

    //modify user
    public boolean modifyUser(User user)
    {
        boolean bool = false;
        int rs;

        try
        {
            connection = JDBCConnection.getConn();
            String sql = "UPDATE user SET password = ? WHERE name= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getPassword());
            preparedStatement.setString(2,user.getName());
            rs = preparedStatement.executeUpdate();

            if(rs > 0)
                bool = true;

            preparedStatement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JDBCConnection.closeConn(connection);
        }

        return bool;
    }
}
