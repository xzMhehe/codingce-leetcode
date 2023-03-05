package cn.com.codingce.demo.service.impl;

import cn.com.codingce.demo.dao.StudentDao;
import cn.com.codingce.demo.entity.dto.StudentInfoDto;
import cn.com.codingce.demo.entity.StudentEntity;
import cn.com.codingce.demo.service.StudentService;
import cn.com.codingce.demo.utils.PageUtil;
import cn.com.codingce.demo.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentEntity> implements StudentService {

    @Override
    public PageUtil queryPage(Map<String, Object> params) {
        IPage<StudentEntity> page = this.page(
                new Query<StudentEntity>().getPage(params),
                new QueryWrapper<StudentEntity>()
        );

        return new PageUtil(page);
    }

    @Override
    public PageUtil queryPageFour(Map<String, Object> params) {
        QueryWrapper<StudentInfoDto> eq = new QueryWrapper<>();
        Integer count = this.baseMapper.selectCount(new QueryWrapper<>());
        params.put("limit", count.toString());
        IPage<StudentInfoDto> page = this.baseMapper
                .selectMyPage(new Query<StudentInfoDto>()
                        .getPage(params), eq);

        return new PageUtil(page);
    }

    @Override
    public PageUtil queryPageFive(Map<String, Object> params) {
        QueryWrapper<StudentInfoDto> eq = new QueryWrapper<>();
        Integer count = this.baseMapper.selectCount(new QueryWrapper<>());
        params.put("limit", count.toString());
        eq.eq("ts.student_gender", "女");
        eq.eq("tc.head_teacher", "战豆豆");
        // TIMESTAMPDIFF(YEAR, ts.student_birth, CURDATE())
        eq.apply("TIMESTAMPDIFF(YEAR, ts.student_birth, CURDATE()) >= 17");
        IPage<StudentInfoDto> page = this.baseMapper
                .selectMyPage(new Query<StudentInfoDto>()
                        .getPage(params), eq);
        return new PageUtil(page);
    }

}