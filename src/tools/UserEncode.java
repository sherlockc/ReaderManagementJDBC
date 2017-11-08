package tools;

import entity.User;

import java.io.UnsupportedEncodingException;

public class UserEncode {
    public static User encodeutf8(User user) throws UnsupportedEncodingException{
        String name = user.getName();
        String password = user.getPassword();


        if (name != null && !name.trim().isEmpty()) {
            name = new String(name.getBytes("ISO-8859-1"), "utf-8");
            user.setName(name);
        }

        if(password != null && !name.trim().isEmpty()){
            password = new String(password.getBytes("ISO-8859-1"),"utf-8");
            user.setPassword(password);
        }

        return user;
    }
}
