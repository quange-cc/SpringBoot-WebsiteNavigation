package com.quange.utils.jwt;

import cn.hutool.core.codec.Base64;
import lombok.val;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


/**
 * 传入网站url，通过api获取网站ico，进行base64编码返回
 */
public class DownIco {

    // 下载网站的ico图片
    public static String downIco(String sitesUrl) throws IOException {
        // 构造URL,获取网站ico的api
        URL url = new URL("https://favicon.zhusl.com/ico?url=" + sitesUrl);

        // 打开连接
        URLConnection urlConnection = url.openConnection();
        // 输入流
        InputStream is = urlConnection.getInputStream();

        val OutputStream = new ByteArrayOutputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        int len;
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            OutputStream.write(bs, 0, len);
        }

        // 完毕，关闭所有链接
        OutputStream.close();
        is.close();
        return Base64.encode(OutputStream.toByteArray());

    }
}
