package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Plan;

import java.util.List;

public interface PlanMapper {

    List<Plan> seePlan(int page, int limit) throws Exception;

    int updataPlan(Plan plan);

    int addPlan(Plan plan);

    int deletePlan(int id);

    int selectCount();
}
