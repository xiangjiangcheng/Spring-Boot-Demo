package com.xiang.demo.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.xiang.demo.model.Alipay;
import net.guerlab.sdk.alipay.controller.AlipayAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/pay/alipay")
public class AlipayController extends AlipayAbstractController {

    /**
     * 支付宝请求sdk客户端
     */
    private AlipayClient client;

    /**
     * 支付宝Entity
     */
    private Alipay alipay;

    @Autowired
    public AlipayController(AlipayClient client, Alipay alipay) {
        this.client = client;
        this.alipay = alipay;
        // 使用支付宝沙箱环境的网关
        String alipayZuul = "https://openapi.alipaydev.com/gateway.do";
        client = new
                DefaultAlipayClient(alipayZuul, alipay.getAppId(), alipay.getPrivateKey(), "json", "utf-8",
                alipay.getAlipayPublicKey(), alipay.getSignType());
    }

    /**
     * 支付请求 - 付款码支付
     */
    @RequestMapping("/app/{orderId}")
    @ResponseBody
    public String app(
            @PathVariable Long orderId,
            HttpServletResponse httpResponse) throws AlipayApiException {

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();
        request.setBizModel(model);

        model.setOutTradeNo(System.currentTimeMillis() + "");
        model.setSubject("Iphone6 32G");
        model.setTotalAmount("100.01");
        // 沙箱钱包中的付款码
        model.setAuthCode("284332639700733280");
        model.setScene("bar_code");

        AlipayTradePayResponse response = null;
        try {
            response = client.execute(request);
            System.out.println(response.getBody());
            System.out.println(response.getTradeNo());
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "支付失败";
        }
        return "支付成功";
        // JSONObject data = new JSONObject();
        // data.put("out_trade_no", "201701010000001234"); //商户订单号
        // data.put("product_code", "QUICK_MSECURITY_PAY"); //产品码, APP支付 QUICK_MSECURITY_PAY, PC支付 FAST_INSTANT_TRADE_PAY, 移动H5支付 QUICK_WAP_PAY
        // data.put("total_amount", "0.01"); //订单金额
        // data.put("subject", "测试订单"); //订单标题
        //
        // //APP支付
        // // AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // //PC支付
        // AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        // //移动H5支付
        // //AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        // // request.setNotifyUrl("http://demo/pay/alipay/notify/1"); //异步通知地址
        // request.setBizContent(data.toJSONString()); //业务参数
        // return client.sdkExecute(request).getBody();
    }


    @PostMapping("/notify/{orderId}")
    public String notify(
            @PathVariable Long orderId,
            HttpServletRequest request) {
        if (!notify0(request.getParameterMap())) {
            // 这里处理验签失败
        }
        // 获取请求参数中的商户订单号
        request.getParameter("trade_no");
        return "success";
    }
}