package cn.caoler.evaluation.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 指标表
 * Created by caole on 2019/3/13 10:54
 */
@Entity
@Data
@Table(name = "[Index]")
public class Index {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "[indexId]")
    private Long indexId;

    /** 指标名 */
    private String indexName;

    /** 父结点 */
    private Long parentId;

    /** 权重. */
    private BigDecimal weight;

    /** 是否生效 (0、不生效)(1、生效)*/
    private Integer isEffective;

    /** 创建时间. */
    private Date createTime;

    /** 修改时间. */
    private Date updateTime;
}
