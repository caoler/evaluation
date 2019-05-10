package cn.caoler.evaluation.repository;

import cn.caoler.evaluation.dataobject.Action;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by caole on 2019/3/14 11:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionRepositoryTest {

    @Autowired
    private ActionRepository repository;


    @Test
    public void saveTest(){
        Action action = new Action();
        action.setActionName("用户管理");
        action.setParentId(0L);
        action.setUrl("xxxxxxxxx");

        Action result = repository.save(action);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByActionId() {
        Action result = repository.findByActionId(1L);
        Assert.assertNotNull(result);
    }
}