package cn.caoler.evaluation.service.impl;

import cn.caoler.evaluation.dataobject.Action;
import cn.caoler.evaluation.exception.EvaluationException;
import cn.caoler.evaluation.repository.ActionRepository;
import cn.caoler.evaluation.enums.ResultEnum;
import cn.caoler.evaluation.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  功能业务
 * Created by caole on 2019/3/18 16:06
 */
@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;


    @Override
    public Action create(Action action) {
        return actionRepository.save(action);
    }

    @Override
    @Transactional
    public void delete(Long actionId) {
        actionRepository.deleteByActionId(actionId);
    }

    @Override
    public Action findByActionId(Long actionId) {
        return actionRepository.findByActionId(actionId);
    }

    @Override
    public Page<Action> findAll(Pageable pageable) {
        return actionRepository.findAll(pageable);
    }

    @Override
    public List<Action> findAll() {
        return actionRepository.findAll();
    }

    @Override
    public Page<Action> findActionIfActionNameIsNotNull(String actionName, Pageable pageable) {
        Page<Action> actionPage  = actionRepository.findActionIfActionNameIsNotNull(actionName,pageable);
        return actionPage;
    }

    @Override
    public Integer countActionForPage(String actionName) {
        Integer count = actionRepository.countActionForPage(actionName);
        return count;
    }


    @Override
    public Action update(Action action) {
        Action result;
        if (action.getActionId()!= null){
            result = actionRepository.save(action);
        }else {
            throw new EvaluationException(ResultEnum.COURSE_NOT_EXIST);
        }
        return  result;
    }
}
