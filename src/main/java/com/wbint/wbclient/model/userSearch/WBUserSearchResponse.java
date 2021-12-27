package com.wbint.wbclient.model.userSearch;

import com.wbint.wbclient.model.user.WBResult;

import java.util.List;

public class WBUserSearchResponse {
    private String status;
    private List<String> messages;
    private List<String> warnings;
    private List<String> errors;
    private WBResult result;
    private String transactionId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public WBResult getResult() {
        return result;
    }

    public void setResult(WBResult result) {
        this.result = result;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "WBUserSearchResponse{" +
            "status='" + status + '\'' +
            ", messages=" + messages +
            ", warnings=" + warnings +
            ", errors=" + errors +
            ", result=" + result +
            ", transactionId='" + transactionId + '\'' +
            '}';
    }
}
