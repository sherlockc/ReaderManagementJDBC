package servlet;

import entity.Reader;
import service.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import tools.ReaderEncode;

import java.io.IOException;

public class addReaderServlet extends HttpServlet{

    private ReaderService readerService = new ReaderService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        Reader reader = CommonUtils.toBean(request.getParameterMap(),Reader.class);
        reader = ReaderEncode.encodeutf8(reader);
        if(readerService.addReader(reader))
            request.setAttribute("message","Added successful!");
        else
            request.setAttribute("message","Added failed!");
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        doGet(request,response);
    }
}
