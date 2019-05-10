package cn.caoler.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by caole on 2019/3/13 10:44
 */
@Entity
@Data
public class Clazz {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clazzId;

    /** 班级名称 */
    private String clazzName;

    /** 班级编号. */
    private String clazzNo;



}
