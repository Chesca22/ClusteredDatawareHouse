package com.francisca.ClusteredDataWareHouse.controller;


import com.francisca.ClusteredDataWareHouse.dto.DealApiResponse;
import com.francisca.ClusteredDataWareHouse.dto.DealDetailsDto;
import com.francisca.ClusteredDataWareHouse.dto.DealRequestDto;
import com.francisca.ClusteredDataWareHouse.model.DealDetails;
import com.francisca.ClusteredDataWareHouse.service.FXDealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
public class FXDealControllerTest {

    @Mock
    private FXDealService dealService;

    @InjectMocks
    private FXDealController fxDealController;
    LocalDateTime localDateTime = LocalDateTime.of(2024, 03, 24, 1, 34, 19);
  //  @Test
//    public void saveDealDetails_Success() {
//
//        DealRequestDto dto = new DealRequestDto("234", "NGN", "USD", BigDecimal.valueOf(340),localDateTime );
//
//
//        when(dealService.saveFxDealDetails(dto)).thenReturn(new ResponseEntity<>("",("FXDeal details with Id number" +dto.getDealId() + "saved successfully, Time: "  + localDateTime, CREATED), CREATED);
//
//
//        ResponseEntity<?> response = fxDealController.saveDealDetails(dto);
//
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//
//    }

    @Test
    public void saveDealDetails_Exception() {

        DealRequestDto dto = new DealRequestDto("234", "NGN", "USD", BigDecimal.valueOf(340),localDateTime );


        when(dealService.saveFxDealDetails(dto)).thenThrow(RuntimeException.class);


        ResponseEntity<?> response = fxDealController.saveDealDetails(dto);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }



//    @Test
//    public void getAllDeals_Success() {
//        // Given
//        List<DealDetailsDto> deals = Arrays.asList("234", "NGN", "USD", BigDecimal.valueOf(340),localDateTime );
//
//
//        when(dealService.getAllFXDeals()).thenReturn(deals);
//
//
//        ResponseEntity<?> response = fxDealController.getAllDeals();
//
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//    }

    @Test
    public void getAllDeals_Exception() {

        when(dealService.getAllFXDeals()).thenThrow(RuntimeException.class);


        ResponseEntity<?> response = fxDealController.getAllDeals();


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

//    @Test
//    public void getDealById_Success() {
//
//        String dealId = "123";
//
//
//        when(dealService.getDealById(dealId)).thenReturn("SUCCESS", localDateTime, ("234", "USD", "NGN",  BigDecimal.valueOf(340),localDateTime);
//
//
//        ResponseEntity<?> response = fxDealController.getDealById(dealId);
//
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//    }

    @Test
    public void getDealById_Exception() {

        String dealId = "123";


        when(dealService.getDealById(dealId)).thenThrow(RuntimeException.class);


        ResponseEntity<?> response = fxDealController.getDealById(dealId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }
}
