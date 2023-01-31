package com.example.management_data.service.scrap;

import com.example.management_data.entity.ScrapEntityExample;
import com.example.management_data.repository.ScrapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScrapServiceImpl implements ScrapService {

    private ScrapRepository scrapRepository;

    @Autowired
    public ScrapServiceImpl(ScrapRepository project3Repository) {
        this.scrapRepository = project3Repository;
    }
    //모두 조회
    @Override
    public List<ScrapEntityExample> FindAllProject3db() {
        return this.scrapRepository.findAll();
    }

    //저장
    @Override
    public void saveProject3db(ScrapEntityExample project3Entity) {
         this.scrapRepository.save(project3Entity);
    }

    //전체 저장
    @Override
    public void saveAllProject3db(ArrayList<ScrapEntityExample> project3Entity) {
        this.scrapRepository.saveAll(project3Entity);
    }

}
