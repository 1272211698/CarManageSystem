package cn.zcbigdata.mybits_demo.entity;

public class Suggest {
    private int id;
    private String suggest;
    private String reply;
    private int userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Suggest{" +
                "id=" + id +
                ", suggest='" + suggest + '\'' +
                ", reply='" + reply + '\'' +
                ", userid=" + userid +
                '}';
    }
}
