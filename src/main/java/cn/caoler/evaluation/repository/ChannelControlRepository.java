package cn.caoler.evaluation.repository;

import cn.caoler.evaluation.dataobject.ChannelControl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by caole on 2019/4/12 22:35
 */
public interface ChannelControlRepository extends JpaRepository<ChannelControl,Long> {

    ChannelControl findByChannelControlId(Long channelControlId);

    void deleteBySemesterId(Long semesterId);

    ChannelControl findBySemesterId(Long semesterId);

}
