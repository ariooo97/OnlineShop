package ir.largesize.OnlineShop.helper.payment.zarinpal.contorollers;

import ir.largesize.OnlineShop.helper.payment.zarinpal.medels.PaymentRequest;
import ir.largesize.OnlineShop.helper.payment.zarinpal.medels.PaymentResponse;
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

    public String goToPayment(StartPaymentVM data) throws Exception {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(data.getAmount());
        request.setDescription(data.getDescription());
        request.setCallBackURL(callbackUrl);
        request.setMerchantId(merchantId);
        request.setEmail(data.getEmail());
        request.setMobile(data.getMobile());

        HttpUtils<PaymentResponse> httpUtils = new HttpUtils<>(PaymentResponse.class);
        PaymentResponse response = httpUtils.callPost(paymentAddress, request);
        if (response.getStatus() != 100)
            throw new Exception("Errore on payment!");
        return startPayAddress + response.getAuthority();

    }
}
