package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Record;

import java.util.List;

public interface IRecordService {
    List<Record> selectRecordByCarid(Integer carid, Integer userid);//查询保养记录

    Integer userAddRecord(Record record, Integer userid);//添加保养记录

    Integer userUpdateRecord(Record record, Integer userid);//修改保养记录

    Integer userDeleteRecordById(Integer id, Integer userid);//删除保养记录
}
