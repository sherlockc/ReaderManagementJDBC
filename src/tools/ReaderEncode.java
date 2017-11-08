package tools;

import entity.Reader;

import java.io.UnsupportedEncodingException;

public class ReaderEncode {
    public static Reader encodeutf8(Reader reader) throws UnsupportedEncodingException{
        String name = reader.getName();
        String address = reader.getAddress();


        if (name != null && !name.trim().isEmpty()) {
            name = new String(name.getBytes("ISO-8859-1"), "utf-8");
            reader.setName(name);
        }
        if (address != null && !address.trim().isEmpty()) {
            address = new String(address.getBytes("ISO-8859-1"), "utf-8");
            reader.setAddress(address);
        }

        return reader;
    }
}
