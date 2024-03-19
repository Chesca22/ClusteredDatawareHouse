package com.francisca.ClusteredDataWareHouse.serviceImpl;

import com.francisca.ClusteredDataWareHouse.dto.DealApiResponse;
import com.francisca.ClusteredDataWareHouse.dto.DealDetailsDto;
import com.francisca.ClusteredDataWareHouse.dto.DealRequestDto;
import com.francisca.ClusteredDataWareHouse.exception.DealAlreadyExistException;
import com.francisca.ClusteredDataWareHouse.exception.DealNotFoundException;
import com.francisca.ClusteredDataWareHouse.exception.InvalidCurrencyException;
import com.francisca.ClusteredDataWareHouse.model.DealDetails;
import com.francisca.ClusteredDataWareHouse.repository.DataRepository;
import com.francisca.ClusteredDataWareHouse.service.FXDealService;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class FXDealServiceImpl implements FXDealService {

    private final DataRepository dealRepository;
@Override
    public ResponseEntity<?> saveFxDealDetails(DealRequestDto dto)  {
        try {
            Optional<DealDetails> existingDealDetails = dealRepository.findByDealId(dto.getDealId());
            if(existingDealDetails.isEmpty()) {
                DealDetails dealDetails = DealDetails.builder()
                        .dealId(dto.getDealId())
                        .orderingCurrency(validateCurrency(dto.getOrderingCurrency()))
                        .convertedCurrency(validateCurrency(dto.getConvertedCurrency()))
                        .amount(validateDealAmount(dto.getAmount()))
                        .build();
                dealRepository.save(dealDetails);
                log.info("Saved deal details");
                return new ResponseEntity<>("FXDeal details with Id number" +dto.getDealId() + "saved successfully, Time: "  + LocalDateTime.now() , CREATED);
            }
            throw new DealAlreadyExistException("Deal with Id number: " +dto.getDealId() + " already exist");
        } catch (Exception e) {
            log.error("Error occurred while saving FX deal details: " + e.getMessage());
            throw e;
        }
    }


    @Override
    public DealApiResponse<List<DealDetailsDto>> getAllFXDeals(){
        List<DealDetails> allDeals = dealRepository.findAll();
        log.info("Retrieving all FX deals details");
        return new DealApiResponse<>("success",LocalDateTime.now(),allDeals.stream().map(this::mapToDealDetailsDto).toList());
    }


    @Override
    public DealApiResponse<DealDetailsDto> getDealById(String id){
        DealDetails dealDetails = dealRepository.findByDealId(id).orElseThrow(() -> new DealNotFoundException("Deal with Id number: " + id + " does not exist"));
        log.info("Retrieving deal with Id no: " + id );
        return new DealApiResponse<>("success",LocalDateTime.now(),mapToDealDetailsDto(dealDetails));
    }



    private DealDetailsDto mapToDealDetailsDto(DealDetails dealDetails){
        return DealDetailsDto.builder()
                .dealId(dealDetails.getDealId())
                .orderingCurrency(dealDetails.getOrderingCurrency())
                .convertedCurrency(dealDetails.getConvertedCurrency())
                .amount(dealDetails.getAmount())
                .dealTimeStamp(dealDetails.getDealTimeStamp())
                .build();
    }


    public BigDecimal validateDealAmount(BigDecimal amount) {
    try {
      //  BigDecimal amount = new BigDecimal(amountString);
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidCurrencyException("Amount cannot be negative: " + amount);
        }
        if (amount.scale() > 2) {
            throw new InvalidCurrencyException("Amount must have at most 2 decimal places: " + amount);
        }
        return amount;
    } catch (NumberFormatException e) {
        throw new InvalidCurrencyException("Invalid amount: " + amount);
    } catch (Exception e) {
        log.error("Error occurred while validating deal amount: " + e.getMessage());
        throw e;
    }
}
    public String validateCurrency(String code) {
        try {
            Currency.getAvailableCurrencies();
            for(Currency currency : Currency.getAvailableCurrencies()) {
                if(currency.getCurrencyCode().equalsIgnoreCase(code)) {
                    return code;
                }
            }
            throw new InvalidCurrencyException("Unknown currency code: " + code);
        } catch (Exception e) {
            log.error("Error occurred while validating currency: " + e.getMessage());
            throw e;
        }
    }

}


