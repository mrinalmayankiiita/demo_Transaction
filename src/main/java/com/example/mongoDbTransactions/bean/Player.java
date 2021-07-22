package com.example.mongoDbTransactions.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    private String playerId;
    private String name;
    private Long age;
}
