package com.quange.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quange.domain.SubCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubCategoryDao extends BaseMapper<SubCategory> {

}
