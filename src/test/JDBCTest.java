package test;

import com.sun.rowset.CachedRowSetImpl;
import entity.Reader;

import org.junit.Test;
import tools.JDBCConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JDBCTest {

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    //demo test
    @Test
    public void queryAlltest()
    {
        int pageRecord = 3;
        int totalRecord;
        int currentPage = 1;
        int totalPage;

        ArrayList<Reader> readerlist = new ArrayList<Reader>();
        Reader reader = new Reader();

        try
        {
            connection = JDBCConnection.getConn();

            //compute totalRecord,totalPage
            String sqlnumber = "SELECT COUNT(*) FROM reader";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlnumber);
            resultSet.next();
            totalRecord = resultSet.getInt(1);
            totalPage = totalRecord/pageRecord;
            if(totalRecord%pageRecord > 0)
                totalPage++;

            //query all Reader
            String sql = "SELECT * FROM reader";
            statement= connection.createStatement();
            resultSet = statement.executeQuery(sql);

            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.setPageSize(pageRecord);
            cachedRowSet.populate(resultSet,pageRecord*(currentPage-1) + 1);

            while (cachedRowSet.next()) {
                //System.out.println(reader.getR_id() + " " + reader.getName() + " " + reader.getAddress());
                readerlist.add(new Reader(cachedRowSet.getInt(1),cachedRowSet.getString(2),cachedRowSet.getString(3)));
            }

            /*
            Iterator<Reader> iterator = readerlist.iterator();
            while(iterator.hasNext())
            {
                reader = iterator.next();
                System.out.println(reader.getR_id() + " " + reader.getName() + " " + reader.getAddress());
            }
            */

            statement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JDBCConnection.closeConn(connection);
        }
    }

    //querySelectedReader
    @Test
    public void querySelectedtest()
    {
        int pageRecord = 5;
        int totalRecord;
        int currentPage = 1;
        int totalPage;

        ArrayList<Reader> readerlist = new ArrayList<Reader>();
        Reader reader = new Reader();
        reader.setAddress("武汉");

        try
        {
            connection = JDBCConnection.getConn();

            //get wheresql
            StringBuilder wheresql = new StringBuilder(" WHERE 1 = 1");

            String name = reader.getName();
            if(name != null && !name.trim().isEmpty())
            {
                wheresql.append(" AND name LIKE '%" + name + "%'");
            }

            String address= reader.getAddress();
            if(address != null && !address.trim().isEmpty())
            {
                wheresql.append(" AND address LIKE '%" + address + "%'");
            }

            System.out.println(wheresql.toString());


            //compute totalRecord,totalPage
            StringBuilder sqlnumber = new StringBuilder("SELECT COUNT(*) FROM reader ");

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlnumber.append(wheresql).toString());
            resultSet.next();
            totalRecord = resultSet.getInt(1);
            totalPage = totalRecord/pageRecord;
            if(totalRecord%pageRecord > 0)
                totalPage++;

            //query selected Reader
            StringBuilder sql = new StringBuilder("SELECT * FROM reader");
            statement= connection.createStatement();
            resultSet = statement.executeQuery(sql.append(wheresql).toString());

            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.setPageSize(pageRecord);
            cachedRowSet.populate(resultSet,pageRecord*(currentPage-1) + 1);

            while (cachedRowSet.next()) {
                //System.out.println(reader.getR_id() + " " + reader.getName() + " " + reader.getAddress());
                readerlist.add(new Reader(cachedRowSet.getInt(1),cachedRowSet.getString(2),cachedRowSet.getString(3)));
            }

            //print readerlist
            Iterator<Reader> iterator = readerlist.iterator();
            while(iterator.hasNext())
            {
                reader = iterator.next();
                System.out.println(reader.getR_id() + " " + reader.getName() + " " + reader.getAddress());
            }


            statement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JDBCConnection.closeConn(connection);
        }
    }

    //add reader
    @Test
    public void addtest()
    {
        Reader reader = new Reader(7,"钟汉良","武汉");
        try
        {
            connection = JDBCConnection.getConn();
            String sql = "INSERT INTO reader VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, reader.getR_id());
            preparedStatement.setString(2,reader.getName());
            preparedStatement.setString( 3,reader.getAddress());
            preparedStatement.executeUpdate();

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
    }

    //delete reader
    @Test
    public void deletetest()
    {

        int r_id =  1;

        int rs;


        try
        {
            connection = JDBCConnection.getConn();
            String sql = "DELETE FROM reader WHERE r_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,r_id);
            rs = preparedStatement.executeUpdate();


            preparedStatement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            JDBCConnection.closeConn(connection);
        }
    }

    //find reader
    @Test
    public void findtest()
    {
        int r_id = 2;

        Reader reader = new Reader();
        try
        {
            connection = JDBCConnection.getConn();
            String sql = "SELECT * FROM reader WHERE r_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,r_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            reader.setR_id(r_id);
            reader.setName(resultSet.getString(2));
            reader.setAddress(resultSet.getString(3));
            System.out.println(reader.getR_id() + " " + reader.getName() + " " + reader.getAddress());

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
    }

    //modify reader
    @Test
    public void modifyreader()
    {
        Reader reader = new Reader(1,"华晨宇1","武汉1");

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

        System.out.println(bool);
    }


}
