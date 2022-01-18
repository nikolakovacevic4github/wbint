package com.wbint.service.dto;

public class UserRegistered {
    private boolean isUserRegistered;
    private String redirectUrl;

    public boolean isUserRegistered() {
        return isUserRegistered;
    }

    public void setUserRegistered(boolean userRegistered) {
        isUserRegistered = userRegistered;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "UserRegistered{" +
            "isUserRegistered=" + isUserRegistered +
            ", redirectUrl='" + redirectUrl + '\'' +
            '}';
    }
}
