package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Suggest;

import java.util.List;

public interface ISuggestService {

    List<Suggest> selectUserSuggest(Integer userid);


    int addSuggest(Suggest suggest);

    int updataReply(Suggest suggest);

    int deleteSuggest(int id);

    List<Suggest> seeSuggest(int page, int limit) throws Exception;

    int selectCount();
}
