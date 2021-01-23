package cn.zcbigdata.mybits_demo.entity;

public class Suggest {
    private int id;
    private String suggest;

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

    @Override
    public String toString() {
        return "Suggest{" +
                "id=" + id +
                ", suggest='" + suggest + '\'' +
                '}';
    }
}
