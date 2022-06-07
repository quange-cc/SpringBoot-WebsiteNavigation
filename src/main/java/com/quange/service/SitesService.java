package com.quange.service;

import com.quange.domain.Sites;

import java.util.HashMap;
import java.util.List;

public interface SitesService {

    HashMap<String, List<Sites>> getSitesAll();


    // 返回所有数据列表
    HashMap<String, Object> getAllAdmin(Integer page, Integer limit);

    // 添加数据
    boolean addSites(String s);

    // 删除指定数据
    boolean delSites(Integer id);

    // 更新数据
    boolean updateSites(String s);

    HashMap<String, List<Sites>> getDataById(Integer id);
}
