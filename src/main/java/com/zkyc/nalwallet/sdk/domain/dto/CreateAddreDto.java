package com.zkyc.nalwallet.sdk.domain.dto;

public class CreateAddreDto extends SdkBaseDto{


    private String walletId;

    private String  alias;

    private String callUrl;

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCallUrl() {
        return callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl;
    }
}
