package cn.caoler.evaluation.dataobject.mapper;

import cn.caoler.evaluation.dataobject.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by caole on 2019/3/26 22:29
 */
public interface UserMapper {

    List<User> findUsersIfUsernameIsNotNull(@Param("username") String username);
}
