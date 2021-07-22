package com.example.mongoDbTransactions.service.repository;

import com.example.mongoDbTransactions.bean.TempBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DemoRepo {
    @Autowired
    private MongoTemplate mongoTemplate;

    public  void save(TempBean tempBean){
        mongoTemplate.save(tempBean);
    }

}
