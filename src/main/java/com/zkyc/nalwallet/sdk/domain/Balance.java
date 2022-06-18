package com.zkyc.nalwallet.sdk.domain;

import java.math.BigDecimal;

public class Balance {
    private String address;
    private BigDecimal balance;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
