package cn.com.codingce.demo.service.impl;

import cn.com.codingce.demo.dao.ClassDao;
import cn.com.codingce.demo.entity.ClassEntity;
import cn.com.codingce.demo.service.ClassService;
import cn.com.codingce.demo.utils.PageUtil;
import cn.com.codingce.demo.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("classService")
public class ClassServiceImpl extends ServiceImpl<ClassDao, ClassEntity> implements ClassService {

    @Override
    public PageUtil queryPage(Map<String, Object> params) {
        IPage<ClassEntity> page = this.page(
                new Query<ClassEntity>().getPage(params),
                new QueryWrapper<ClassEntity>()
        );

        return new PageUtil(page);
    }

}