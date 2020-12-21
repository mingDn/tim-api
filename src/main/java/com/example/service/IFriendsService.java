package com.example.service;

import com.example.domin.Friends;
import com.example.domin.Group;

import java.util.List;

public interface IFriendsService {

    List<Group> groupSelect(String account);

    List<Friends> friendsSelect(String account, Integer groupId);
}
