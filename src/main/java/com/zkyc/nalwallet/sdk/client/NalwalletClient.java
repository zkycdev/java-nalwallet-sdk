package com.zkyc.nalwallet.sdk.client;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.zkyc.nalwallet.sdk.constant.ApiPath;
import com.zkyc.nalwallet.sdk.domain.*;
import com.zkyc.nalwallet.sdk.domain.dto.CheckAddressDto;
import com.zkyc.nalwallet.sdk.domain.dto.CreateAddreDto;
import com.zkyc.nalwallet.sdk.domain.dto.WithdrawDto;
import com.zkyc.nalwallet.sdk.util.NalwalletUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NalwalletClient implements NalwalletApi {
    /**
     * 网关
     */
    private final String gateway;
    /**
     * 商户编号
     */
    private final String merchantId;
    /**
     * 商户key
     */
    private final String merchantKey;
    /**
     * 默认回调地址
     */
    private final String defaultCallBackUrl;

    public NalwalletClient(String gateway, String merchantId, String merchantKey, String defaultCallBackUrl) {
        this.gateway = gateway;
        this.merchantId = merchantId;
        this.merchantKey = merchantKey;
        this.defaultCallBackUrl = defaultCallBackUrl;
    }

    @Override
    public Address createAddress(String mainCoinType) {
        return createAddress(mainCoinType, "", "", defaultCallBackUrl);
    }

    @Override
    public Address createAddress(String mainCoinType, String alias, String walletId) {
        return createAddress(mainCoinType, alias, walletId, defaultCallBackUrl);
    }

    @Override
    public Address createAddress(String mainCoinType, String alias, String walletId, String callUrl) {
        CreateAddreDto createAddreDto = new CreateAddreDto();
        createAddreDto.setMerchantId(merchantId);
        createAddreDto.setCoinType(mainCoinType);
        createAddreDto.setCallUrl(callUrl);
        createAddreDto.setWalletId(walletId);
        createAddreDto.setAlias(alias);
        String jsonStr = NalwalletUtils.postAddre(gateway, merchantKey, ApiPath.CREATE_ADDRESS, createAddreDto);
        ResultMsg result = JSONObject.parseObject(jsonStr,ResultMsg.class);
        if (result.getCode() != HttpStatus.HTTP_OK) {
            Console.error(JSONUtil.toJsonStr(result));
            return null;
        }
        return JSONUtil.toBean(result.getData(), Address.class);
    }

    @Override
    public ResultMsg withdraw(String address, BigDecimal amount,  String coinType, String businessId, String memo) {
        return withdraw(address, amount, coinType,businessId,  memo, defaultCallBackUrl);
    }

    @Override
    public ResultMsg withdraw(String address, BigDecimal amount, String coinType, String businessId, String memo, String callUrl) {
        WithdrawDto withdrawDto = new WithdrawDto();
        withdrawDto.setMerchantId(merchantId);
        withdrawDto.setAddress(address);
        withdrawDto.setBusinessId(businessId);
        withdrawDto.setMemo(memo);
        withdrawDto.setAmount(amount);
        withdrawDto.setCoinType(coinType);
        withdrawDto.setCallUrl(callUrl);
        return JSONUtil.toBean(NalwalletUtils.postWithdraw(gateway, merchantKey, ApiPath.WITHDRAW, withdrawDto), ResultMsg.class);

    }

    @Override
    public ResultMsg proxyPay(String address, BigDecimal amount, String mainCoinType, String coinType, String businessId, String memo) {
        return null;
    }

    @Override
    public ResultMsg proxyPay(String address, BigDecimal amount, String mainCoinType, String coinType, String businessId, String memo, String callUrl) {
        return null;
    }


    @Override
    public boolean checkAddress(String mainCoinType, String address) {
        CheckAddressDto checkAddressDto = new CheckAddressDto();
        checkAddressDto.setAddress(address);
        checkAddressDto.setMerchantId(merchantId);
        checkAddressDto.setCoinType(mainCoinType);
        ResultMsg result = JSONUtil.toBean(NalwalletUtils.postCheckAddress(gateway, merchantKey, ApiPath.CHECK_ADDRESS,checkAddressDto), ResultMsg.class);
        return result.getCode() == HttpStatus.HTTP_OK;
    }

    @Override
    public List<Coin> listSupportCoin(boolean showBalance) {
        return null;
    }

    @Override
    public List<Balance> queryBalance(String coinType, List<String> address) {
        return null;
    }

}
