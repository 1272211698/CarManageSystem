package cn.zcbigdata.mybits_demo.entity;

public class Manager {
    private Integer id;
    private String username;
    private String password;
    private String idcard_num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdcard_num() {
        return idcard_num;
    }

    public void setIdcard_num(String idcard_num) {
        this.idcard_num = idcard_num;
    }

    @Override
    public String toString() {
        return "manager{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", idcard_num=" + idcard_num +
                '}';
    }
}
