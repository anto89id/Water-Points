/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anto89.apps;

import com.anto89.processor.RankCommunityWaterPoint;
import com.anto89.processor.CommunityWaterPoint;
import com.anto89.processor.CounterWaterPoint;
import com.anto89.processor.FunctionalWaterPoint;
import com.anto89.processor.ProcessorManager;
import com.anto89.utils.JsonFormatter;

/**
 *
 * @author Hendranto.Nugroho
 */
public class Statistic {

    private static final String URL_String = "https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json";
    //private static final String URL_String = "file:///D:/Downloads/water_points.json.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create manager
        ProcessorManager manager = new ProcessorManager();

        // register data processors - can add more...
        manager.register(new CounterWaterPoint());
        manager.register(new FunctionalWaterPoint());
        manager.register(new CommunityWaterPoint());
        manager.register(new RankCommunityWaterPoint());

        // run
        if (!manager.run(URL_String)) {
            System.out.println("No Result...");
            return;
        }

        // success no error
        System.out.println("----------------------- JSON Raw -----------------------------");
        System.out.println(manager.getResult());

        System.out.println("\n\n----------------------- JSON Format -----------------------------");
        System.out.println(JsonFormatter.format(manager.getResult()));
    }

}
