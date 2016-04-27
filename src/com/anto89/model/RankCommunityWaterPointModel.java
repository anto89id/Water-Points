/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anto89.model;

/**
 *
 * @author Hendranto Nugroho
 * @description model for calculate rank community waterpoint
 */
public class RankCommunityWaterPointModel {

    String place;
    int total;
    float rank;
    int yes;
    int no;

    public RankCommunityWaterPointModel(String place) {
        this.place = place;
        total = 0;
        rank = 0;
        yes = 0;
        no = 0;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public int getYes() {
        return yes;
    }

    public void addYes(int yes) {
        this.yes += yes;
        this.total++;
        this.rank = (float)(this.no * 100 / total) ;
    }

    public int getNo() {
        return no;
    }

    public void addNo(int no) {
        this.no += no;
        this.total++;
        this.rank = (float)(this.no * 100 / total) ;
    }

}
