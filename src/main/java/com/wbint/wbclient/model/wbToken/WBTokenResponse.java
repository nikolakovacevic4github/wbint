package com.wbint.wbclient.model.wbToken;

public class WBTokenResponse {
    private AuthResult authResult;
    private String errorMessage;

    public AuthResult getAuthResult() {
        return authResult;
    }

    public void setAuthResult(AuthResult authResult) {
        this.authResult = authResult;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "WBTokenResponse{" +
                "authResult=" + authResult +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}