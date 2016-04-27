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
public class CommunityWaterPointTest {
    
    @Test
    public void run() {
        CommunityWaterPoint point = new CommunityWaterPoint();
        
        JSONObject item = new JSONObject();
        item.put("respondent", "community");
        item.put("communities_villages", "test_village");
        
        point.run(item);

        Assert.assertEquals("test_village", point.entries.get(0));
        
    }
}
