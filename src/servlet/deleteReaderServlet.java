package servlet;

import cn.itcast.commons.CommonUtils;
import entity.Reader;
import service.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class deleteReaderServlet extends HttpServlet {

    private ReaderService readerService = new ReaderService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        int r_id = Integer.parseInt(request.getParameter("r_id"));
        if(readerService.deleteReader(r_id))
            request.setAttribute("message","Delete successful!");
        else
            request.setAttribute("message", "Delete failed!");
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        doGet(request,response);
    }

}
