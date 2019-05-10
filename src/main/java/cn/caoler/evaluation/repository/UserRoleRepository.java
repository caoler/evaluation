package cn.caoler.evaluation.repository;

import cn.caoler.evaluation.dataobject.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by caole on 2019/3/20 14:24
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long>{

    /** 通过userId查询该用户所拥有的所有角色.  */
      List<UserRole> findUserRolesByUserId(Long userId);

}
