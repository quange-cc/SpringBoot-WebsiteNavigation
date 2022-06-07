package com.quange.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quange.dao.ParentCategoryDao;
import com.quange.dao.SitesDao;
import com.quange.dao.SubCategoryDao;
import com.quange.domain.ParentCategory;
import com.quange.domain.Sites;
import com.quange.domain.SubCategory;
import com.quange.service.SitesService;
import com.quange.utils.jwt.DownIco;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Service
public class SitesImpl implements SitesService {

    @Autowired
    private SitesDao sitesDao;

    @Autowired
    private ParentCategoryDao parentCategoryDao;

    @Autowired
    private SubCategoryDao subCategoryDao;

    @Override
    public HashMap<String, List<Sites>> getSitesAll() {

        val data = new HashMap<String, List<Sites>>();

        val subCategoryList = subCategoryDao.selectList(null);
        val all = sitesDao.getAll();

        for (SubCategory subCategory : subCategoryList) {
            val name = subCategory.getName();

            val sites = new ArrayList<Sites>();

            for (Sites site : all) {
                if (Objects.equals(name, site.getName())) {
                    sites.add(site);
                }
            }

            data.put(name, sites);
        }

        return data;
    }


    /**
     * 分页查询所有网址信息
     */
    @Override
    public HashMap<String, Object> getAllAdmin(Integer page, Integer limit) {

        // 分页查询
        Page<Sites> pg = new Page<>(page, limit);
        Page<Sites> sitesIPage = sitesDao.selectPage(pg, null);
        List<Sites> sites = sitesIPage.getRecords();

        for (Sites site : sites) {
            // 获取子类的id
            Integer subSort = site.getSubSort();
            QueryWrapper<SubCategory> subCategoryQueryWrapper = new QueryWrapper<>();
            subCategoryQueryWrapper.eq("id", subSort);
            // 查询所绑定子类信息
            SubCategory subCategory = subCategoryDao.selectOne(subCategoryQueryWrapper);
            String name = subCategory.getName();
            Integer parentSort = subCategory.getParentSort();

            // 查询子类绑定父类的信息
            QueryWrapper<ParentCategory> parentCategoryQueryWrapper = new QueryWrapper<>();
            parentCategoryQueryWrapper.eq("id", parentSort);
            ParentCategory parentCategory = parentCategoryDao.selectOne(parentCategoryQueryWrapper);
            String parentName = parentCategory.getParentName();

            site.setParentId(parentSort);
            site.setParent(parentName);
            site.setSort(name);
        }

        val total = pg.getTotal();

        val map = new HashMap<String, Object>();
        map.put("data", sites);
        map.put("count", total);
        return map;
    }


    // 添加网址信息
    @Override
    public boolean addSites(String s) {

        // 接收传入的用户信息字符串，JSON字符串解析
        JSONObject sitesData = JSONUtil.parseObj(s);
        String sort = sitesData.getStr("sort");
        String title = sitesData.getStr("title");
        String url = sitesData.getStr("url");
        String description = sitesData.getStr("description");

        // 设置属性值
        Sites sites = new Sites();
        sites.setSubSort(Integer.valueOf(sort));
        sites.setTitle(title);
        sites.setUrl(url);

        try {
            String ico = DownIco.downIco(url);
            System.out.printf(ico);
            sites.setIcon(ico);
        } catch (IOException e) {
            System.out.println("下载ico 异常");
        }

        sites.setDescription(description);

        // 执行插入sql
        int insert = sitesDao.insert(sites);

        return insert > 0;
    }


    // 传入id，删除指定数据
    @Override
    public boolean delSites(Integer id) {
        QueryWrapper<Sites> sitesQueryWrapper = new QueryWrapper<>();
        sitesQueryWrapper.eq("id", id);
        int delete = sitesDao.delete(sitesQueryWrapper);
        return delete > 0;
    }

    // 修改指定数据
    @Override
    public boolean updateSites(String s) {
        // 接收传入的用户信息字符串，JSON字符串解析
        JSONObject sitesData = JSONUtil.parseObj(s);

        String id = sitesData.getStr("id");
        String subSort = sitesData.getStr("subSort");
        String title = sitesData.getStr("title");
        String url = sitesData.getStr("url");
        String description = sitesData.getStr("description");

        Sites sites = new Sites();
        sites.setId(Integer.valueOf(id));
        sites.setSubSort(Integer.valueOf(subSort));
        sites.setTitle(title);
        sites.setUrl(url);
        sites.setDescription(description);

        int i = sitesDao.updateById(sites);

        return i > 0;
    }


    // 根据子分类id，查询数据
    @Override
    public HashMap<String, List<Sites>> getDataById(Integer id) {
        val data = new HashMap<String, List<Sites>>();
        // 查询子类的名称
        val subCategory = subCategoryDao.selectById(id);
        val name = subCategory.getName();
        // 查询信息
        val sitesQueryWrapper = new QueryWrapper<Sites>();
        sitesQueryWrapper.eq("subSort", id);
        val sites = sitesDao.selectList(sitesQueryWrapper);
        data.put(name, sites);

        return data;
    }


}
