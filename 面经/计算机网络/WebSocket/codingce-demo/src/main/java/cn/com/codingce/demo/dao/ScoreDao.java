package cn.com.codingce.demo.dao;

import cn.com.codingce.demo.entity.dto.ChineseScoreDto;
import cn.com.codingce.demo.entity.dto.StudentScoreInfoDto;
import cn.com.codingce.demo.entity.dto.StudentScoreSumDto;
import cn.com.codingce.demo.entity.ScoreEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author mxz
 */
@Mapper
public interface ScoreDao extends BaseMapper<ScoreEntity> {

    /**
     * 输出各班语文课成绩汇总结果
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<ChineseScoreDto> selectMyPageSix(IPage<ChineseScoreDto> page, @Param(Constants.WRAPPER) QueryWrapper<ChineseScoreDto> queryWrapper);

    /**
     * 输出【班主任为林婉儿】的班里学生的总分清单
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<StudentScoreSumDto> selectMyPageSeven(IPage<StudentScoreSumDto> page, @Param(Constants.WRAPPER) QueryWrapper<StudentScoreSumDto> queryWrapper);

    /**
     * 输出所有学生的成绩汇总表
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<StudentScoreInfoDto> selectMyPageEight(IPage<StudentScoreInfoDto> page, @Param(Constants.WRAPPER) QueryWrapper<StudentScoreInfoDto> queryWrapper);
}
