package cn.caoler.evaluation.converter;

import cn.caoler.evaluation.dataobject.Lecture;
import cn.caoler.evaluation.dto.LectureDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by caole on 2019/4/10 10:54
 */
public class Lecture2LectureDTOConverter {

    public static LectureDTO convert(Lecture lecture){

        LectureDTO lectureDTO = new LectureDTO();
        BeanUtils.copyProperties(lecture,lectureDTO);
        return lectureDTO;
    }

    public static List<LectureDTO> convert(List<Lecture> lectureList){
        return lectureList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
