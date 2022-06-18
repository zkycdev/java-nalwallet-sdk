package com.zkyc.nalwallet.sdk.domain.dto;

import java.math.BigDecimal;

public class WithdrawDto extends SdkBaseDto{


    private String contract;


    private String  address;


    private BigDecimal amount;


    private String  businessId;

    private String  memo;

    private String callUrl;

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCallUrl() {
        return callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl;
    }
}
