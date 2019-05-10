package cn.caoler.evaluation.service.impl;

import cn.caoler.evaluation.dataobject.Option;
import cn.caoler.evaluation.exception.EvaluationException;
import cn.caoler.evaluation.repository.OptionRepository;
import cn.caoler.evaluation.enums.ResultEnum;
import cn.caoler.evaluation.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caole on 2019/4/9 14:05
 */
@Service
@Transactional
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public List<Option> findOptionsByIndexId(Long indexId) {
        return optionRepository.findOptionsByIndexId(indexId);
    }

    @Override
    public Page<Option> findOptionsByIndexId(Long indexId,Pageable pageable) {

        return optionRepository.findOptionsByIndexId(indexId,pageable);
    }

    @Override
    public Integer countOptionsForPage(Long indexId) {
        return optionRepository.countOptionsForPage(indexId);
    }

    @Override
    public Option findOptionByOptionId(Long optionId) {
        return optionRepository.findOptionByOptionId(optionId);
    }

    @Override
    public Option update(Option option) {
        Option result;
        if (option.getOptionId()!= null){
            result = optionRepository.save(option);
        }else {
            throw new EvaluationException(ResultEnum.OPTION_NOT_EXIST);
        }
        return  result;
    }

    @Override
    public Option create(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public void delete(Long optionId) {
        optionRepository.deleteByOptionId(optionId);
    }
}
