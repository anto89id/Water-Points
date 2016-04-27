/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anto89.processor;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Hendranto.Nugroho
 */
public class CommunityWaterPoint extends Processor {

    protected Map<String, Integer> entries = new HashMap<>();

    @Override
    public boolean run(JSONObject data) {

        // we take valid data only
        if (!data.containsKey("respondent") || !data.containsKey("communities_villages")) {
            return false;
        }

        // we take community data only
        if (!"community".equalsIgnoreCase(String.valueOf(data.get("respondent")))) {
            return false;
        }

        int counter = 1;
        String key = String.valueOf(data.get("communities_villages"));
        if (entries.containsKey(key)) {  // if the previous data exists
            counter = entries.get(key) + 1;
        }
        entries.put(key, counter);
        return true;
    }

    @Override
    public JSONObject getReturnJson() {
        
        // change the data to json...
        JSONArray result = new JSONArray();
        for (Map.Entry entry : entries.entrySet()) {
            JSONObject item = new JSONObject();
            item.put(entry.getKey(), entry.getValue());
            result.add(item);
        }
        returnJson.put("number_water_points", result);
        return returnJson;
    }

}
