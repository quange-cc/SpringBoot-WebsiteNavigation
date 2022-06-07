package com.quange.service.impl;

import cn.hutool.json.JSONUtil;
import com.quange.dao.BackgroundStyleDao;
import com.quange.domain.BackgroundStyle;
import com.quange.service.BackgroundStyleService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BackgroundStyleImpl implements BackgroundStyleService {

    @Autowired
    private BackgroundStyleDao backgroundStyleDao;


    // 查询数据
    @Override
    public List<BackgroundStyle> getBackgroundStyles() {
        return backgroundStyleDao.selectList(null);
    }


    // 添加数据
    @Override
    public boolean addBackgroundStyle(String s) {
        val parse = JSONUtil.parseObj(s);
        val name = parse.getStr("name");
        val style = parse.getStr("style");

        val data = new BackgroundStyle();
        data.setStyle(style);
        data.setName(name);

        val insert = backgroundStyleDao.insert(data);
        return insert > 0;
    }


    /**
     * 根据传入的id，删除数据
     */
    @Override
    public boolean deleteBackgroundStyle(Integer id) {

        val i = backgroundStyleDao.deleteById(id);

        return i > 0;
    }


}
