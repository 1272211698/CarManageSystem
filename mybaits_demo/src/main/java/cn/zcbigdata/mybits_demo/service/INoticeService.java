package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Notice;

import java.util.List;

public interface INoticeService {
    List<Notice> seeNotice(int page, int limit) throws Exception;

    int updataNotice(Notice notice);

    int addNotice(Notice notice);

    int deleteNotice(int id);

    int selectCount();
}
