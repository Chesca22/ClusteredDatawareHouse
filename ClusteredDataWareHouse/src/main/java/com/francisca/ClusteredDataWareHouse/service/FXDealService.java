package com.francisca.ClusteredDataWareHouse.service;

import com.francisca.ClusteredDataWareHouse.dto.DealApiResponse;
import com.francisca.ClusteredDataWareHouse.dto.DealDetailsDto;
import com.francisca.ClusteredDataWareHouse.dto.DealRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FXDealService {
    ResponseEntity<?> saveFxDealDetails(DealRequestDto dto);

    DealApiResponse<List<DealDetailsDto>> getAllFXDeals();

    DealApiResponse<DealDetailsDto> getDealById(String id);
}
