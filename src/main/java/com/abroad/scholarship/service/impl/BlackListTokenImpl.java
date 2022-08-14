package com.abroad.scholarship.service.impl;

import com.abroad.scholarship.models.BlackListedToken;
import com.abroad.scholarship.repository.BlackListedTokenRepository;
import com.abroad.scholarship.service.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListTokenImpl implements BlackListService {

    private final BlackListedTokenRepository blackListedTokenRepository;

    @Override
    public BlackListedToken blackListToken(String token) {

        BlackListedToken blackListedToken = new BlackListedToken();
        blackListedToken.setToken(token.substring(7));

        return blackListedTokenRepository.save(blackListedToken);
    }

    @Override
    public boolean tokenExist(String token){
        return blackListedTokenRepository.existsByToken(token);
    }
}

