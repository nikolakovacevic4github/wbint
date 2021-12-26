package com.wbint.wbclient.model.user;

public class WBAddUserRequest {
    private String invitedUserDisplayName;
    private String invitedUserEmailAddress;
    private String inviteRedirectUrl;
    private String groupName;
    private Boolean sendInvitationMessage;
    private User user;

    public String getInvitedUserDisplayName() {
        return invitedUserDisplayName;
    }

    public void setInvitedUserDisplayName(String invitedUserDisplayName) {
        this.invitedUserDisplayName = invitedUserDisplayName;
    }

    public String getInvitedUserEmailAddress() {
        return invitedUserEmailAddress;
    }

    public void setInvitedUserEmailAddress(String invitedUserEmailAddress) {
        this.invitedUserEmailAddress = invitedUserEmailAddress;
    }

    public String getInviteRedirectUrl() {
        return inviteRedirectUrl;
    }

    public void setInviteRedirectUrl(String inviteRedirectUrl) {
        this.inviteRedirectUrl = inviteRedirectUrl;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean getSendInvitationMessage() {
        return sendInvitationMessage;
    }

    public void setSendInvitationMessage(Boolean sendInvitationMessage) {
        this.sendInvitationMessage = sendInvitationMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "WBAddUserRequest{" +
                "invitedUserDisplayName='" + invitedUserDisplayName + '\'' +
                ", invitedUserEmailAddress='" + invitedUserEmailAddress + '\'' +
                ", inviteRedirectUrl='" + inviteRedirectUrl + '\'' +
                ", groupName='" + groupName + '\'' +
                ", sendInvitationMessage='" + sendInvitationMessage + '\'' +
                ", user=" + user +
                '}';
    }
}