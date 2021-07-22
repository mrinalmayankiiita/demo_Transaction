package com.example.mongoDbTransactions.service;


import com.example.mongoDbTransactions.bean.TempBean;
import com.example.mongoDbTransactions.dto.TempListDto;
import com.example.mongoDbTransactions.service.repository.DemoRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class TempService {
    @Autowired
    private DemoRepo demoRepo;

    public void save(TempBean tempBean) {
        demoRepo.save(tempBean);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public TempListDto saveBeansExample1(TempListDto tempBeanListDto){
        demoRepo.save(tempBeanListDto.getTempBeanList().get(0));
        log.info("1st bean added");
        demoRepo.save(tempBeanListDto.getTempBeanList().get(1));
        log.info("2nd bean added");
        if(true){
            throw  new RuntimeException("Error in Transaction");
        }
        demoRepo.save(tempBeanListDto.getTempBeanList().get(2));
        log.info("3nd bean added");
        return  tempBeanListDto;
    }
}
