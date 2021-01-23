package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Notice;
import cn.zcbigdata.mybits_demo.mapper.NoticeMapper;
import cn.zcbigdata.mybits_demo.service.INoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class INoticeServiceImpl implements INoticeService {


    @Resource
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> seeNotice(int page, int limit) throws Exception {
        int pageIndex = (page - 1) * limit;
        return this.noticeMapper.seeNotice(pageIndex, limit);
    }

    @Override
    public int updataNotice(Notice notice) {
        return this.noticeMapper.updataNotice(notice);
    }

    @Override
    public int addNotice(Notice notice) {
        return this.noticeMapper.addNotice(notice);
    }

    @Override
    public int deleteNotice(int id) {
        return this.noticeMapper.deleteNotice(id);
    }

    @Override
    public int selectCount() {
        return this.noticeMapper.selectCount();
    }
}
