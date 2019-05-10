package cn.caoler.evaluation.converter;

import cn.caoler.evaluation.dataobject.User;
import cn.caoler.evaluation.dto.UserDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  用户类型转换
 * Created by caole on 2019/3/17 16:20
 */
public class User2UserDTOConverter {

    public static UserDTO convert(User user){

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public static List<UserDTO> convert(List<User> userList){
        return userList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }

}
