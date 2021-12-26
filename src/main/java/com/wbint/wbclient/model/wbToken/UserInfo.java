package com.wbint.wbclient.model.wbToken;

public class UserInfo {
    private String uniqueId;
    private String displayableId;
    private String givenName;
    private String familyName;
    private String identityProvider;
    private String passwordChangeUrl;
    private Long passwordExpiresOn;
    private String tenantId;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getDisplayableId() {
        return displayableId;
    }

    public void setDisplayableId(String displayableId) {
        this.displayableId = displayableId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getIdentityProvider() {
        return identityProvider;
    }

    public void setIdentityProvider(String identityProvider) {
        this.identityProvider = identityProvider;
    }

    public String getPasswordChangeUrl() {
        return passwordChangeUrl;
    }

    public void setPasswordChangeUrl(String passwordChangeUrl) {
        this.passwordChangeUrl = passwordChangeUrl;
    }

    public Long getPasswordExpiresOn() {
        return passwordExpiresOn;
    }

    public void setPasswordExpiresOn(Long passwordExpiresOn) {
        this.passwordExpiresOn = passwordExpiresOn;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uniqueId='" + uniqueId + '\'' +
                ", displayableId='" + displayableId + '\'' +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", identityProvider='" + identityProvider + '\'' +
                ", passwordChangeUrl='" + passwordChangeUrl + '\'' +
                ", passwordExpiresOn=" + passwordExpiresOn +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}