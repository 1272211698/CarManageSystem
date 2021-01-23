package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Suggest;

import java.util.List;

public interface SuggestService {

    int addSuggest(Suggest suggest);

    int deleteSuggest(int id);

    List<Suggest> seeSuggest(int page, int limit) throws Exception;

    int selectCount();
}
