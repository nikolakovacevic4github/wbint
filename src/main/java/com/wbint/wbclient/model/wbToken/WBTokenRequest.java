package com.wbint.wbclient.model.wbToken;

public class WBTokenRequest {
    private String authority;
    private String tenant;
    private String client_id;
    private String resource;
    private String username;
    private String password;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "WBTokenRequest{" +
                "authority='" + authority + '\'' +
                ", tenant='" + tenant + '\'' +
                ", client_id='" + client_id + '\'' +
                ", resource='" + resource + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
