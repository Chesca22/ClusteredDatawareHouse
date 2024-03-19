package com.francisca.ClusteredDataWareHouse.controller;

import com.francisca.ClusteredDataWareHouse.dto.DealApiResponse;
import com.francisca.ClusteredDataWareHouse.dto.DealRequestDto;
import com.francisca.ClusteredDataWareHouse.exception.DealAlreadyExistException;
import com.francisca.ClusteredDataWareHouse.service.FXDealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deals")
@Slf4j
public class FXDealController {
private final FXDealService dealService;


@PostMapping
public ResponseEntity<?> saveDealDetails(@RequestBody DealRequestDto dto) {

        log.info("Deal details saved in the successfully");
        return new ResponseEntity<>( dealService.saveFxDealDetails(dto), HttpStatus.CREATED);

//        log.error("Error occurred while saving deal details: " + e.getMessage());
//    throw new DealAlreadyExistException("Deal with Id number: " +dto.getDealId() + " already exist");
//
}

    @GetMapping
    public ResponseEntity<?> getAllDeals() {
        try {
            log.info("All deals retrieved successfully");
            return new ResponseEntity<>(dealService.getAllFXDeals(), OK);
        } catch (Exception e) {
            log.error("Error occurred while retrieving all deals: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve all deals");
        }
    }

    @GetMapping("/{deal-id}")
    public ResponseEntity<?> getDealById(@PathVariable(value="deal-id") String id) {
        try {
            log.info("Deal retrieved successfully");
            return new ResponseEntity<>(dealService.getDealById(id), OK);
        } catch (Exception e) {
            log.error("Error occurred while retrieving deal by id: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to retrieve deal by id");
        }
    }
}