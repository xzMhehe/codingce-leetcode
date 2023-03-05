package cn.com.codingce.demo.service.impl;

import cn.com.codingce.demo.dao.ScoreDao;
import cn.com.codingce.demo.entity.dto.ChineseScoreDto;
import cn.com.codingce.demo.entity.dto.StudentScoreInfoDto;
import cn.com.codingce.demo.entity.dto.StudentScoreSumDto;
import cn.com.codingce.demo.entity.ScoreEntity;
import cn.com.codingce.demo.service.ScoreService;
import cn.com.codingce.demo.utils.PageUtil;
import cn.com.codingce.demo.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("scoreService")
public class ScoreServiceImpl extends ServiceImpl<ScoreDao, ScoreEntity> implements ScoreService {

    @Override
    public PageUtil queryPage(Map<String, Object> params) {
        IPage<ScoreEntity> page = this.page(
                new Query<ScoreEntity>().getPage(params),
                new QueryWrapper<ScoreEntity>()
        );

        return new PageUtil(page);
    }

    @Override
    public PageUtil queryPageSix(Map<String, Object> params) {
        QueryWrapper<ChineseScoreDto> eq = new QueryWrapper<>();
        eq.eq("course_name", "语文");
        Integer count = this.baseMapper.selectCount(new QueryWrapper<>());
        params.put("limit", count.toString());
        IPage<ChineseScoreDto> page = this.baseMapper
                .selectMyPageSix(new Query<ChineseScoreDto>()
                        .getPage(params), eq);
        return new PageUtil(page);
    }

    @Override
    public PageUtil queryPageSeven(Map<String, Object> params) {
        QueryWrapper<StudentScoreSumDto> eq = new QueryWrapper<>();
        eq.eq("tc.head_teacher", "林婉儿");
        Integer count = this.baseMapper.selectCount(new QueryWrapper<>());
        params.put("limit", count.toString());
        IPage<StudentScoreSumDto> page = this.baseMapper
                .selectMyPageSeven(new Query<StudentScoreSumDto>()
                        .getPage(params), eq);
        return new PageUtil(page);
    }

    @Override
    public PageUtil queryPageEight(Map<String, Object> params) {
        QueryWrapper<StudentScoreInfoDto> eq = new QueryWrapper<>();
        Integer count = this.baseMapper.selectCount(new QueryWrapper<>());
        params.put("limit", count.toString());
        IPage<StudentScoreInfoDto> page = this.baseMapper
                .selectMyPageEight(new Query<StudentScoreInfoDto>()
                        .getPage(params), eq);
        return new PageUtil(page);
    }

}