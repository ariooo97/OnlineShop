package ir.largesize.OnlineShop.helper.utils;


import com.google.gson.*;
import ir.largesize.OnlineShop.helper.payment.zarinpal.medels.PaymentResponse;
import ir.largesize.OnlineShop.helper.payment.zarinpal.medels.VerifyResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class HttpUtils<T> {
    final Class<T> type;


    public HttpUtils(Class<T> type) {
        this.type = type;
    }

    public T callPost(String address, Object data) throws JSONException {
        Gson gson = getGson();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String bodyData = gson.toJson(data);
        HttpEntity<String> httpEntity = new HttpEntity<>(bodyData, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(address, HttpMethod.POST, httpEntity, String.class);
        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setCode(Long.parseLong(jsonObject.getJSONObject("data").getString("code")));
        paymentResponse.setAuthority(jsonObject.getJSONObject("data").getString("authority"));
        return (T) paymentResponse;
    }

    public T callPostVerify(String address, Object data) throws JSONException {
        Gson gson = getGson();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String bodyData = gson.toJson(data);
        HttpEntity<String> httpEntity = new HttpEntity<>(bodyData, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(address, HttpMethod.POST, httpEntity, String.class);
        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        VerifyResponse verifyResponse = new VerifyResponse();
        verifyResponse.setCode(Long.parseLong(jsonObject.getJSONObject("data").getString("code")));
        verifyResponse.setRef_id(Long.parseLong(jsonObject.getJSONObject("data").getString("ref_id")));
        return (T) verifyResponse;
    }

    public ResponseEntity<T> callGet(String address) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(address, getType());
        return responseEntity;
    }

    public Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson;

    }

    public Class<T> getType() {
        return type;
    }
}
