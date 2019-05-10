package cn.caoler.evaluation.dataobject.mapper;

import cn.caoler.evaluation.dataobject.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by caole on 2019/3/26 23:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUsersIfUsernameIsNotNull() {
        List<User> result = userMapper.findUsersIfUsernameIsNotNull("康一凡");
        Assert.assertNotNull(result );
    }
}