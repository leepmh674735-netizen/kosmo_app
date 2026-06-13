package com.winter.app.stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {
	public String getMemberPrice() {
		return "some price";
				
	}
    
    @Value("${api.appKey}")
    private String appkey;
    
    @Value("${api.secretKey}")
    private String secretkey;
    
    @Value("${api.accesstoken}")
    private String accesstoken;
    
    public void getFastApi() throws Exception {
        RestClient resultClient = RestClient.create();
        String result = resultClient.get()
                .uri("http://localhost:8000/list") 
                .retrieve()                        
                .body(String.class);
        
        System.out.println(result);
    }
    
    public String getMarketPrice() throws Exception {
        RestClient client = RestClient.create();
        
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("FID_COND_MRKT_DIV_CODE", "J"); 
        queryParams.add("FID_INPUT_ISCD", "005930");     

        String result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("openapi.koreainvestment.com")
                        .port(29443)
                        .path("/uapi/domestic-stock/v1/quotations/inquire-price")
                        .queryParams(queryParams) 
                        .build())
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("authorization", "Bearer " + accesstoken) 
                .header("appkey", appkey)
                .header("appsecret", secretkey)
                .header("tr_id", "FNHKST01010100") 
                .header("custtype", "P")           
                .retrieve()
                .body(String.class);

        System.out.println("Market Price Result: " + result);
        return result;
    }
    
    public void getAccessToken() throws Exception {
        RestClient client = RestClient.create(); 
        
        AccessTokenRequest bodyPayload = new AccessTokenRequest("client_credentials", appkey, secretkey);
        
        String result = client.post()
                .uri("https://openapi.koreainvestment.com:29443/oauth2/tokenP") 
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyPayload)
                .retrieve()
                .body(String.class);
        
        System.out.println("Access Token Result: " + result);
    }   

    private static class AccessTokenRequest {
        private final String grant_type;
        private final String appkey;
        private final String appsecret;

        public AccessTokenRequest(String grant_type, String appkey, String appsecret) {
            this.grant_type = grant_type;
            this.appkey = appkey;
            this.appsecret = appsecret;
        }

        public String getGrant_type() { return grant_type; }
        public String getAppkey() { return appkey; }
        public String getAppsecret() { return appsecret; }
    }
}