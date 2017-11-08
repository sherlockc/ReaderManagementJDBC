package servlet;

import cn.itcast.commons.CommonUtils;
import entity.PageBean;
import entity.Reader;
import service.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class queryAllReaderServlet extends HttpServlet{

    private ReaderService readerService = new ReaderService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        int currentPage;
        String currentPage_s = request.getParameter("currentPage");
        if(currentPage_s == null || currentPage_s.trim().isEmpty())
            currentPage = 1;
        else
            currentPage = Integer.parseInt(currentPage_s);
        int pageRecord = 2;
        PageBean pageBean = readerService.queryAllReader(pageRecord, currentPage);
        request.setAttribute("pageBean_all",pageBean);
        request.getRequestDispatcher("/queryAllList.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        doGet(request,response);
    }
}
