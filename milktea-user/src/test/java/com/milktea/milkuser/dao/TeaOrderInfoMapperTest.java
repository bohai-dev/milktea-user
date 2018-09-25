package com.milktea.milkuser.dao;

import com.milktea.milkteauser.Application;
import com.milktea.milkteauser.dao.TeaOrderInfoMapper;
import com.milktea.milkteauser.domain.TeaOrderInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Cteated by cxy on 2018/9/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TeaOrderInfoMapperTest {

    @Autowired
    TeaOrderInfoMapper teaOrderInfoMapper;

    @Test
    public void selectByOrderNo(){
        TeaOrderInfo teaOrderInfo=teaOrderInfoMapper.selectByPrimaryKey("20180822_A_837");
        Assert.assertNotNull(teaOrderInfo);

    }
}
