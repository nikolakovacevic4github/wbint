package com.wbint.service.dto;

public class UserExist {

    private boolean isUserExist;

    private String redirectUrl;

    public boolean isUserExist() {
        return isUserExist;
    }

    public void setUserExist(boolean userExist) {
        isUserExist = userExist;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "UserExist{" +
            "isUserExist=" + isUserExist +
            ", redirectUrl='" + redirectUrl + '\'' +
            '}';
    }
}
