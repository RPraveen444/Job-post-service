package com.revature.jobpostservice.service;

import com.revature.jobpostservice.model.Summary;
import com.revature.jobpostservice.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryService {

    @Autowired
    private SummaryRepository summaryRepository;

    public Summary createSummary(Summary summary) {
        return summaryRepository.save(summary);
    }

    public List<Summary> getAllSummaries() {
        return summaryRepository.findAll();
    }

    public Summary getSummaryByUserId(Long userId) {
        return summaryRepository.findAll().stream()
                .filter(summary -> summary.getUser().getUserId() == userId)
                .findFirst()
                .orElse(null);
    }
}