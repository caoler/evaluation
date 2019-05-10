package cn.caoler.evaluation.service.impl;

import cn.caoler.evaluation.dataobject.Clazz;
import cn.caoler.evaluation.exception.EvaluationException;
import cn.caoler.evaluation.repository.ClazzRepository;
import cn.caoler.evaluation.enums.ResultEnum;
import cn.caoler.evaluation.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzRepository clazzRepository;

    @Override
    public Clazz create(Clazz clazz) {
        return clazzRepository.save(clazz);
    }

    @Override
    public Clazz updateClazz(Clazz clazz) {
        Clazz result;
        if (clazz.getClazzId()!= null){
            result = clazzRepository.save(clazz);
        }else {
            throw new EvaluationException(ResultEnum.CLAZZ_NOT_EXIST);
        }
        return  result;
    }

    @Override
    public Page<Clazz> findClazzIfClazzNameIsNotNull(String clazzName, Pageable pageable) {
        Page<Clazz> clazzPage = clazzRepository.findClazzIfClazzNameIsNotNull(clazzName,pageable);

        return clazzPage;
    }

    @Override
    public Integer countClazzForPage(String clazzName) {
        Integer count = clazzRepository.countClazzForPage(clazzName);

        return count;
    }

    @Override
    public Clazz findByClazzId(Long clazzId) {
        Clazz clazz = clazzRepository.findByClazzId(clazzId);
        if (clazz == null){
            throw new EvaluationException(ResultEnum.USER_NOT_EXIST);
        }

        return clazz;
    }

    @Override
    public void deleteByClazzId(Long clazzId) {
        clazzRepository.deleteByClazzId(clazzId);
    }
}
