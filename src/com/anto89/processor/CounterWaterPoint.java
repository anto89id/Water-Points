/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anto89.processor;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author Hendranto Nugroho
 */
public class CounterWaterPoint extends Processor {

    int counter;
    List<String> waterPoints = new ArrayList<>();

    public CounterWaterPoint() {
        counter = 0;
    }
    
    @Override
    public boolean run(JSONObject item) {

        // valid data only
        if (!item.containsKey("water_functioning") || !item.containsKey("communities_villages")) {
            return false;
        }
        
        // just add the counter
        counter++;
        waterPoints.add(String.valueOf(item.get("communities_villages")));

        return true;
    }

    @Override
    public JSONObject getReturnJson() {
        returnJson.put("counter_water_points", counter);
        return returnJson;
    }
}
