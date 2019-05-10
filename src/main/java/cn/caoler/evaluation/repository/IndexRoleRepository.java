package cn.caoler.evaluation.repository;

import cn.caoler.evaluation.dataobject.IndexRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *  指标---角色   关系表
 * Created by caole on 2019/4/8 21:59
 */
public interface IndexRoleRepository extends JpaRepository<IndexRole,Long> {

    /** 查询某个角色拥有的指标 .*/
    List<IndexRole> findIndexRolesByRoleId(Long roleId);


}
