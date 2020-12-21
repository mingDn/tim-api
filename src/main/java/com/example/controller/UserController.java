package com.example.controller;

import com.example.domin.User;
import com.example.service.IUserService;
import com.example.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 注册账号
     * @param request
     * @return
     */
    @RequestMapping("/register")
    public Map register(HttpServletRequest request) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        Map<String, Object> data = userService.doRegister(account, password, name);
        return data;
    }

    /**
     * 账号登录
     * @param request
     * @return
     */
    @RequestMapping("/load")
    public Map load(HttpServletRequest request) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Map<String, Object> data = userService.load(account, password);
        return data;
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public Map logOut(HttpServletRequest request) {
        String account = request.getParameter("account");
        Map<String, Object> data = userService.logOut(account);
        return data;
    }

    /**
     * 找回密码校验账号及验证码
     * @param request
     * @return
     */
    @RequestMapping("/accountCheck")
    public Map accountCheck(HttpServletRequest request) {
        String account = request.getParameter("account");
        String verifyCode = request.getParameter("verifyCode").toLowerCase();
        HttpSession session = request.getSession();
        Map<String, Object> data = new HashMap<>();
        if (!verifyCode.equals(session.getAttribute("verCode"))) {
            data.put("info", "0");
            data.put("message", "验证码错误");
        } else if (!userService.accountCheck(account)) {
            data.put("info", "0");
            data.put("message", "账号不存在");
        } else {
            data.put("info", "1");
            data.put("message", "校验成功");
        }
        return data;
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @RequestMapping("/updatePassword")
    public Map updatePassword(HttpServletRequest request) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Map<String, Object> data = userService.updatePassword(account, password);
        return data;
    }


    /**
     * 修改头像
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/changePicture")
    public User changePicture(MultipartFile file, HttpServletRequest request) throws IOException{
        DateFormat df = new SimpleDateFormat("/yyyy.MM.dd/");
        String date = df.format(new Date());
        String realPath = "D:\\IDEA\\file\\img" + date;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString().replace("-", "") + oldName;
        file.transferTo(new File(folder, newName));
        File oldFile = new File("D:\\IDEA\\file\\img" + request.getParameter("oldFile"));
        if (oldFile.exists()) {
            oldFile.delete();
        }
        String path = date.replaceAll("/", "_") + newName;
        String account = request.getParameter("account");
        User user = userService.changePicture(account, path);
        return user;
    }

    /**
     * 获取头像
     * @param path
     * @return
     */
    @RequestMapping("/getPicture/{path}")
    public FileSystemResource getPicture(@PathVariable("path") String path){
        path = path.replaceAll("_","/");
        FileSystemResource resource = new FileSystemResource("D:\\IDEA\\file\\img" + path);
        return resource;
    }

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 使用服务器端控制AJAX页面缓存
        // 为了防止浏览器缓存动态内容生成JSP或Servlet,使每次都是一个新的请求而不是使用缓存的页面
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字符串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除之前存入的验证码
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        session.setAttribute("codeTime", LocalDateTime.now());
        // 生成图片并输出图片流
        int w = 120, h = 38;
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
    }
}
