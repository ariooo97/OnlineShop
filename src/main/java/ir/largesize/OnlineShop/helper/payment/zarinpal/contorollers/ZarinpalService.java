package ir.largesize.OnlineShop.helper.payment.zarinpal.contorollers;

import ir.largesize.OnlineShop.entities.orders.Transactions;
import ir.largesize.OnlineShop.helper.payment.zarinpal.medels.PaymentRequest;
import ir.largesize.OnlineShop.helper.payment.zarinpal.medels.PaymentResponse;
import ir.largesize.OnlineShop.helper.payment.zarinpal.medels.VerifyRequest;
import ir.largesize.OnlineShop.helper.payment.zarinpal.medels.VerifyResponse;
import ir.largesize.OnlineShop.helper.uimodels.StartPaymentVM;
import ir.largesize.OnlineShop.helper.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ZarinpalService {

    @Value("${zarinpal.merchantId}")
    private String merchantId;

    @Value("${zarinpal.callBackUrl}")
    private String callbackUrl;

    @Value("${zarinpal.paymentAddress}")
    private String paymentAddress;

    @Value("${zarinpal.startPayAddress}")
    private String startPayAddress;


    @Value("${zarinpal.verificationAddress}")
    private String verificationAddress;

    public String goToPayment(StartPaymentVM data) throws Exception {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(data.getAmount());
        request.setDescription(data.getDescription());
        request.setCallback_url(callbackUrl);
        request.setMerchantId(merchantId);
        request.setEmail(data.getEmail());
        request.setMobile(data.getMobile());

        HttpUtils<PaymentResponse> httpUtils = new HttpUtils<>(PaymentResponse.class);
        PaymentResponse response = httpUtils.callPost(paymentAddress, request);
        data.setAuthority(response.getAuthority());
        data.setStatus(response.getCode());
        if (response.getCode() != 100)
            throw new Exception("Error on payment!");
        return startPayAddress + response.getAuthority();

    }

    public Transactions goToVerify(Transactions transactions) throws Exception {
        VerifyRequest request = new VerifyRequest();
        request.setAmount(transactions.getAmount());
        request.setAuthority(transactions.getAuthority());
        request.setMerchant_id(merchantId);
        HttpUtils<VerifyResponse> httpUtils = new HttpUtils<>(VerifyResponse.class);
        VerifyResponse response = httpUtils.callPostVerify(verificationAddress, request);
        transactions.setTransactionVerify(response.getCode());
        transactions.setRefId(response.getRef_id());
        if (response.getCode() != 100)
            throw new Exception("Error on payment!");

        return transactions;

    }
}
