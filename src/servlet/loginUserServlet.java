package servlet;

import cn.itcast.commons.CommonUtils;
import entity.User;
import service.UserService;
import tools.UserEncode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        user = UserEncode.encodeutf8(user);
        if(userService.loginUser(user.getName(), user.getPassword()))
            request.getRequestDispatcher("/frame.jsp").forward(request, response);
        else
            request.getRequestDispatcher("/loginUser.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        doGet(request,response);
    }
}
