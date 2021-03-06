package cn.caoler.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 指标——角色表(index_role)
 * Created by caole on 2019/3/13 16:43
 */
@Entity
@Data
public class IndexRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indexRoleId;

    /** 指标id */
    private Long indexId;

    /** 角色id */
    private Long roleId;
}
