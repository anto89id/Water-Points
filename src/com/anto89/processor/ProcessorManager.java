/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anto89.processor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Hendranto.Nugroho
 */
public class ProcessorManager {

    List<Processor> list = new ArrayList<>();
    JSONObject result = new JSONObject();

    public ProcessorManager() {

    }

    // register processor
    public void register(Processor processor) {
        list.add(processor);
    }

    // main process
    public boolean run(String urlString) {

        JSONArray json = null;
        try {
            URL url = new URL(urlString);
            String jsonResult = IOUtils.toString(url);
            json = (JSONArray) JSONValue.parseWithException(jsonResult);

        } catch (MalformedURLException ex) {
            Logger.getLogger(ProcessorManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException | ParseException ex) {
            Logger.getLogger(ProcessorManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        // data valid only
        if (json == null || json.isEmpty()) {
            return false;
        }

        // setup result
        result.put("url_data", urlString);
        result.put("process_time", new Date());
        for (Processor p : list) {
            p.setReturnJson(result);
        }

        // main processing
        for (int i = 0; i < json.size(); i++) {
            JSONObject data = (JSONObject) json.get(i);
            for (Processor p : list) {
                p.run(data);
            }
        }

        // return result
        for (Processor p : list) {
            p.getReturnJson();
        }
        return true;
    }

    // get result
    public JSONObject getResult() {
        return result;
    }

}
