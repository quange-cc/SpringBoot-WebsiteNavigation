package com.quange.utils.jwt;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;


/**
 * 生成jwt
 */
public class Jwt {
    public static final byte[] KEY = "850433244".getBytes();


    // 创建token
    public static String crateToken(String name) {
        DateTime now = DateTime.now();
        //现行时间后的60分钟
        DateTime newTime = now.offsetNew(DateField.MINUTE, 60);
        HashMap<String, Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);

        payload.put("username", name);

        return JWTUtil.createToken(payload, KEY);
    }

}
