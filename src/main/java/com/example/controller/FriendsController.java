package com.example.controller;

import com.example.domin.Friends;
import com.example.domin.Group;
import com.example.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    IFriendsService friendsService;

    /**
     * 查询好友分组
     *
     * @param request
     * @return
     */
    @RequestMapping("/groupSelect")
    public List<Group> groupSelect(HttpServletRequest request) {
        String account = request.getParameter("account");
        return friendsService.groupSelect(account);
    }

    /**
     * 查询好友分组
     *
     * @param request
     * @return
     */
    @RequestMapping("/friendsSelect")
    public List<Friends> friendsSelect(HttpServletRequest request) {
        String account = request.getParameter("account");
        Integer groupId = Integer.parseInt(request.getParameter("groupId"));
        return friendsService.friendsSelect(account, groupId);
    }
}
