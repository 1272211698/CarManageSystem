package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Record;
import cn.zcbigdata.mybits_demo.mapper.RecordMapper;
import cn.zcbigdata.mybits_demo.service.IRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class IRecordServiceImpl implements IRecordService {
    @Resource
    private RecordMapper recordMapper;

    //前台用户查询保养记录接口Service层实现
    @Override
    public List<Record> selectRecordByCarid(Integer carid, Integer userid) {
        Integer useridSelected = this.recordMapper.selectUseridByCarId(carid);
        if (!useridSelected.equals(userid)) {
            return null;
        } else {
            return this.recordMapper.selectRecordByCarid(carid);
        }
    }

    //前台用户添加保养记录接口Service层实现
    @Override
    public Integer userAddRecord(Record record, Integer userid) {
        Integer useridSelected = this.recordMapper.selectUseridByCarId(record.getCarid());
        if (!useridSelected.equals(userid)) {
            return 0;
        } else {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dataStr = simpleDateFormat.format(date);
            record.setRecordTime(dataStr);
            return this.recordMapper.userAddRecord(record);
        }

    }

    //前台用户修改保养记录接口Service层实现
    @Override
    public Integer userUpdateRecord(Record record, Integer userid) {
        Record recordSelected = this.recordMapper.selectRecordById(record.getId());
        if (recordSelected == null) {
            return 0;
        }
        Integer useridSelected = this.recordMapper.selectUseridByCarId(recordSelected.getCarid());
        if (!useridSelected.equals(userid)) {
            return 0;
        } else {
            return this.recordMapper.userUpdateRecord(record);
        }
    }

    //前台用户删除保养记录接口Service层实现
    @Override
    public Integer userDeleteRecordById(Integer id, Integer userid) {
        Record recordSelected = this.recordMapper.selectRecordById(id);
        if (recordSelected == null) {
            return 0;
        }
        Integer useridSelected = this.recordMapper.selectUseridByCarId(recordSelected.getCarid());
        if (!useridSelected.equals(userid)) {
            return 0;
        } else {
            return this.recordMapper.userDeleteRecordById(id);
        }
    }
}
