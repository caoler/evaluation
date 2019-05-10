package cn.caoler.evaluation.service;

import cn.caoler.evaluation.dataobject.IndexRole;

import java.util.List;

/**
 *  指标--角色表
 * Created by caole on 2019/4/15 15:13
 */
public interface IndexRoleService {

    List<IndexRole> findIndexRolesByRoleId(Long roleId);
}
