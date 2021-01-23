package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Suggest;

import java.util.List;

public interface SuggestMapper {

    int addSuggest(Suggest suggest);

    int deleteSuggest(int id);

    List<Suggest> seeSuggest(int page, int limit) throws Exception;

    int selectCount();
}
