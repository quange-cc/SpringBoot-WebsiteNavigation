package com.quange.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quange.domain.Sites;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SitesDao extends BaseMapper<Sites> {


    @Select("select * from sites INNER join sub_category on sites.subsort = sub_category.id")
    List<Sites> getAll();
}
