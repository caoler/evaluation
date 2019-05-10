package cn.caoler.evaluation.converter;

import cn.caoler.evaluation.dto.IndexDTO;
import cn.caoler.evaluation.tree.IndexNode;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by caole on 2019/4/15 16:43
 */
public class IndexDTO2IndexNodeConverter {

    public static IndexNode convert(IndexDTO indexDTO){

        IndexNode indexNode = new IndexNode();
        BeanUtils.copyProperties(indexDTO,indexNode);
        return indexNode;
    }

    public static List<IndexNode> convert(List<IndexDTO> lectureList){
        return lectureList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
