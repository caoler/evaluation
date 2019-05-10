package cn.caoler.evaluation.dataobject.dao;

import cn.caoler.evaluation.dataobject.User;
import cn.caoler.evaluation.dataobject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *         整个项目我没有用mybatis，此处不做删除操作
 * Created by caole on 2019/3/27 0:15
 */
@Repository
public class UserDao{

    @Autowired
    UserMapper mapper;

    public List<User> findUsersIfUsernameIsNotNull(String username){
        return mapper.findUsersIfUsernameIsNotNull(username);
    }
}
