package service;

import dao.UserDao;
import entity.User;

public class UserService {

    private UserDao userDao = new UserDao();

    public UserService()
    {
    }

    //add user
    public boolean addUser(User user)
    {
        return userDao.addUser(user);
    }

    //delete user
    public boolean deleteUser(String name)
    {
        return userDao.deleteUser(name);
    }

    //find user
    public User findUser(String name)
    {
        return userDao.findUser(name);
    }

    //modify user
    public boolean modifyUser(User user)
    {
        return userDao.modifyUser(user);
    }

    //login
    public boolean loginUser(String name, String password)
    {
        boolean bool = false;

        User user = userDao.findUser(name);
        if(password.equals(user.getPassword()))
            bool = true;
        return bool;
    }
}
