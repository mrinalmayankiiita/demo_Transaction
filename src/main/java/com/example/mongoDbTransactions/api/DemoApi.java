package com.example.mongoDbTransactions.api;


import com.example.mongoDbTransactions.bean.Employee;
import com.example.mongoDbTransactions.dto.TempListDto;
import com.example.mongoDbTransactions.service.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApi {
    @Autowired
    private TempService tempService;


    @PostMapping("/save")
    public void saveDemo(@RequestBody Employee employee){
        tempService.save(employee);
    }

    @PostMapping("/save/listBeans")
    public void saveList(@RequestBody TempListDto tempListDto){
        tempService.saveBeansExample1(tempListDto);
    }

    @PostMapping("/save/employeeAndPlayer")
    public void saveList(){
        tempService.savePlayerAndEmployee();
    }

    @PostMapping("/error")
    public Exception error(Exception exception){
        return exception;
    }
}
