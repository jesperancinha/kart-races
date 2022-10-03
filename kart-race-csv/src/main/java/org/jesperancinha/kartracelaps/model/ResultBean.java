package org.jesperancinha.kartracelaps.model;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class ResultBean {

    @CsvBindByName
    private String kart;

    @CsvBindByName
    private String passingtime;


    public ResultBean(String kart, String passingtime) {
        this.kart = kart;
        this.passingtime = passingtime;
    }

    public String getKart() {
        return kart;
    }

    public String getPassingtime() {
        return passingtime;
    }
}
