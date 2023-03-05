package cn.com.codingce.demo.dao;

import cn.com.codingce.demo.entity.dto.StudentInfoDto;
import cn.com.codingce.demo.entity.StudentEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author mxz
 */
@Mapper
public interface StudentDao extends BaseMapper<StudentEntity> {

    /**
     * 输出所有学生清单
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<StudentInfoDto> selectMyPage(IPage<StudentInfoDto> page, @Param(Constants.WRAPPER) Wrapper<StudentInfoDto> queryWrapper);

}
