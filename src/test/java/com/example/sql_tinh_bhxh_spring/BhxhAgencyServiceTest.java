package com.example.sql_tinh_bhxh_spring;

import com.example.sql_tinh_bhxh_spring.entity.BhxhAgencyEntity;
import com.example.sql_tinh_bhxh_spring.repository.BhxhAgencyRepository;
import com.example.sql_tinh_bhxh_spring.service.BhxhAgencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BhxhAgencyServiceTest {

    @Mock
    private BhxhAgencyRepository bhxhAgencyRepository;

    @InjectMocks
    private BhxhAgencyService bhxhAgencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBhxhAgency() {
        // Arrange
        BhxhAgencyEntity agency1 = new BhxhAgencyEntity();
        agency1.setId(1L);
        agency1.setDisplayName("Agency 1");
        agency1.setAddress("Ha Dong");

        BhxhAgencyEntity agency2 = new BhxhAgencyEntity();
        agency2.setId(2L);
        agency2.setDisplayName("Agency 2");
        agency1.setAddress("Ha Noi");

        List<BhxhAgencyEntity> expectedAgencies = Arrays.asList(agency1, agency2);
        when(bhxhAgencyRepository.findAll()).thenReturn(expectedAgencies);

        // Act
        List<BhxhAgencyEntity> actualAgencies = bhxhAgencyService.getAllBhxhAgency();

        // Assert
        assertEquals(expectedAgencies, actualAgencies);
    }
}

