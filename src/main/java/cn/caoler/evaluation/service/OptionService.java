package cn.caoler.evaluation.service;

import cn.caoler.evaluation.dataobject.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *  选项服务层
 * Created by caole on 2019/4/9 14:03
 */
public interface OptionService {

    /** 查询某个指标的选项 .*/
    List<Option> findOptionsByIndexId(Long indexId);


    /** 分页查询当前指标的所有选项 .*/
    Page<Option> findOptionsByIndexId(Long indexId, Pageable pageable);


    Integer countOptionsForPage(Long indexId);

    Option findOptionByOptionId(Long optionId);


    Option update(Option option);


    Option create(Option option);


    void delete(Long optionId);




}
