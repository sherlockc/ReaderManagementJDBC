package servlet;

import cn.itcast.commons.CommonUtils;
import entity.Reader;
import service.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class modifyReaderServlet extends HttpServlet {

    private ReaderService readerService = new ReaderService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        Reader reader = CommonUtils.toBean(request.getParameterMap(), Reader.class);
        if(readerService.modifyReader(reader))
            request.setAttribute("message","Modify successful!");
        else
            request.setAttribute("message", "Modify failed!");
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        doGet(request,response);
    }

}
