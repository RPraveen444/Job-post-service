package com.revature.jobpostservice.service;

import com.revature.jobpostservice.model.Language;
import com.revature.jobpostservice.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public List<Language> getLanguagesByUserId(Long userId) {
        return languageRepository.findAll().stream()
                .filter(language -> language.getUser().getUserId() == userId)
                .toList();
    }
}
