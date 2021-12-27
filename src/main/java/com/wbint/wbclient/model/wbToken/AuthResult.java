package com.wbint.wbclient.model.wbToken;

import java.time.LocalDateTime;

public class AuthResult {
    private String accessTokenType;
    private Long expiresOn;
    private String idToken;
    private UserInfo userInfo;
    private String accessToken;
    private String refreshToken;
    private Boolean multipleResourceRefreshToken;
    private Long expiresAfter;
    private String expiresOnDate;

    public String getAccessTokenType() {
        return accessTokenType;
    }

    public void setAccessTokenType(String accessTokenType) {
        this.accessTokenType = accessTokenType;
    }

    public Long getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Long expiresOn) {
        this.expiresOn = expiresOn;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Boolean getMultipleResourceRefreshToken() {
        return multipleResourceRefreshToken;
    }

    public void setMultipleResourceRefreshToken(Boolean multipleResourceRefreshToken) {
        this.multipleResourceRefreshToken = multipleResourceRefreshToken;
    }

    public Long getExpiresAfter() {
        return expiresAfter;
    }

    public void setExpiresAfter(Long expiresAfter) {
        this.expiresAfter = expiresAfter;
    }

    public String getExpiresOnDate() {
        return expiresOnDate;
    }

    public void setExpiresOnDate(String expiresOnDate) {
        this.expiresOnDate = expiresOnDate;
    }

    @Override
    public String toString() {
        return "AuthResult{" +
                "accessTokenType='" + accessTokenType + '\'' +
                ", expiresOn=" + expiresOn +
                ", idToken='" + idToken + '\'' +
                ", userInfo=" + userInfo +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", multipleResourceRefreshToken=" + multipleResourceRefreshToken +
                ", expiresAfter=" + expiresAfter +
                ", expiresOnDate=" + expiresOnDate +
                '}';
    }
}
