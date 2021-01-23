package cn.zcbigdata.mybits_demo.entity;


public class Record {

  private Integer id;
  private String plan;
  private Integer carid;
  private String recordTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getPlan() {
    return plan;
  }

  public void setPlan(String plan) {
    this.plan = plan;
  }


  public Integer getCarid() {
    return carid;
  }

  public void setCarid(Integer carid) {
    this.carid = carid;
  }


  public String getRecordTime() {
    return recordTime;
  }

  public void setRecordTime(String recordTime) {
    this.recordTime = recordTime;
  }

  @Override
  public String toString() {
    return "Record{" +
            "id=" + id +
            ", plan='" + plan + '\'' +
            ", carid=" + carid +
            ", recordTime='" + recordTime + '\'' +
            '}';
  }
}
