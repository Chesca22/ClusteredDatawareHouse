package com.francisca.ClusteredDataWareHouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder

public class DealDetailsDto {
    private String dealId;
    private String orderingCurrency;
    private String convertedCurrency;
    private BigDecimal amount;
    private LocalDateTime dealTimeStamp;

}
