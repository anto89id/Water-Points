/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anto89.processor;

import com.anto89.model.RankCommunityWaterPointModel;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Hendranto.Nugroho
 */
public class RankCommunityWaterPoint extends Processor {

    protected Map<String, RankCommunityWaterPointModel> ranks = new HashMap<>();

    @Override
    public boolean run(JSONObject item) {

        // valid data only
        if (!item.containsKey("respondent") || !item.containsKey("communities_villages")) {
            return false;
        }
        if (!"community".equalsIgnoreCase(String.valueOf(item.get("respondent")))) {
            return false;
        }

        RankCommunityWaterPointModel model;
        String place = String.valueOf(item.get("communities_villages"));
        if (ranks.containsKey(place)) {
            model = ranks.get(place);  // data previous exists
        } else {
            model = new RankCommunityWaterPointModel(place); // new data

        }
        if ("yes".equalsIgnoreCase(String.valueOf(item.get("water_functioning")))) {
            model.addYes(1);  // add functional
        } else {
            model.addNo(1); // add non functional
        }

        ranks.put(place, model);
        return true;
    }

    @Override
    public JSONObject getReturnJson() {
        SortedMap<String, RankCommunityWaterPointModel> sorted = new TreeMap<>();
        
        // to allow sorted by biggest number of water points per area
        int biggest=0;
        for (Map.Entry entry : ranks.entrySet()) {
            RankCommunityWaterPointModel model = (RankCommunityWaterPointModel) entry.getValue();
            if (model.getTotal() > biggest) {
                biggest = model.getTotal();
            }
        }
        
        // rank process
        for (Map.Entry entry : ranks.entrySet()) {
            RankCommunityWaterPointModel model = (RankCommunityWaterPointModel) entry.getValue();
            int rank = (int)model.getRank()*100;
            String key = String.format("%04d", rank) + "|" + String.format("%03d", biggest-model.getTotal()) + "|" + entry.getKey();
            sorted.put(key, model);
        }

        // result of rank process
        JSONArray array = new JSONArray();
        int rank = 1;
        for (Map.Entry entry : sorted.entrySet()) {
            JSONObject item = new JSONObject();
            
            item.put("place", ((RankCommunityWaterPointModel) entry.getValue()).getPlace());
            item.put("broken%", ((RankCommunityWaterPointModel) entry.getValue()).getRank());
            item.put("total", ((RankCommunityWaterPointModel) entry.getValue()).getTotal());
            item.put("broken", ((RankCommunityWaterPointModel) entry.getValue()).getNo());
            item.put("rank", rank);
            //item.put("key", entry.getKey());
            rank++;
            array.add(item);
        }
        returnJson.put("community_ranking", array);
        return returnJson;
    }

}
