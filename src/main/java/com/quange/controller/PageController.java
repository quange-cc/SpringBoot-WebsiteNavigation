package com.quange.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.quange.domain.*;
import com.quange.service.*;
import com.quange.utils.jwt.Jwt;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:8080", allowCredentials = "true")
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private SitesService service;

    @Autowired
    private SortService sortService;

    @Autowired
    private ParentService parentService;

    @Autowired
    private SettingService settingService;


    @Autowired
    private BackgroundStyleService backgroundStyleService;


    // 返回所有的网址信息
    @GetMapping("sites")
    public Result all() {
        return new Result(Code.GET_OK, "成功", service.getSitesAll());
    }

    // 返回主页侧栏数据
    @GetMapping("getAside")
    public Result getAside() {
        List<ParentCategory> category = parentService.getCategory();
        return new Result(Code.GET_OK, "获取成功", category);
    }


    @GetMapping("validateToken")
    public Result valDateToken() {
        return new Result(Code.TOKEN_OK, "token 有效");
    }


    // 根据子分类id，返回所属分类的所有数据
    @GetMapping("getWebSitesById")
    public Result getWebSitesById(Integer id) {
        if (id == null) {
            return new Result(Code.GET_ERR, "id为空值");
        }
        val data = service.getDataById(id);
        return new Result(Code.GET_OK, "成功", data);
    }


    // 登录请求
    @PostMapping("login")
    public Result login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 读取传入的字符串
        String s = req.getReader().readLine();

        // 接收传入的用户信息字符串，JSON字符串解析
        JSONObject userData = JSONUtil.parseObj(s);

        // 取用户名，密码
        String username = userData.getStr("username");
        String password = userData.getStr("passwd");

        // 验证账号密码
        boolean loginStatus = userService.checkUser(username, password);

        if (loginStatus) {

            String token = Jwt.crateToken(username);

//            Cookie cookie = new Cookie("token", token);
//            cookie.setPath("/");
//            cookie.setMaxAge(60 * 60);
//            resp.setHeader("Set-Cookie", "token1=123;Secure;SameSite=None");
//            resp.addCookie(cookie);

            return new Result(Code.LOGIN_OK, "登录成功", token);

        } else {

            return new Result(Code.LOGIN_ERR, "用户名或密码错误");
        }

    }

    // 修改密码
    @PostMapping("updatePasswd")
    public Result updatePasswd(HttpServletRequest req) throws IOException {
        val s = req.getReader().readLine();
        val b = userService.updatePasswd(s);

        if (b) {
            return new Result(Code.UPDATE_OK, "修改成功");
        }
        return new Result(Code.UPDATE_ERR, "修改失败");
    }

    // 分页查询
    @GetMapping("getAllData")
    public Result getAll(Integer page, Integer limit) {
        val allAdmin = service.getAllAdmin(page, limit);
        val data = allAdmin.get("data");
        long count = (long) allAdmin.get("count");

        return new Result(Code.GET_OK, "成功", count, data);
    }

    // 父分类数据
    @GetMapping("getAllParent")
    public Result getAllParent() {
        return new Result(Code.GET_OK, "ok", sortService.getParentAll());
    }

    // 接收父分类id，查询所属子分类数据
    @GetMapping("getAllSub")
    public Result getAllSub(Integer id) {
        if (id == null) {
            return new Result(Code.GET_ERR, "数据为空");
        }
        List<SubCategory> sub = sortService.getSubById(id);

        return new Result(Code.GET_OK, sub);
    }


    // 添加数据
    @PostMapping("addSitesData")
    public Result addSitesData(HttpServletRequest req) throws IOException {
        // 读取传入的字符串
        String s = req.getReader().readLine();

        boolean b = service.addSites(s);
        if (b) {
            return new Result(Code.SAVE_OK, "保存成功");
        }
        return new Result(Code.SAVE_ERR, "保存失败");
    }


    // 删除数据
    @GetMapping("delSitesData")
    public Result delSiteData(Integer id) {
        if (id == null) {
            return new Result(Code.DELETE_ERR, "失败");
        }
        boolean b = service.delSites(id);
        if (b) {
            return new Result(Code.DELETE_OK, "删除成功");
        } else {
            return new Result(Code.DELETE_ERR, "失败");
        }
    }


    // 修改数据
    @PostMapping("updateSitesData")
    public Result updateSitesData(HttpServletRequest req) throws IOException {
        // 读取传入的字符串
        String s = req.getReader().readLine();
        boolean b = service.updateSites(s);

        if (b) {
            return new Result(Code.UPDATE_OK, "修改成功");
        }

        return new Result(Code.UPDATE_ERR, "修改失败");
    }


    // 获取分类信息
    @GetMapping("getCategoryAll")
    public Result getCategoryAll() {
        List<ParentCategory> category = parentService.getCategory();
        return new Result(Code.GET_OK, "获取成功", category);
    }


    // 添加主分类
    @PostMapping("addParentData")
    public Result addParent(HttpServletRequest req) throws IOException {
        // 读取传入的字符串
        String s = req.getReader().readLine();

        boolean b = parentService.addParent(s);

        if (b) {
            return new Result(Code.SAVE_OK, "添加成功");
        }

        return new Result(Code.SAVE_ERR, "添加失败");
    }


    //添加子分类信息addSubData
    @PostMapping("addSubData")
    public Result addSubData(HttpServletRequest req) throws IOException {

        // 读取传入的字符串
        String s = req.getReader().readLine();
        boolean b = sortService.addSubSort(s);
        if (b) {
            return new Result(Code.SAVE_OK, "添加成功");
        }
        return new Result(Code.SAVE_ERR, "添加失败");
    }


    // 按id删除子分类
    @GetMapping("delSubData")
    public Result delSubData(Integer id) {
        if (id == null) {
            return new Result(Code.DELETE_ERR, "不容许为空");
        }

        boolean b = sortService.delSubSort(id);
        if (b) {
            return new Result(Code.DELETE_OK, "删除成功");
        }

        return new Result(Code.DELETE_ERR, "不容许为空");
    }

    // 按id删除主分类
    @GetMapping("delParent")
    public Result delParent(Integer id) {
        if (id == null) {
            return new Result(Code.DELETE_ERR, "不容许为空");
        }

        boolean b = parentService.delParent(id);
        if (b) {
            return new Result(Code.DELETE_OK, "删除成功");
        }

        return new Result(Code.DELETE_ERR, "删除失败");
    }


    // 按id修改主分类信息
    @PostMapping("updateParent")
    public Result updateParent(HttpServletRequest req) throws IOException {
        // 读取传入的字符串
        String s = req.getReader().readLine();
        boolean b = parentService.updateParent(s);

        if (b) {
            return new Result(Code.UPDATE_OK, "修改成功");
        }

        return new Result(Code.UPDATE_ERR, "修改失败");
    }


    //按id修改子分类信息
    @PostMapping("updateSubSort")
    public Result updateSubSort(HttpServletRequest req) throws IOException {
        // 读取传入的字符串
        String s = req.getReader().readLine();
        boolean b = sortService.updateSubSort(s);

        if (b) {
            return new Result(Code.UPDATE_OK, "修改成功");
        }
        return new Result(Code.UPDATE_ERR, "修改失败");

    }

    // 返回样式列表
    @GetMapping("getBackgroundStyle")
    public Result getBackgroundStyle() {
        val data = backgroundStyleService.getBackgroundStyles();
        return new Result(Code.GET_OK, "成功", data);
    }

    // 添加样式
    @PostMapping("addBackgroundStyle")
    public Result addBackgroundStyle(HttpServletRequest req) throws IOException {
        val s = req.getReader().readLine();
        val b = backgroundStyleService.addBackgroundStyle(s);

        if (b) {
            return new Result(Code.SAVE_OK, "保存成功");
        }

        return new Result(Code.SAVE_ERR, "保存失败");
    }

    // 删除样式
    @GetMapping("delBackgroundStyle")
    public Result deleteBackgroundStyle(Integer id) {
        if (id == null) {
            return new Result(Code.DELETE_ERR, "id为空");
        }
        val b = backgroundStyleService.deleteBackgroundStyle(id);

        if (b) {
            return new Result(Code.DELETE_OK, "删除成功");
        }

        return new Result(Code.DELETE_ERR, "删除失败");
    }

    // 修改系统设置
    @PostMapping("updateSettings")
    public Result updateSettings(HttpServletRequest req) throws IOException {

        // 读取传入的字符串
        String s = req.getReader().readLine();
        boolean b = settingService.updateSettings(s);

        if (b) {
            return new Result(Code.UPDATE_OK, "修改成功");
        }

        return new Result(Code.UPDATE_ERR, "修改错误");
    }

    @GetMapping("getSettings")
    public Result getSettings() {
        List<Settings> all = settingService.getAll();

        return new Result(Code.GET_OK, "获取成功", all);
    }


}
