package cn.caoler.evaluation.converter;

import cn.caoler.evaluation.dataobject.Grade;
import cn.caoler.evaluation.dto.GradeDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by caole on 2019/4/18 21:02
 */
public class Grade2GradeDTO {

    public static GradeDTO convert(Grade grade){

        GradeDTO gradeDTO = new GradeDTO();
        BeanUtils.copyProperties(grade,gradeDTO);
        return gradeDTO;
    }

    public static List<GradeDTO> convert(List<Grade> gradeList){
        return gradeList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
