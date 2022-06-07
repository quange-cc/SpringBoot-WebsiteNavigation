package com.quange.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quange.domain.Settings;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SettingDao extends BaseMapper<Settings> {
}
