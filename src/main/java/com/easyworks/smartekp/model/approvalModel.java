package com.easyworks.smartekp.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;
    
@Data
@Alias("approvalModel")
public class approvalModel {
    private Long id;
    private String name;
    private String country;
    private Long population;

    public approvalModel() {
    }

    public approvalModel(String name, String country, Long population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }
    

}
