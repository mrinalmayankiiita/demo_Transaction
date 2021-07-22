package com.example.mongoDbTransactions.dto;

import com.example.mongoDbTransactions.bean.TempBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempListDto {
    @NonNull
    private List<TempBean> tempBeanList;
}
