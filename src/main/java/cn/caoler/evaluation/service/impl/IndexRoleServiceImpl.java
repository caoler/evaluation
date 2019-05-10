package cn.caoler.evaluation.service.impl;

import cn.caoler.evaluation.dataobject.IndexRole;
import cn.caoler.evaluation.repository.IndexRoleRepository;
import cn.caoler.evaluation.service.IndexRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caole on 2019/4/15 15:14
 */
@Service
@Transactional
public class IndexRoleServiceImpl implements IndexRoleService {

    @Autowired
    private IndexRoleRepository indexRoleRepository;

    @Override
    public List<IndexRole> findIndexRolesByRoleId(Long roleId) {
        List<IndexRole> indexRoleList = indexRoleRepository.findIndexRolesByRoleId(roleId);
        return indexRoleList;
    }
}
