package cn.caoler.evaluation.service.impl;

import cn.caoler.evaluation.converter.ChannelControl2ChannelControlDTOConverter;
import cn.caoler.evaluation.dataobject.ChannelControl;
import cn.caoler.evaluation.repository.ChannelControlRepository;
import cn.caoler.evaluation.repository.SemesterRepository;
import cn.caoler.evaluation.dto.ChannelControlDTO;
import cn.caoler.evaluation.service.ChannelControlService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.caoler.evaluation.converter.ChannelControl2ChannelControlDTOConverter.convert;

/**
 * Created by caole on 2019/4/12 22:53
 */
@Service
@Transactional
public class ChannelControlServiceImpl implements ChannelControlService {

    @Autowired
    private ChannelControlRepository channelControlRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Integer count() {
        Integer count =  new Long(channelControlRepository.count()).intValue();
        return count;
    }

    @Override
    public Page<ChannelControlDTO> findAll(Pageable pageable) {
        Page<ChannelControl> channelControlPage = channelControlRepository.findAll(pageable);
        List<ChannelControl> channelControlList = channelControlPage.getContent();
        List<ChannelControlDTO> channelControlDTOList = ChannelControl2ChannelControlDTOConverter.convert(channelControlList);
        for (ChannelControlDTO channelControlDTO : channelControlDTOList){
            channelControlDTO.setSemesterName(semesterRepository.findBySemesterId(channelControlDTO.getSemesterId()).getSemesterName());
        }
        Page<ChannelControlDTO> channelControlDTOPage = new PageImpl<ChannelControlDTO>(channelControlDTOList,pageable,channelControlPage.getTotalElements());
        return channelControlDTOPage;
    }

    @Override
    public ChannelControlDTO findByChannelControlId(Long channelControlId) {
        ChannelControl channelControl = channelControlRepository.findByChannelControlId(channelControlId);
        ChannelControlDTO channelControlDTO = ChannelControl2ChannelControlDTOConverter.convert(channelControl);
        return channelControlDTO;
    }

    @Override
    public ChannelControlDTO update(ChannelControlDTO channelControlDTO) {
        ChannelControl channelControl = new ChannelControl();
        BeanUtils.copyProperties(channelControlDTO,channelControl);
        ChannelControl result = channelControlRepository.save(channelControl);
        return ChannelControl2ChannelControlDTOConverter.convert(result);
    }

    @Override
    public ChannelControlDTO findBySemesterId(Long semesterId) {
        ChannelControl channelControl  = channelControlRepository.findBySemesterId(semesterId);
        return ChannelControl2ChannelControlDTOConverter.convert(channelControl);
    }
}
