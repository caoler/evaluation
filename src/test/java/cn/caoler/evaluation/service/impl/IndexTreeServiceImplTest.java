package cn.caoler.evaluation.service.impl;

import cn.caoler.evaluation.converter.IndexDTO2IndexNodeConverter;
import cn.caoler.evaluation.dto.IndexDTO;
import cn.caoler.evaluation.service.IndexService;
import cn.caoler.evaluation.tree.IndexNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static cn.caoler.evaluation.converter.IndexDTO2IndexNodeConverter.convert;

/**
 * Created by caole on 2019/4/16 16:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IndexTreeServiceImplTest {

    @Autowired
    private IndexTreeServiceImpl indexTreeService;

    @Autowired
    private IndexService indexService;


    @Test
    public void getParentNode() {
        IndexDTO indexDTO = indexService.findByIndexId(18L);
        List<IndexNode> indexNodeList =indexTreeService.getParentNode(IndexDTO2IndexNodeConverter.convert(indexDTO));
        Assert.assertNotNull(indexNodeList);
    }

    @Test
    public void getLeafNode() {
        IndexDTO indexDTO =indexService.findByIndexId(27L);
        List<IndexNode> result = indexTreeService.getLeafNode(IndexDTO2IndexNodeConverter.convert(indexDTO));
        Assert.assertNotNull(result);

    }
}