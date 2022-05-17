package com.ruoyi.system.domain.mb;

public class MbCancel {

    private String code;//内部订单号
    private String changeStatus = "cancel";//exception
    private String processMessage;//订单异常信息

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    public String getProcessMessage() {
        return processMessage;
    }

    public void setProcessMessage(String processMessage) {
        this.processMessage = processMessage;
    }

}
