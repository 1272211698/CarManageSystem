package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Plan;
import cn.zcbigdata.mybits_demo.mapper.PlanMapper;
import cn.zcbigdata.mybits_demo.service.IPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IPlanServiceImpl implements IPlanService {

    @Resource
    PlanMapper planMapper;

    @Override
    public List<Plan> seePlan(int page, int limit) throws Exception {
        int pageIndex = (page - 1) * limit;
        int pageSize = limit;
        return this.planMapper.seePlan(pageIndex,pageSize);
    }

    @Override
    public int updataPlan(Plan plan) {
        return this.planMapper.updataPlan(plan);
    }

    @Override
    public int addPlan(Plan plan) {
        return this.planMapper.addPlan(plan);
    }

    @Override
    public int deletePlan(int id) {
        return this.planMapper.deletePlan(id);
    }

    @Override
    public int selectCount() {
        return this.planMapper.selectCount();
    }
}
