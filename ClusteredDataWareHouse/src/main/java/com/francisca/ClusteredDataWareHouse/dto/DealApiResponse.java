package com.francisca.ClusteredDataWareHouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DealApiResponse <T>{

    private String message;
    private LocalDateTime timeStamp;
    private T data;
}
