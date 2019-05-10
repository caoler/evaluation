package cn.caoler.evaluation.service;

import cn.caoler.evaluation.dto.ChannelControlDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by caole on 2019/4/12 22:49
 */
public interface ChannelControlService {

    Integer count();

    Page<ChannelControlDTO> findAll(Pageable pageable);


    ChannelControlDTO findByChannelControlId(Long channelControlId);

    ChannelControlDTO update(ChannelControlDTO channelControlDTO);


    ChannelControlDTO findBySemesterId(Long semesterId);
}
