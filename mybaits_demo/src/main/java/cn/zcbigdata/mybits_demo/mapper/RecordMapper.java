package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Record;

import java.util.List;

public interface RecordMapper {
    List<Record> selectRecordByCarid(Integer carid);//根据车辆id获取保养记录
    Integer selectUseridByCarId(Integer carid);//根据车辆id获取用户id，用于鉴权
    Integer userAddRecord(Record record);//新增保养记录
    Integer userUpdateRecord(Record record);//修改保养记录
    Record selectRecordById(Integer id);//根据保养记录id获取保养记录
    Integer userDeleteRecordById(Integer id);//根据保养记录id删除保养记录
}
