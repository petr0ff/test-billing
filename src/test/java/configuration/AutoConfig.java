package configuration;

import io.restassured.config.RestAssuredConfig;

/**
 * Created by German on 25.09.2016.
 */
public class AutoConfig {
    public static final String BASE_HOST = "http://127.0.0.1:8080/billing";
    public static final String ACCOUNT = BASE_HOST + "/account";
    public static final String BALANCE = BASE_HOST + "/billing/balance";
}
