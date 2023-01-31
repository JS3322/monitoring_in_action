package com.example.management_data.service.scrap;

import com.example.management_data.entity.ScrapEntityExample;

import java.util.ArrayList;
import java.util.List;


public interface ScrapService {

    List<ScrapEntityExample> FindAllProject3db();

    void saveProject3db(ScrapEntityExample project3Entity);

    void saveAllProject3db(ArrayList<ScrapEntityExample> project3Entity);





}
