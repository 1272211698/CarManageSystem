package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Suggest;
import cn.zcbigdata.mybits_demo.mapper.SuggestMapper;
import cn.zcbigdata.mybits_demo.service.ISuggestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ISuggestServiceImpl implements ISuggestService {

    @Resource
    SuggestMapper suggestMapper;

    @Override
    public int addSuggest(Suggest suggest) {
        return this.suggestMapper.addSuggest(suggest);
    }

    @Override
    public int updataReply(Suggest suggest) {
        return this.suggestMapper.updataReply(suggest);
    }

    @Override
    public int deleteSuggest(int id) {
        return this.suggestMapper.deleteSuggest(id);
    }

    @Override
    public List<Suggest> seeSuggest(int page, int limit) throws Exception {
        int pageIndex = (page - 1) * limit;
        return this.suggestMapper.seeSuggest(pageIndex, limit);
    }

    @Override
    public int selectCount() {
        return this.suggestMapper.selectCount();
    }
}
