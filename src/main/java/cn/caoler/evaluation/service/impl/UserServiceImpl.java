package cn.caoler.evaluation.service.impl;

import cn.caoler.evaluation.converter.User2UserDTOConverter;
import cn.caoler.evaluation.dataobject.Department;
import cn.caoler.evaluation.dataobject.User;
import cn.caoler.evaluation.dataobject.UserRole;
import cn.caoler.evaluation.exception.EvaluationException;
import cn.caoler.evaluation.repository.DepartmentRepository;
import cn.caoler.evaluation.repository.UserRepository;
import cn.caoler.evaluation.repository.UserRoleRepository;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.caoler.evaluation.dto.UserDTO;
import cn.caoler.evaluation.enums.ResultEnum;
import cn.caoler.evaluation.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.caoler.evaluation.converter.User2UserDTOConverter.convert;

/**
 * Created by caole on 2019/3/13 15:51
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;



    @Override
    public UserDTO create(UserDTO userDTO) {


        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        //保存user信息
        User user1 = userRepository.save(user);

//        UserRole userRole = new UserRole();
//        userRole.setUserId(user.getUserId());
//        userRole.setRoleId((long)user.getIdentity());
//        //保存user-role信息
//        userRoleRepository.save(userRole);
        return User2UserDTOConverter.convert(user1);
    }

    @Override
    public UserDTO findByUserId(Long userId) {

        User user = userRepository.findByUserId(userId);
        if (user == null){
            throw new EvaluationException(ResultEnum.USER_NOT_EXIST);
        }

        UserDTO userDTO = User2UserDTOConverter.convert(user);

        return userDTO;
    }

    @Override
    public UserDTO findByUserNo(String userNo) {

        User user = userRepository.findByUserNo(userNo);
        if (user == null){
            throw new EvaluationException(ResultEnum.USER_NOT_EXIST);
        }
        return User2UserDTOConverter.convert(userRepository.findByUserNo(userNo));

    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {

        Page<User> userPage =  userRepository.findAll(pageable);

        List<UserDTO> userDTOList = User2UserDTOConverter.convert(userPage.getContent());

        Page<UserDTO> userDTOPage = new PageImpl<UserDTO>(userDTOList,pageable,userPage.getTotalElements());

        return userDTOPage;

    }

    @Override
    public Page<UserDTO> findUsersIfUsernameIsNotNull(String username,Pageable pageable) {

        Page<User> userPage = userRepository.findUsersIfUsernameIsNotNull(username,pageable);

        List<UserDTO> userDTOList = User2UserDTOConverter.convert(userPage.getContent());

        Page<UserDTO> userDTOPage = new PageImpl<UserDTO>(userDTOList,pageable,userPage.getTotalElements());

        return userDTOPage;


    }

    @Override
    public Page<UserDTO> findAllByIdentity(Integer identity, Pageable pageable) {

        Page<User> userPage =  userRepository.findAllByIdentity(identity,pageable);

        List<UserDTO> userDTOList = User2UserDTOConverter.convert(userPage.getContent());

        Page<UserDTO> userDTOPage = new PageImpl<UserDTO>(userDTOList,pageable,userPage.getTotalElements());

        return userDTOPage;
    }

    @Override
    public Page<UserDTO> findAllByIdentityAndDepartmentNo(Integer identity, String departmentNo, Pageable pageable) {
        Page<User> userPage =  userRepository.findAllByIdentityAndDepartmentNo(identity,departmentNo,pageable);

        List<UserDTO> userDTOList = User2UserDTOConverter.convert(userPage.getContent());

        Page<UserDTO> userDTOPage = new PageImpl<UserDTO>(userDTOList,pageable,userPage.getTotalElements());

        return userDTOPage;
    }

    @Override
    public Page<UserDTO> findAllByClazzNo(String clazzNo, Pageable pageable) {
        Page<User> userPage =  userRepository.findAllByClazzNo(clazzNo,pageable);

        List<UserDTO> userDTOList = User2UserDTOConverter.convert(userPage.getContent());

        Page<UserDTO> userDTOPage = new PageImpl<UserDTO>(userDTOList,pageable,userPage.getTotalElements());

        return userDTOPage;
    }

    @Override
    public Integer countUsersByUserIdIsNotNull() {
        return userRepository.countUsersByUserIdIsNotNull();
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user;
        if (userDTO.getUserId() != null){
             user = userRepository.findByUserId(userDTO.getUserId());
        }else {
            throw new EvaluationException(ResultEnum.USER_NOT_EXIST);
        }

        //update user-role表
//        UserRole userRole = new UserRole();
//        userRole.setUserId(userDTO.getUserId());
//        userRole.setRoleId((long)userDTO.getIdentity());

//        UserRole delUserRole = new UserRole();
//        delUserRole.setRoleId((long)user.getIdentity());
//        delUserRole.setUserId(user.getUserId());
//        userRoleRepository.deleteById(user.getUserId());
//        userRoleRepository.save(userRole);

        /**
         *      属性拷贝，从src到target。  倘若src里面有空值，则不拷贝过去
         */
        BeanUtil.copyProperties(userDTO,user,true, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        User user1 = userRepository.save(user);
        return User2UserDTOConverter.convert(user1);
    }

    @Override
    public void deleteByUserId(Long userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public Integer countUserForPage(String username) {
        return userRepository.countUserForPage(username);
    }



    /** 根据院系编号查找院系. */
    @Override
    public Department getByDepartmentNo(String departmentNo) {

        Department department = departmentRepository.findByDepartmentNo(departmentNo);
        return department;
    }


    @Override
    public Page<User> findTeachers(String username,Pageable pageable) {

        Page<User> userPage = userRepository.findTeachers(username,pageable);

        return userPage;
    }

    @Override
    public Integer countTeachers(String username,Pageable pageable) {
        Page<User> userPage = findTeachers(username,pageable);
        Integer count = userPage.getContent().size();
        return count;
    }

    @Override
    public UserDTO findByUsername(String username) {
        User teacher = userRepository.findByUsername(username);
        UserDTO teacherDto = User2UserDTOConverter.convert(teacher);
        return teacherDto;
    }

    @Override
    public Page<UserDTO> findIfUserId(Long userId, Pageable pageable) {
        Page<User> userPage = userRepository.findIfUserId(userId,pageable);
        List<UserDTO> userDTOList = User2UserDTOConverter.convert(userPage.getContent());
        Page<UserDTO> userDTOPage = new PageImpl<UserDTO>(userDTOList,pageable,userPage.getTotalElements());

        return userDTOPage;
    }

    @Override
    public Integer countIfUserId(Long userId) {
        return userRepository.countIfUserId(userId);
    }


}
