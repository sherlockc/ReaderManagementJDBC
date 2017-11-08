package servlet;

import cn.itcast.commons.CommonUtils;
import entity.PageBean;
import entity.Reader;
import service.ReaderService;
import tools.ReaderEncode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class querySelectedReaderServlet extends HttpServlet{

    private ReaderService readerService = new ReaderService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        int currentPage;
        String currentPage_s = request.getParameter("currentPage");
        if(currentPage_s == null || currentPage_s.trim().isEmpty())
            currentPage = 1;
        else
            currentPage = Integer.parseInt(currentPage_s);
        int pageRecord = 2;
        Reader reader = CommonUtils.toBean(request.getParameterMap(),Reader.class);
        reader = ReaderEncode.encodeutf8(reader);
        
        PageBean pageBean = readerService.querySelectedReader(reader, pageRecord, currentPage);
        request.setAttribute("reader",reader);
        request.setAttribute("pageBean_selected",pageBean);
        request.getRequestDispatcher("/querySelectedList.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        doGet(request,response);
    }
}
