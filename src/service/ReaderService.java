package service;

import com.sun.rowset.CachedRowSetImpl;
import dao.ReaderDao;
import entity.PageBean;
import entity.Reader;
import tools.JDBCConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ReaderService {

    Connection connection = null;
    Statement statement = null;
    //PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    private ReaderDao readerDao = new ReaderDao();

    public ReaderService()
    {
    }

    //add reader
    public boolean addReader(Reader reader)
    {
        return readerDao.addReader(reader);
    }

    //delete reader
    public boolean deleteReader(int r_id)
    {
        return readerDao.deleteReader(r_id);
    }

    //find reader
    public Reader findReader(int r_id)
    {
        return readerDao.findReader(r_id);
    }

    //modify reader
    public boolean modifyReader(Reader reader)
    {
        return readerDao.modifyReader(reader);
    }


    //query all reader
    public PageBean queryAllReader(int pageRecord, int currentPage)
    {
        PageBean pageBean = new PageBean();
        pageBean.setPageRecord(pageRecord);
        pageBean.setCurrentPage(currentPage);

        int totalRecord;
        int totalPage;

        ArrayList<Reader> readerlist = new ArrayList<Reader>();
        try {
            connection = JDBCConnection.getConn();

            //compute totalRecord,totalPage
            String sqlnumber = "SELECT COUNT(*) FROM reader";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlnumber);
            resultSet.next();
            totalRecord = resultSet.getInt(1);
            totalPage = totalRecord / pageRecord;
            if (totalRecord % pageRecord > 0)
                totalPage++;
            pageBean.setTotalRecord(totalRecord);
            pageBean.setTotalPage(totalPage);

            //query all Reader
            String sql = "SELECT * FROM reader";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.setPageSize(pageRecord);
            cachedRowSet.populate(resultSet, pageRecord * (currentPage - 1) + 1);

            while (cachedRowSet.next()) {
                readerlist.add(new Reader(cachedRowSet.getInt(1), cachedRowSet.getString(2), cachedRowSet.getString(3)));
            }
            pageBean.setReaderlist(readerlist);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConnection.closeConn(connection);
        }


        return pageBean;
    }

    //query selected reader
    public PageBean querySelectedReader(Reader reader, int pageRecord, int currentPage)
    {
        PageBean pageBean = new PageBean();
        pageBean.setPageRecord(pageRecord);
        pageBean.setCurrentPage(currentPage);

        int totalRecord;
        int totalPage;

        ArrayList<Reader> readerlist = new ArrayList<Reader>();

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


            //compute totalRecord,totalPage
            StringBuilder sqlnumber = new StringBuilder("SELECT COUNT(*) FROM reader ");

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlnumber.append(wheresql).toString());
            resultSet.next();
            totalRecord = resultSet.getInt(1);
            totalPage = totalRecord/pageRecord;
            if(totalRecord%pageRecord > 0)
                totalPage++;
            pageBean.setTotalRecord(totalRecord);
            pageBean.setTotalPage(totalPage);

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

            pageBean.setReaderlist(readerlist);

            /*print readerlist
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

        return pageBean;
    }

}
