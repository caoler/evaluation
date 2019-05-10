package cn.caoler.evaluation.service.impl;

import cn.caoler.evaluation.converter.EvaluateInfo2EvaluateInfoDTOConverter;
import cn.caoler.evaluation.dataobject.EvaluateInfo;
import cn.caoler.evaluation.repository.CourseRepository;
import cn.caoler.evaluation.repository.EvaluateInfoRepository;
import cn.caoler.evaluation.repository.UserRepository;
import cn.caoler.evaluation.dto.EvaluateInfoDTO;
import cn.caoler.evaluation.service.EvaluateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.caoler.evaluation.converter.EvaluateInfo2EvaluateInfoDTOConverter.convert;

/**
 *  评教记录
 * Created by caole on 2019/4/16 12:09
 */
@Service
@Transactional
public class EvaluateInfoServiceImpl implements EvaluateInfoService {

    @Autowired
    private EvaluateInfoRepository evaluateInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public EvaluateInfo save(EvaluateInfo evaluateInfo) {

        EvaluateInfo info = evaluateInfoRepository.save(evaluateInfo);

        return info;
    }

    @Override
    public Page<EvaluateInfoDTO> findEvaluateInfosByEvaluatorId(Long teacherId, Long evaluatorId, Pageable pageable) {
        Page<EvaluateInfo> evaluateInfoPage =evaluateInfoRepository.findEvaluateInfosByEvaluatorId(teacherId,evaluatorId,pageable);
        List<EvaluateInfoDTO> evaluateInfoDTOList = EvaluateInfo2EvaluateInfoDTOConverter.convert(evaluateInfoPage.getContent());
        for (EvaluateInfoDTO e : evaluateInfoDTOList){
            e.setEvaluatorName(userRepository.findByUserId(e.getEvaluatorId()).getUsername());
            e.setTeacherName(userRepository.findByUserId(e.getTeacherId()).getUsername());
            e.setCourseName(courseRepository.findByCourseId(e.getCourseId()).getCourseName());
        }
        Page<EvaluateInfoDTO> evaluateInfoDTOPage = new PageImpl<EvaluateInfoDTO>(evaluateInfoDTOList,pageable,evaluateInfoPage.getTotalElements());
        return evaluateInfoDTOPage;
    }

    @Override
    public Integer countForPageByEvaluatorId(Long teacherId, Long evaluatorId) {
        Integer count =  new Long(evaluateInfoRepository.countForPageByEvaluatorId(teacherId,evaluatorId)).intValue();
        return count;
    }

    @Override
    public List<EvaluateInfoDTO> findEvaluateInfosByTeacherId(Long teacherId) {
        List<EvaluateInfo> evaluateInfoList = evaluateInfoRepository.findEvaluateInfosByTeacherId(teacherId);
        return EvaluateInfo2EvaluateInfoDTOConverter.convert(evaluateInfoList);
    }
}
