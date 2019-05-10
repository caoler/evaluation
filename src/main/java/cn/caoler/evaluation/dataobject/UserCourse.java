package cn.caoler.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户——课程 关系表
 * Created by caole on 2019/3/13 16:35
 */
@Data
@Entity
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCourseId;

    /** 用户编号 */
    private String userNo;

    /** 课程编号 */
    private String cnum;


}
