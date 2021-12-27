package com.wbint.service;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.wbint.config.ApplicationProperties;
import com.wbint.config.AsyncConfiguration;
import com.wbint.wbclient.WBClient;
import com.wbint.wbclient.model.userSearch.WBUserSearchResponse;
import com.wbint.wbclient.model.wbToken.WBTokenRequest;
import com.wbint.wbclient.model.wbToken.WBTokenResponse;
import feign.Feign;
import feign.Logger;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import liquibase.repackaged.org.apache.commons.collections4.map.HashedMap;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WBService {

    private final ApplicationProperties applicationProperties;

    private WBClient wbClient;

    private String accesToken;

    private final org.slf4j.Logger log = LoggerFactory.getLogger(AsyncConfiguration.class);

    public WBService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.wbClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new FormEncoder(new GsonEncoder()))
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(WBClient.class))
            .logLevel(Logger.Level.FULL)
            .target(WBClient.class, this.applicationProperties.getUrl());
    }

    public void getWBToken() {
        WBTokenRequest tokenRequest = new WBTokenRequest();
        tokenRequest.setAuthority(applicationProperties.getAuthority());
        tokenRequest.setClient_id(applicationProperties.getClient_id());
        tokenRequest.setResource(applicationProperties.getResource());
        tokenRequest.setTenant(applicationProperties.getTenant());
        tokenRequest.setUsername(applicationProperties.getUsername());
        tokenRequest.setPassword(applicationProperties.getPassword());
        WBTokenResponse tokenResponse = wbClient.getWBToken(tokenRequest, applicationProperties.getApikey());
        this.accesToken = "Bearer " + tokenResponse.getAuthResult().getAccessToken();
    }

    public Boolean isUserExist(String email) {
        Map<String, Object> queryMap = new HashedMap<>();
        queryMap.put("emailId", email);
        WBUserSearchResponse userSearchResponse = wbClient.searchUser(queryMap, applicationProperties.getApikey(), accesToken);
        return userSearchResponse.getErrors().isEmpty();
    }
}
