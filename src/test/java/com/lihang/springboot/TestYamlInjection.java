package com.lihang.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestYamlInjection {
    @Autowired
    YamlInjection yamlInjection;

    @Test
    public void testYamlInjection() {
        String[] infos = {"string_1", "string_2", "string_3", "string_4"};
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("listInfo_1");
        arrayList.add("listInfo_2");
        arrayList.add("listInfo_3");
        arrayList.add("listInfo_4");
        HashMap<String, String> map = new HashMap<>();
        map.put("key_1", "val_1");
        map.put("key_2", "val_2");
        map.put("key_3", "val_3");
        map.put("key_4", "val_4");
        Assert.assertEquals(yamlInjection.getInfo(), "lihang");
        Assert.assertArrayEquals(yamlInjection.getInfos(), infos);
        Assert.assertEquals(yamlInjection.getList_infos(), arrayList);
        Assert.assertEquals(yamlInjection.getMap_infos(), map);
    }
}
