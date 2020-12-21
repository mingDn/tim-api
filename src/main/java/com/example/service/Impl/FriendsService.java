package com.example.service.Impl;

import com.example.dao.IFriendsDao;
import com.example.domin.Friends;
import com.example.domin.Group;
import com.example.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService implements IFriendsService {

    @Autowired
    IFriendsDao friendsDao;

    @Override
    public List<Group> groupSelect(String account) {
        return friendsDao.groupSelect(account);
    }

    public List<Friends> friendsSelect(String account, Integer groupId) {
        return friendsDao.friendSelectByGroup(account, groupId);
    }
}
