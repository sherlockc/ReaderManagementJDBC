package dao;

import entity.Reader;
import tools.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public ReaderDao()
    {
    }

    //add reader
    public boolean addReader(Reader reader) {
        boolean bool = false;
        int rs;
        try
        {
            connection = JDBCConnection.getConn();
            String sql = "INSERT INTO reader VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, reader.getR_id());
            preparedStatement.setString(2,reader.getName());
            preparedStatement.setString(3,reader.getAddress());
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

    //delete reader
    public boolean deleteReader(int r_id)
    {
        boolean bool = false;
        int rs;

        try
        {
            connection = JDBCConnection.getConn();
            String sql = "DELETE FROM reader WHERE r_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,r_id);
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

    //find reader
    public Reader findReader(int r_id)
    {
        Reader reader = new Reader();
        try
        {
            connection = JDBCConnection.getConn();
            String sql = "SELECT * FROM reader WHERE r_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,r_id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            reader.setR_id(r_id);
            reader.setName(resultSet.getString(2));
            reader.setAddress(resultSet.getString(3));

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

        return reader;
    }

    //modify reader
    public boolean modifyReader(Reader reader)
    {
        boolean bool = false;
        int rs;

        try
        {
            connection = JDBCConnection.getConn();
            String sql = "UPDATE reader SET name = ?,address = ? WHERE r_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,reader.getName());
            preparedStatement.setString(2,reader.getAddress());
            preparedStatement.setInt(3,reader.getR_id());
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
