package com.example.dao;

import com.example.domin.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {

    /**
     * 向user表添加一条数据
     * @param user
     * @return
     */
    @Insert("insert into user(account, password, name, birthday) values (#{account}, #{password}, #{name}, #{birthday})")
    boolean userAdd(User user);

    /**
     * 通过account查询数据
     * @param account
     * @return
     */
    @Select("select account,name,path,sex,age,birthday from user where account = #{account}")
    User userSelectByAccount(String account);

    /**
     * 通过account和password查询数据
     * @param account
     * @param password
     * @return
     */
    @Select("select account,password,state from user where account = #{account} and password = #{password}")
    User userSelect(String account, String password);

    /**
     * 通过account修改state
     * @param account
     * @param state
     * @return
     */
    @Update("update user set state = #{state} where account = #{account}")
    boolean updateState(String account, int state);

    /**
     * 通过account修改password
     * @param account
     * @param password
     * @return
     */
    @Update("update user set password = #{password} where account = #{account}")
    boolean updatePassword(String account, String password);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Update("update user set name = #{name}, sex = #{sex}, age = #{age}, birthday = #{birthday}  where account = #{account}")
    boolean updateUser(User user);

    /**
     * 通过account修改path
     * @param account
     * @param path
     * @return
     */
    @Update("update user set path = #{path} where account = #{account}")
    boolean updatePath(String account, String path);
}
