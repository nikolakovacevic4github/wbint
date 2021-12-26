package com.wbint.wbclient.model.user;

import java.util.List;

public class WBAddUserResponse {
    private String status;
    private List<String> messages;
    private List<String> warnings;
    private List<String> errors;
    private WBResult result;
    private String transactionId;
}