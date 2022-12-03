package com.bharat.food.ordering.system.payment.service.dataacess.credithistory.adapter;
/*
 * @author bharat.verma
 * @created Thursday, 26 January 2023
 */

import com.bharat.food.orderin.system.payment.service.domain.ports.output.repository.CreditHistoryRepository;
import com.bharat.food.ordering.system.domain.vo.CustomerId;
import com.bharat.food.ordering.system.payment.service.dataacess.credithistory.entity.CreditHistoryEntity;
import com.bharat.food.ordering.system.payment.service.dataacess.credithistory.mapper.CreditHistoryDataAccessMapper;
import com.bharat.food.ordering.system.payment.service.dataacess.credithistory.repository.CreditHistoryJpaRepository;
import com.bharat.food.ordering.system.payment.service.domain.entity.CreditHistory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreditHistoryRepositoryImpl implements CreditHistoryRepository {

    private final CreditHistoryJpaRepository creditHistoryJpaRepository;
    private final CreditHistoryDataAccessMapper creditHistoryDataAccessMapper;

    public CreditHistoryRepositoryImpl(CreditHistoryJpaRepository creditHistoryJpaRepository,
                                       CreditHistoryDataAccessMapper creditHistoryDataAccessMapper) {
        this.creditHistoryJpaRepository = creditHistoryJpaRepository;
        this.creditHistoryDataAccessMapper = creditHistoryDataAccessMapper;
    }

    @Override
    public CreditHistory save(CreditHistory creditHistory) {
        return creditHistoryDataAccessMapper.creditHistoryEntityToCreditHistory(creditHistoryJpaRepository
                .save(creditHistoryDataAccessMapper.creditHistoryToCreditHistoryEntity(creditHistory)));
    }

    @Override
    public Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId) {
        Optional<List<CreditHistoryEntity>> creditHistory =
                creditHistoryJpaRepository.findByCustomerId(customerId.getValue());
        return creditHistory
                .map(creditHistoryList ->
                        creditHistoryList.stream()
                                .map(creditHistoryDataAccessMapper::creditHistoryEntityToCreditHistory)
                                .collect(Collectors.toList()));
    }
}