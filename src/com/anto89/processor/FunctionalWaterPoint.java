/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anto89.processor;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Hendranto.Nugroho
 */
public class FunctionalWaterPoint extends Processor {

    int functionals;
    int nonfunctionals;
    List<String> waterPoints = new ArrayList<>();

    public FunctionalWaterPoint() {
        nonfunctionals = 0;
        functionals = 0;
    }

    @Override
    public boolean run(JSONObject item) {

        // valid data only
        if (!item.containsKey("water_functioning") || !item.containsKey("communities_villages")) {
            return false;
        }
        
        // fuctional or nonfunctional data
        if ("yes".equalsIgnoreCase(String.valueOf(item.get("water_functioning")))) {
            functionals++;
            waterPoints.add(String.valueOf(item.get("communities_villages")));
        }
        else {
            nonfunctionals++;
        }
        return true;
    }

    @Override
    public JSONObject getReturnJson() {
        // convert to json
       returnJson.put("number_functionals", functionals);
       returnJson.put("number_nonfunctionals", nonfunctionals);
       return returnJson;
    }
    
    

}
