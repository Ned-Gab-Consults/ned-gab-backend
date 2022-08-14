package com.abroad.scholarship.service;


import com.abroad.scholarship.models.BlackListedToken;


public interface BlackListService {

    BlackListedToken blackListToken(String  token);

//    BlackListedToken getToken(String  token);

    boolean tokenExist(String token);
}
