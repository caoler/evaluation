package cn.caoler.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  功能表
 * Created by caole on 2019/3/13 11:26
 */
@Entity
@Data
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actionId;

    /** 功能名. */
    private String actionName;

    /** 功能地址. */
    private String url;

    /** 父结点.*/
    private Long parentId;

}
