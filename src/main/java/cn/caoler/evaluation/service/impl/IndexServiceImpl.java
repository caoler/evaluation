package cn.caoler.evaluation.service.impl;

import cn.caoler.evaluation.converter.Index2IndexDTOConverter;
import cn.caoler.evaluation.dataobject.Index;
import cn.caoler.evaluation.exception.EvaluationException;
import cn.caoler.evaluation.repository.IndexRepository;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.caoler.evaluation.dto.IndexDTO;
import cn.caoler.evaluation.enums.ResultEnum;
import cn.caoler.evaluation.service.IndexService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.caoler.evaluation.converter.Index2IndexDTOConverter.convert;

/**
 * Created by caole on 2019/4/8 9:53
 */
@Service
@Transactional
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexRepository indexRepository;

    @Override
    public Page<IndexDTO> findIndexIfIndexNameIsNotNull(String indexName, Pageable pageable) {
        Page<Index> indexPage = indexRepository.findIndexIfIndexNameIsNotNull(indexName,pageable);

        List<IndexDTO> indexDTOList = Index2IndexDTOConverter.convert(indexPage.getContent());

        Page<IndexDTO> indexDTOPage = new PageImpl<IndexDTO>(indexDTOList,pageable,indexPage.getTotalElements());

        return indexDTOPage;
    }

    @Override
    public Integer countIndexForPage(String indexName) {
        return indexRepository.countIndexForPage(indexName);
    }

    @Override
    public IndexDTO findByIndexId(Long indexId) {
        Index index = indexRepository.findByIndexId(indexId);
        if (index == null){
            throw new EvaluationException(ResultEnum.INDEX_NOT_EXIST);
        }

        IndexDTO indexDTO = Index2IndexDTOConverter.convert(index);

        return indexDTO;
    }


    @Override
    public IndexDTO updateIndex(IndexDTO indexDTO) {
        Index index;
        if (indexDTO.getIndexId() != null){
            index = indexRepository.findByIndexId(indexDTO.getIndexId());
        }else {
            throw new EvaluationException(ResultEnum.INDEX_NOT_EXIST);
        }

        /**
         *      属性拷贝，从src到target。  倘若src里面有空值，则不拷贝过去
         */
        BeanUtil.copyProperties(indexDTO,index,true, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        Index index1= indexRepository.save(index);
        return Index2IndexDTOConverter.convert(index1);
    }


    @Override
    public IndexDTO create(IndexDTO indexDTO) {


        Index index = new Index();
        BeanUtils.copyProperties(indexDTO,index);
        Index index1 = indexRepository.save(index);
        return Index2IndexDTOConverter.convert(index1);
    }

    @Override
    public void deleteByIndexId(Long indexId) {
         indexRepository.deleteByIndexId(indexId);
    }


    @Override
    public List<Index> findAll() {
        return indexRepository.findAll();
    }
}
