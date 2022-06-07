package com.quange.service;

import com.quange.domain.ParentCategory;

import java.util.List;

public interface ParentService {

    // 返回父分类与子分类信息
    List<ParentCategory> getCategory();

    // 添加分类信息
    boolean addParent(String name);

    // 修改
    boolean delParent(Integer id);


    boolean updateParent(String s);
}
