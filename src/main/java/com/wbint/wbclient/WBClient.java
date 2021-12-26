package com.wbint.wbclient;

import com.wbint.wbclient.model.user.WBAddUserRequest;
import com.wbint.wbclient.model.user.WBAddUserResponse;
import com.wbint.wbclient.model.userSearch.WBUserSearchResponse;
import com.wbint.wbclient.model.wbToken.WBTokenRequest;
import com.wbint.wbclient.model.wbToken.WBTokenResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

public interface WBClient {
    @RequestLine("POST /oauth-services/v1.0/generate-token-through-resource-owner")
    @Headers({"Content-Type: application/x-www-form-urlencoded", "ApiKey: ${wb.apikey}"})
    WBTokenResponse getWBToken(WBTokenRequest wbTokenRequest);

    @RequestLine("GET /b2b/v2.0/user")
    @Headers({"Content-Type: application/json", "ApiKey: ${wb.apikey}", "Authorization: {access_token}"})
    WBUserSearchResponse searchUser(@QueryMap Map<String, Object> queryMap, @Param("access_token") String accessToken);

    @RequestLine("POST /b2b/v2.0/user")
    @Headers({"Content-Type: application/json", "ApiKey: ${wb.apikey}", "Authorization: {access_token}"})
    WBAddUserResponse addUser(WBAddUserRequest userRequest, @Param("access_token") String accessToken);
}