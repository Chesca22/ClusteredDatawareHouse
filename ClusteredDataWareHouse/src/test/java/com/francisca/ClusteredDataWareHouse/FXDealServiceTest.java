package com.francisca.ClusteredDataWareHouse;

//public class  {
//}
import com.francisca.ClusteredDataWareHouse.dto.DealApiResponse;
import com.francisca.ClusteredDataWareHouse.dto.DealRequestDto;
import com.francisca.ClusteredDataWareHouse.exception.DealAlreadyExistException;
import com.francisca.ClusteredDataWareHouse.exception.DealNotFoundException;
import com.francisca.ClusteredDataWareHouse.model.DealDetails;
import com.francisca.ClusteredDataWareHouse.repository.DataRepository;
import com.francisca.ClusteredDataWareHouse.serviceImpl.FXDealServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
public class FXDealServiceTest {
    @InjectMocks
    private FXDealServiceImpl dealService;
    @Mock
    private DataRepository dealRepository;
    @Mock
    private DealDetails deals;
    @Mock
    DealApiResponse res;
    LocalDateTime localDateTime = LocalDateTime.of(2024, 03, 24, 1, 34, 19);

    @BeforeEach
    void init(){

        deals = new DealDetails("234","USD","NGN", BigDecimal.valueOf(340), localDateTime);
        when(dealRepository.save(deals)).thenReturn(deals);
    }

    @Test
    @DisplayName("should save the deal details")
    void saveDetails()  {

        var dto = new DealRequestDto("675",dealService.validateCurrency("USD"), dealService.validateCurrency("NGN"), dealService.validateDealAmount(BigDecimal.valueOf(340)),localDateTime);
        when(dealRepository.findByDealId(deals.getDealId())).thenReturn(Optional.empty());
        var actual = dealService.saveFxDealDetails(dto);
        assertEquals("201 CREATED", actual.getStatusCode().toString());

    }

    @Test
    void testToFindDealDetailsById(){
        when(dealRepository.findByDealId(deals.getDealId())).thenReturn(Optional.of(deals));
        var actual = dealService.getDealById("234");
        assertEquals("success", actual.getMessage());
    }


    @Test
    void shouldThrowDealAlreadyExistException() {
        DealRequestDto dto = new DealRequestDto("675","USD", "NGN", BigDecimal.valueOf(340), localDateTime);
        when(dealRepository.findByDealId(deals.getDealId())).thenReturn(Optional.of(deals));
        assertThrows(DealAlreadyExistException.class, () -> dealService.saveFxDealDetails(dto));

    }

    @Test
    void shouldThrowDealNotFoundException(){
        when(dealRepository.findByDealId(deals.getDealId())).thenReturn(Optional.of(deals));
        assertThrows(DealNotFoundException.class, () -> dealService.getDealById("230"));
    }
}