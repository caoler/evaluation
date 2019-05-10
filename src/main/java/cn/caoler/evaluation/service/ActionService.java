package cn.caoler.evaluation.service;

import cn.caoler.evaluation.dataobject.Action;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *  功能业务逻辑
 * Created by caole on 2019/3/14 9:33
 */
public interface ActionService {

    /** 添加功能. */
    Action create(Action action);

    /** 删除功能. */
    void delete(Long actionId);

    /** 根据id查询功能 */
    Action findByActionId(Long actionId);


    /** 分页查询所有功能 **/
    Page<Action> findAll(Pageable pageable);

    /** 不分页查询所有功能.*/
    List<Action> findAll();

    Page<Action> findActionIfActionNameIsNotNull(String actionName, Pageable pageable);


    Integer countActionForPage(String actionName);


    Action update(Action action);


}
