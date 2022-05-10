package com.songshu.usercenter.service;

import com.songshu.usercenter.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-04 下午 9:03
 * @Problems:这个只需要将启动类的包的路径和测试类的路径保持一致就可以了。
 *           Test路径和java路径不一致导致找不到启动类入口 所以报错吗
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {


    @Resource
    private UserService userService;

    @Test
    public void testInsUser(){

        User user = new User();
        user.setUsername("dogYupi");
        user.setUserAccount("123");
        user.setAvatarUrl("www.baidu.com");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123");
        user.setEmail("546");

        boolean save = userService.save(user);
        System.out.println(user.getId());

        Assertions.assertTrue(save);

    }

    @Test
    public void regTest(){

        User user = new User();
        long dogYupi = userService.userRegistry("dogYupi", "12345678", "12345678");
        System.out.println(dogYupi);
    }

    @Test
    public void logTest(){

//        User user = new User();
//        User dogYupi = userService.userLogin("dogYupi", "12345678");
//        System.out.println(dogYupi);
    }
}