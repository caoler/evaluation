package cn.caoler.evaluation.converter;

import cn.caoler.evaluation.dataobject.EvaluateInfo;
import cn.caoler.evaluation.dto.EvaluateInfoDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  评教记录  转换器
 * Created by caole on 2019/4/16 14:41
 */
public class EvaluateInfo2EvaluateInfoDTOConverter {

    public static EvaluateInfoDTO convert(EvaluateInfo evaluateInfo){

        EvaluateInfoDTO evaluateInfoDTO = new EvaluateInfoDTO();
        BeanUtils.copyProperties(evaluateInfo,evaluateInfoDTO);
        return evaluateInfoDTO;
    }

    public static List<EvaluateInfoDTO> convert(List<EvaluateInfo> evaluateInfoList){
        return evaluateInfoList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
