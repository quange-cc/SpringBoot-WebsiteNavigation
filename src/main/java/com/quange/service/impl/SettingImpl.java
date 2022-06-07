package com.quange.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quange.dao.SettingDao;
import com.quange.domain.Settings;
import com.quange.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingImpl implements SettingService {

    @Autowired
    private SettingDao settingDao;

    @Override
    public boolean updateSettings(String s) {

        // 接收传入的用户信息字符串，JSON字符串解析
        JSONObject settingData = JSONUtil.parseObj(s);
        String siteName = settingData.getStr("sitename");
        String domain = settingData.getStr("domain");
        int cache = Integer.parseInt(settingData.getStr("cache"));
        String title = settingData.getStr("title");
        String descript = settingData.getStr("descript");
        String keywords = settingData.getStr("keywords");
        String copyright = settingData.getStr("copyright");

        Settings setting = new Settings();
        setting.setId(1);
        setting.setName(siteName);
        setting.setDomain(domain);
        setting.setCache(cache);
        setting.setTitle(title);
        setting.setDes(descript);
        setting.setKeyword(keywords);
        setting.setBottom(copyright);
        // 执行sql
        int i = settingDao.updateById(setting);

        return i > 0;

    }

    @Override
    public List<Settings> getAll() {

        return settingDao.selectList(null);
    }

}
