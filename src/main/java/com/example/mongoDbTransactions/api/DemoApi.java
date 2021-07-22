package com.example.mongoDbTransactions.api;


import com.example.mongoDbTransactions.bean.TempBean;
import com.example.mongoDbTransactions.dto.TempListDto;
import com.example.mongoDbTransactions.service.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApi {
    @Autowired
    private TempService tempService;


    @PostMapping("/save")
    public void saveDemo(@RequestBody TempBean tempBean){
        tempService.save(tempBean);
    }

    @PostMapping("/save/listBeans")
    public void saveList(@RequestBody TempListDto tempListDto){
        tempService.saveBeansExample1(tempListDto);
    }

    @PostMapping("/error")
    public Exception error(Exception exception){
        return exception;
    }
}
