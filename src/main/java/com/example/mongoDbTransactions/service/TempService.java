package com.example.mongoDbTransactions.service;


import com.example.mongoDbTransactions.bean.Employee;
import com.example.mongoDbTransactions.bean.Player;
import com.example.mongoDbTransactions.dto.TempListDto;
import com.example.mongoDbTransactions.service.repository.DemoRepo;
import com.example.mongoDbTransactions.service.repository.PlayerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class TempService {
    @Autowired
    private DemoRepo demoRepo;

    @Autowired
    private PlayerRepo playerRepo;

    public void save(Employee employee) {
        demoRepo.save(employee);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public TempListDto saveBeansExample1(TempListDto tempBeanListDto){
        demoRepo.save(tempBeanListDto.getEmployeeList().get(0));
        log.info("1st bean added");
        demoRepo.save(tempBeanListDto.getEmployeeList().get(1));
        log.info("2nd bean added");
        if(true){
            throw  new RuntimeException("Error in Transaction");
        }
        demoRepo.save(tempBeanListDto.getEmployeeList().get(2));
        log.info("3nd bean added");
        return  tempBeanListDto;
    }

    @Transactional                               // Rollback happening in both the collections
    public void savePlayerAndEmployee(){
        demoRepo.save(new Employee("001","Mayank"));
        log.info("2nd Employee has been added");
        playerRepo.save(new Player("001","Rishab Pant", 35L));
        log.info("2nd Player has been added");
        if(true){
            throw new RuntimeException("Error while saving employee and Player");
        }
    }

}
