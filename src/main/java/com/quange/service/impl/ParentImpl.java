package com.quange.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quange.dao.ParentCategoryDao;
import com.quange.dao.SubCategoryDao;
import com.quange.domain.ParentCategory;
import com.quange.domain.SubCategory;
import com.quange.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ParentImpl implements ParentService {

    @Autowired
    private ParentCategoryDao parentCategoryDao;

    @Autowired
    private SubCategoryDao subCategoryDao;

    @Override
    public List<ParentCategory> getCategory() {

        // 查询父分类信息
        List<ParentCategory> parentCategories = parentCategoryDao.selectList(null);
        // 遍历父分类类信息
        for (ParentCategory parentCategory : parentCategories) {
            Integer id = parentCategory.getId();
            parentCategory.setTag(1);

            // 创建一个新的list
            List<ParentCategory> newParent = new ArrayList<>();

            // 查询所绑定的子分类信息
            QueryWrapper<SubCategory> subCategoryQueryWrapper = new QueryWrapper<>();
            subCategoryQueryWrapper.eq("parentSort", id);
            List<SubCategory> subCategoryList = subCategoryDao.selectList(subCategoryQueryWrapper);
            for (SubCategory subCategory : subCategoryList) {

                ParentCategory parent = new ParentCategory();

                parent.setId(subCategory.getId());
                parent.setParentName(subCategory.getName());
                parent.setTag(2);
                newParent.add(parent);
            }

            parentCategory.setChildren(newParent);
        }

        return parentCategories;
    }

    // 添加分类信息
    @Override
    public boolean addParent(String name) {
        ParentCategory parentCategory = new ParentCategory();
        parentCategory.setParentName(name);
        int insert = parentCategoryDao.insert(parentCategory);

        return insert > 0;
    }

    // 删除父分类
    @Override
    public boolean delParent(Integer id) {

        try {
            int i = parentCategoryDao.deleteById(id);
            return i > 0;

        } catch (Exception e) {
            return false;
        }

    }


    // 修改主分类信息
    @Override
    public boolean updateParent(String s) {
        // 接收传入的用户信息字符串，JSON字符串解析
        JSONObject data = JSONUtil.parseObj(s);
        String id = data.getStr("id");
        String name = data.getStr("name");

        ParentCategory parentCategory = new ParentCategory();
        parentCategory.setId(Integer.valueOf(id));
        parentCategory.setParentName(name);

        int i = parentCategoryDao.updateById(parentCategory);

        return i > 0;
    }
}
