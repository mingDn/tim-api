package com.example.dao;

import com.example.domin.Friends;
import com.example.domin.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFriendsDao {

    /**
     * 向group表添加数据
     * @param account
     * @param groupName
     * @return
     */
    @Insert("insert into `group`(`account`, `groupName`) values (#{account}, #{groupName})")
    boolean groupAdd(String account, String groupName);

    /**
     * 查询好友分组
     * @param account
     * @return
     */
    @Select("select groupId,groupName from `group` where account = #{account}")
    List<Group> groupSelect(String account);

    /**
     * 根据account查询好友列表
     * @param account
     * @return
     */
    @Select("select account,name,path from user,friends where friends.u_account = #{account} and friends.f_account = user.account and friends.groupId = #{groupId}")
    List<Friends> friendSelectByGroup(String account, Integer groupId);
}
