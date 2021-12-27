package com.wbint.wbclient;

import com.wbint.wbclient.model.user.WBAddUserRequest;
import com.wbint.wbclient.model.user.WBAddUserResponse;
import com.wbint.wbclient.model.userSearch.WBUserSearchResponse;
import com.wbint.wbclient.model.wbToken.WBTokenRequest;
import com.wbint.wbclient.model.wbToken.WBTokenResponse;
import feign.*;

import java.util.Map;

public interface WBClient {
    @RequestLine("POST /oauth-services/v1.0/generate-token-through-resource-owner")
    @Headers({"Content-Type: application/x-www-form-urlencoded", "ApiKey: {apikey}"})
    WBTokenResponse getWBToken(WBTokenRequest wbTokenRequest, @Param("apikey") String apikey);

    @RequestLine("GET /b2b/v2.0/user")
    @Headers({"Content-Type: application/json", "ApiKey: {apikey}", "Authorization: {access_token}"})
    WBUserSearchResponse searchUser(@QueryMap Map<String, Object> queryMap, @Param("apikey") String apikey, @Param("access_token") String accessToken);

    @RequestLine("POST /b2b/v2.0/user")
    @Headers({"Content-Type: application/json", "ApiKey: {apikey}", "Authorization: {access_token}"})
    WBAddUserResponse addUser(WBAddUserRequest userRequest, @Param("apikey") String apikey, @Param("access_token") String accessToken);
}
