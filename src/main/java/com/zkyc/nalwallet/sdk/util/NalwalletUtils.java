package com.zkyc.nalwallet.sdk.util;



import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import com.alibaba.fastjson.JSONObject;
import com.zkyc.nalwallet.sdk.client.NalwalletClient;
import com.zkyc.nalwallet.sdk.domain.Address;
import com.zkyc.nalwallet.sdk.domain.dto.CheckAddressDto;
import com.zkyc.nalwallet.sdk.domain.dto.CreateAddreDto;
import com.zkyc.nalwallet.sdk.domain.dto.WithdrawDto;

import java.util.HashMap;
import java.util.Map;

public class NalwalletUtils {

    public static void main(String[] args) {
       /* CreateAddreDto c= new CreateAddreDto();
        c.setAlias("");
        c.setCoinType("fil");
        c.setWalletId("");
        c.setCallUrl("http://192.168.110.159:8129/nalwallet/notify");
        c.setMerchantId("1");
        CreateAddreDto dto = parseParamsAddre("9add10b914fb4cb70197aafed8f004a7", c);
        String s = HttpRequest.post("http://192.168.110.159:8004/sdk/api/createAddress").form( MyMapUtil.getValueMap(dto)).execute().toString();
        System.out.println(s);*/
//
//        NalwalletClient nalwalletClient=new NalwalletClient("http://192.168.110.159:8004", "1", "9add10b914fb4cb70197aafed8f004a7", "http://192.168.110.159:8129/nalwallet/notify");
//        nalwalletClient.createAddress("fil");
    }
    public static String postAddre(String gateway, String merchantKey, String path, CreateAddreDto createAddreDto) {
        CreateAddreDto dto = parseParamsAddre(merchantKey, createAddreDto);
        return HttpRequest.post(gateway.concat(path)).form(MyMapUtil.getValueMap(dto)).execute().body();

    }
    public static String postWithdraw(String gateway, String merchantKey, String path, WithdrawDto withdrawDto) {
        WithdrawDto dto = parseParamsWithdr(merchantKey, withdrawDto);
        return HttpRequest.post(gateway.concat(path)).form(MyMapUtil.getValueMap(dto)).execute().body();

    }
    public static String postCheckAddress(String gateway, String merchantKey, String path, CheckAddressDto checkAddressDto) {
        CheckAddressDto dto = parseCheckAddress(merchantKey, checkAddressDto);
        return HttpRequest.post(gateway.concat(path)).form(MyMapUtil.getValueMap(dto)).execute().body();

    }

    public static CreateAddreDto parseParamsAddre(String merchantKey, CreateAddreDto createAddreDto) {
        String timestamp = System.currentTimeMillis() + "";
        String nonce = RandomUtil.randomString(6);
        String sign = sign(merchantKey, timestamp, nonce, JSONObject.toJSONString(createAddreDto));
        createAddreDto.setTimestamp(timestamp);
        createAddreDto.setSign(sign);
        createAddreDto.setNonce(nonce);
        return createAddreDto;
    }

    public static WithdrawDto parseParamsWithdr(String merchantKey, WithdrawDto withdrawDto) {
        String timestamp = System.currentTimeMillis() + "";
        String nonce = RandomUtil.randomString(6);
        String sign = sign(merchantKey, timestamp, nonce, JSONObject.toJSONString(withdrawDto));
        withdrawDto.setTimestamp(timestamp);
        withdrawDto.setSign(sign);
        withdrawDto.setNonce(nonce);
        return withdrawDto;
    }

    public static CheckAddressDto parseCheckAddress(String merchantKey, CheckAddressDto checkAddressDto) {
        String timestamp = System.currentTimeMillis() + "";
        String nonce = RandomUtil.randomString(6);
        String sign = sign(merchantKey, timestamp, nonce, JSONObject.toJSONString(checkAddressDto));
        checkAddressDto.setTimestamp(timestamp);
        checkAddressDto.setSign(sign);
        checkAddressDto.setNonce(nonce);
        return checkAddressDto;
    }

    public static String sign(String key, String timestamp, String nonce, String body) {
        String raw = body.concat(key).concat(nonce).concat(timestamp);
        return SecureUtil.md5(raw);
    }

    public static boolean checkSign(String key, String timestamp, String nonce, String body, String sign) {
        String checkSign = sign(key, timestamp, nonce, body);
        return checkSign.equals(sign);
    }
}
