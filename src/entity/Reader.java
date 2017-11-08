package entity;

public class Reader {

    private int r_id;
    private String name;
    private String address;

    public Reader()
    {
    }

    public Reader(int r_id, String name, String address)
    {
        this.r_id = r_id;
        this.name = name;
        this.address = address;
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

