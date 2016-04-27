/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anto89.processor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Hendranto.Nugroho
 * parent of data processor
 */
public class Processor {
    
    protected JSONObject returnJson;
    
    public boolean run(JSONObject data) {
        return false;
    }
    
    public JSONObject getReturnJson() {
        return returnJson;
    }

    public void setReturnJson(JSONObject returnJson) {
        this.returnJson = returnJson;
    }
    
}
