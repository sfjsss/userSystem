package com.tianyuli.usersystem;

import com.tianyuli.usersystem.rpcDomain.common.utils.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersystemApplicationTests {

    @Test
    public void testToken() throws Exception {
        String username = "test123";
        String userId = "123456";
        String token = JwtTokenUtil.createJWT(userId, username);
        Assertions.assertFalse(JwtTokenUtil.isExpiration(token));
        Assertions.assertEquals(username, JwtTokenUtil.getUsername(token));
        Assertions.assertEquals(userId, JwtTokenUtil.getUserId(token));
    }

}
