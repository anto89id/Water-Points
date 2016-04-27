/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anto89.processor;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Hendranto.Nugroho
 */
public class CounterWaterPointTest {

    @Test
    public void run() {
        CounterWaterPoint point = new CounterWaterPoint();
        
        JSONObject item = new JSONObject();
        item.put("water_functioning", 1);
        item.put("communities_villages", "test_village");
        
        point.run(item);
        
        Assert.assertEquals(1L, (long)point.counter);
        Assert.assertEquals("test_village", point.waterPoints.get(0));
    }    
}
