package com.social.credits.test

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import org.apache.commons.lang3.RandomStringUtils


import java.security.SecureRandom

import static groovyx.net.http.ContentType.JSON

class FuncTestUtils {
    static final DEFAULT_BASE_URL = System.getProperty("host")
    static final DEFAULT_PLAY_BASE_URL = "http://192.168.31.116:8380"
    static final int TEN_SECONDS = 1000000

    public static final String API_PATH = "/api"

    private static final Random SECURE_RANDOM = new SecureRandom();

    static String getScBaseUrl() {
        if (DEFAULT_BASE_URL == null) {
            System.getProperties().put("sc-base-url", "http://192.168.31.116:8380")
        }
        System.getProperty("sc-base-url", DEFAULT_BASE_URL)
    }

    static String getPlayBaseUrl() {
        System.getProperty("play-base-url", DEFAULT_PLAY_BASE_URL)
    }

    static RESTClient restClient() {
        def restClient = new RESTClient(getScBaseUrl())
        restClient.getClient().getParams().setParameter("http.connection.timeout", new Integer(TEN_SECONDS))
        restClient.getClient().getParams().setParameter("http.socket.timeout", new Integer(TEN_SECONDS))
        return restClient
    }

    static HTTPBuilder httpClient() {
        def httpBuilder = new HTTPBuilder(getScBaseUrl())
        return httpBuilder
    }

    static def mapToJsonString(map) {
        String json = "{"
        map.each { key, value ->
            json += "\"$key\":"
            json += "\"$value\""
            json += ","
        }
        json = json.substring(0, json.length() - 1)
        json += "}"
        return json;
    }

    static def randomString(len = 10) {
        char[] chars = "1234567890abcdefghijklmnopqrstuvwxyz"
        return RandomStringUtils.random(len, 0, chars.length, false, false, chars, SECURE_RANDOM);
    }

    static def randomNumber(len = 11) {
        char[] chars = "1234567890"
        return RandomStringUtils.random(len, 0, chars.length, false, false, chars, SECURE_RANDOM);
    }

    static def getvalidatedcode(){
        def  res=restClient().post(path:API_PATH+"/captcha/pic")
        return res

    }



    static void expectedHttpError(Integer statusCode, Closure c) {
        try {
            def response = c()
            return;
        } catch (HttpResponseException ex) {
            assert ex.getStatusCode() == statusCode
            return
        }
        assert false
    }



    static def login(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def res=restClient().post(path: API_PATH+"/user/login",contentType: JSON,body: payload)
        def header = [
                "sc-token": res.headers."sc-token"
        ]
        res.responseData.headers=header;
        return res

    }





    static def register() {

        def payload =    [
                "username": randomString() + "@mailinator.com",
                "password": "12345678",
                "captcha": "1424",
                "nickname":  randomString(),
                "authType":"EMAIL" //只能为EMAIL或PHONE
        ]
        def resp = restClient().post(path: API_PATH + "/user/register", contentType: JSON, body: payload);

        return [
                "id": resp.responseData.id,
                "email": resp.responseData.email,
                "phone": resp.responseData.phone,
                "nickname": resp.responseData.nickname,
                "isEmailBind": false,
                "isPhoneBind": false,
                "lastLoginDate": resp.responseData.lastLoginDate,
                "loginTime": resp.responseData.loginTime,
                "status":resp.status



        ]
    }
}






