package cn.caoler.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by caole on 2019/3/13 10:11
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**  人员编号. */
    private String userNo;

    /**  密码. */
    private String password;

    /** 身份. */
    private Integer identity;

    /** 用户姓名. */
    private String username;

    /** 院系编号. */
    private String departmentNo;

    /** 班级编号. */
    private String clazzNo;




}
