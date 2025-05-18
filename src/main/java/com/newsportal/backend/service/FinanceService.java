package com.newsportal.backend.service;

import com.newsportal.backend.entity.Finance;
import com.newsportal.backend.repository.FinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceService {

    private final FinanceRepository financeRepository;

    @Autowired
    public FinanceService(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    public List<Finance> getAllFinanceData() {
        return financeRepository.findAll();
    }

    public Finance saveFinance(Finance finance) {
        return financeRepository.save(finance);
    }

    public void saveAllFinance(List<Finance> financeList) {
        financeRepository.saveAll(financeList);
    }
}