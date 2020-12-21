package com.example;

import com.example.service.IFriendsService;
import com.example.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TimApplication.class)
public class timTest {

    @Autowired
    private IUserService userService;
    @Autowired
    private IFriendsService friendsService;

    @Test
    public void test(){

    }
}
