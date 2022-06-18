import com.zkyc.nalwallet.sdk.client.NalwalletClient;
import com.zkyc.nalwallet.sdk.domain.Address;
import com.zkyc.nalwallet.sdk.domain.Balance;
import com.zkyc.nalwallet.sdk.domain.Coin;
import com.zkyc.nalwallet.sdk.domain.ResultMsg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UdunclientDemo {
    public static void main(String[] args) {
        //初始化
        NalwalletClient nalwalletClient = new NalwalletClient("http://192.168.110.46:8004/",
                "10005",
                "9863d40985eb92792e0e51cce41a0f2c",
                "http://192.168.110.159:8129/nalwallet/notify");
        //获取商户支持币种
    //    List<Coin> coinList = nalwalletClient.listSupportCoin(false);
        //创建地址
//        for (int i = 0; i < 20; i++) {
            Address address3 = nalwalletClient.createAddress("usdt-trc20");
            Address address4 = nalwalletClient.createAddress("fil");
            System.out.println(address3);
            System.out.println(address4);
//        }

        //查询余额
//        List<String> listAddress = new ArrayList<>();
//        listAddress.add("t1ge6MzUFbBFWVymxX1gdQa3yr7PFdBkomc");
//        listAddress.add("0xa09921e9a3886e1b2b79e8fcd27d3a61ebe0ecd9");
//        List<Balance> listBalance = nalwalletClient.queryBalance("fil", listAddress);
        //检验地址合法性
     //   boolean b = nalwalletClient.checkAddress("133", "t1ge6MzUFbBFWVymxX1gdQa3yr7PFdBkomc");
     //   System.out.println(b);
        //提币
//        ResultMsg withdrawResult1 = nalwalletClient.withdraw("TPkoMv33QPEaHYqroeUQUg4QhUwHQwBFQB", new BigDecimal("1"),"usdt-trc20","");
//        System.out.println(withdrawResult1);
//
//        ResultMsg withdrawResult2 = nalwalletClient.withdraw("0xa09921e9a3886e1b2b79e8fcd27d3a61ebe0ecd9", BigDecimal.TEN,
//                "520", "520",
//                "42423121", "");
//        System.out.println(withdrawResult2);
//        //代付
//        ResultMsg proxyPayResult = nalwalletClient.proxyPay("0xa09921e9a3886e1b2b79e8fcd27d3a61ebe0ecd9", BigDecimal.TEN,
//                "520", "520",
//                "42423121", "", "http://demo.com/notify");
//        System.out.println(proxyPayResult);

    }
}
