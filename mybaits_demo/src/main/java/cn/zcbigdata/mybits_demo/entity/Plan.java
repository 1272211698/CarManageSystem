package cn.zcbigdata.mybits_demo.entity;

public class Plan {
    private int id;
    private String plan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", plan='" + plan + '\'' +
                '}';
    }
}
