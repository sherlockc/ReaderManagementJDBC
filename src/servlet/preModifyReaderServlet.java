package servlet;

import cn.itcast.commons.CommonUtils;
import entity.Reader;
import service.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class preModifyReaderServlet extends HttpServlet{

    private ReaderService readerService = new ReaderService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        int r_id = Integer.parseInt(request.getParameter("r_id"));
        Reader reader = readerService.findReader(r_id);
        request.setAttribute("reader", reader);
        request.getRequestDispatcher("/modify.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        doGet(request,response);
    }
}
