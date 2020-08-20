package com.tianyuli.usersystem.rpcDomain.req;

import java.io.Serializable;

public class UserPreferenceReq implements Serializable {

    private static final long serialVersionUID = 6096404114716427149L;

    private String todoNotice;

    private String sysMessageNotice;

    private String otherUserMessageNotice;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTodoNotice() {
        return todoNotice;
    }

    public void setTodoNotice(String todoNotice) {
        this.todoNotice = todoNotice;
    }

    public String getSysMessageNotice() {
        return sysMessageNotice;
    }

    public void setSysMessageNotice(String sysMessageNotice) {
        this.sysMessageNotice = sysMessageNotice;
    }

    public String getOtherUserMessageNotice() {
        return otherUserMessageNotice;
    }

    public void setOtherUserMessageNotice(String otherUserMessageNotice) {
        this.otherUserMessageNotice = otherUserMessageNotice;
    }
}
