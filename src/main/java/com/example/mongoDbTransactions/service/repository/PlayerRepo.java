package com.example.mongoDbTransactions.service.repository;

import com.example.mongoDbTransactions.bean.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepo {
    @Autowired
    private MongoTemplate mongoTemplate;

    public  void save(Player player){
        mongoTemplate.save(player);
    }
}
