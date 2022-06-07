package com.quange.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quange.dao.UserDao;
import com.quange.domain.User;
import com.quange.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserImpl implements UserService {
    @Autowired
    private UserDao userDao;


    // 登录
    @Override
    public boolean checkUser(String username, String password) {

        // 执行sql查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userDao.selectOne(wrapper);

        if (user == null) {
            return false;
        }
        // md5 加密
        String pw = SecureUtil.md5().digestHex16(password);

        // 判断密码是否一致
        return user.getPassword().equals(pw);

    }


    // 修改密码
    @Override
    public boolean updatePasswd(String s) {

        val entries = JSONUtil.parseObj(s);
        val token = entries.getStr("token");
        val oldPasswd = entries.getStr("oldPasswd");
        val newPasswd = entries.getStr("newPasswd");
        // 解析token
        String username = (String) JWTUtil.parseToken(token).getPayload("username");

        //将传入的旧密码MD5加密
        val hex16 = SecureUtil.md5().digestHex16(oldPasswd);

        // 验证旧密码是否正确
        // 执行sql查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userDao.selectOne(wrapper);
        val id = user.getId();
        val newUser = new User();
        if (Objects.equals(user.getPassword(), hex16)) {
            newUser.setId(id);
            newUser.setUsername(username);
            val s1 = SecureUtil.md5().digestHex16(newPasswd);
            newUser.setPassword(s1);
        }
        val i = userDao.updateById(newUser);


        return i > 0;
    }

}
