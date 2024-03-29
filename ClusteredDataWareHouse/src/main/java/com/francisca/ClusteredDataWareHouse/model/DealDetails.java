package com.francisca.ClusteredDataWareHouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "deal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DealDetails implements Serializable {
    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long Id;

    @NotNull
    private String dealId;

    @Pattern(regexp="^[A-Za-z]{3}$",message = "Invalid Input")
    private String orderingCurrency;

   @Pattern(regexp="^[A-Za-z]{3}$",message = "Invalid Input")
    private String convertedCurrency;

    @NotNull(message = "amount cannot be blank")
    private BigDecimal amount;

    @CreationTimestamp
    private LocalDateTime dealTimeStamp;
}