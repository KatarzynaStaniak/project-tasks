package com.crud.tasks.config;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void testGetValues() {
        //When
        String appKey = trelloConfig.getTrelloAppKey();
        String trelloUserName = trelloConfig.getTrelloUsername();
        //Then
        Assert.assertEquals("a42b73d686b1c414ca6f6ddc2dfffe00", appKey);
        Assert.assertEquals("kstaniak11@gmail.com", trelloUserName);
    }
}
