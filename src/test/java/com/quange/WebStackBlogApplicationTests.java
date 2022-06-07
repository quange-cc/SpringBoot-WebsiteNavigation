package com.quange;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ClassUtil;

import com.quange.dao.*;

import com.quange.service.SitesService;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import java.util.Objects;


@SpringBootTest
class WebStackBlogApplicationTests {

    @Autowired
    private ParentCategoryDao parentCategoryDao;

    @Autowired
    private SubCategoryDao subCategoryDao;

    @Autowired
    private SitesDao sitesDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SettingDao settingDao;

    @Autowired
    private SitesService sitesService;


    @Test
    void contextLoads() throws IOException {
        // 构造URL
        URL url = new URL("https://favicon.zhusl.com/ico?url=https://xn--qpru0x.cn/");
        // 打开连接 URL连接读取的输入流
        InputStream inputStream = url.openConnection().getInputStream();
//
//        URLConnection urlConnection = url.openConnection();
//        // 输入流
//        InputStream is = urlConnection.getInputStream();

        val byteArrayOutputStream = new ByteArrayOutputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];

        int len;
        // 开始读取
        while ((len = inputStream.read(bs)) != -1) {
            byteArrayOutputStream.write(bs, 0, len);
        }

        inputStream.close();
        byteArrayOutputStream.close();
        // 转换成base64编码
        val encode = Base64.encode(byteArrayOutputStream.toByteArray());

        System.out.println(encode);


    }


    @Test
    void downIco() {
        val all = sitesDao.getAll();

        System.out.println(all);
    }


}
