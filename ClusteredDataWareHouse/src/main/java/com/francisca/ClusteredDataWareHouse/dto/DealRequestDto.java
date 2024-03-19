package com.francisca.ClusteredDataWareHouse.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DealRequestDto {


    @NotNull(message = "DealID cannot be blank")
    private String dealId;

    @Pattern(regexp="^[A-Z]{3}$",message = "Invalid Input")
    @NotNull(message = "orderingCurrency cannot be blank")
    private String orderingCurrency;

   @Pattern(regexp="^[A-Z]{3}$",message = "Invalid Input")
    @NotNull(message = "orderingCurrency cannot be blank")
    private String convertedCurrency;


    @NotNull(message = "amount cannot be blank")
    @Pattern(regexp="^[0-9]+$", message = "Invalid Input")
    private BigDecimal amount;


    private LocalDateTime dealTimeStamp;

}

