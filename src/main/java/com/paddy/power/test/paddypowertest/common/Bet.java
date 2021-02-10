package com.paddy.power.test.paddypowertest.common;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Bet {
    @Id
    private String bedId;
    private String timestamp;
    private int selectionId;
    private String selectionName;
    private long stake;
    private long price;
    private String currency;
}