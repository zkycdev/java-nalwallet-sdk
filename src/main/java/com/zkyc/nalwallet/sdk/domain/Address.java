package com.zkyc.nalwallet.sdk.domain;


public class Address {
    private String address;
    private String coinType;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", coinType=" + coinType +
                '}';
    }
}
