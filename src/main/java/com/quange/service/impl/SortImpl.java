package com.quange.service.impl;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quange.dao.ParentCategoryDao;
import com.quange.dao.SubCategoryDao;
import com.quange.domain.ParentCategory;
import com.quange.domain.SubCategory;
import com.quange.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortImpl implements SortService {

    @Autowired
    private ParentCategoryDao parentCategoryDao;

    @Autowired
    private SubCategoryDao subCategoryDao;

    // 返回所有父分类列表
    @Override
    public List<ParentCategory> getParentAll() {
        return parentCategoryDao.selectList(null);
    }

    // 返回子分类信息
    @Override
    public List<SubCategory> getSubById(Integer id) {
        QueryWrapper<SubCategory> subCategoryQueryWrapper = new QueryWrapper<>();
        subCategoryQueryWrapper.eq("parentSort", id);

        return subCategoryDao.selectList(subCategoryQueryWrapper);
    }


    // 添加子分类信息
    @Override
    public boolean addSubSort(String s) {

        // 接收传入的用户信息字符串，JSON字符串解析
        JSONObject subData = JSONUtil.parseObj(s);

        String name = subData.getStr("name");
        String parentSort = subData.getStr("parentSort");

        SubCategory subCategory = new SubCategory();
        subCategory.setName(name);
        subCategory.setParentSort(Integer.valueOf(parentSort));
        int insert = subCategoryDao.insert(subCategory);


        return insert > 0;
    }


    // 删除子分类
    @Override
    public boolean delSubSort(Integer id) {

        try {
            int i = subCategoryDao.deleteById(id);
            return i > 0;

        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateSubSort(String s) {
        // 接收传入的用户信息字符串，JSON字符串解析
        JSONObject data = JSONUtil.parseObj(s);
        String id = data.getStr("id");
        String name = data.getStr("name");

        SubCategory subCategory = new SubCategory();
        subCategory.setId(Integer.valueOf(id));
        subCategory.setName(name);

        int i = subCategoryDao.updateById(subCategory);

        return i > 0;
    }


}
