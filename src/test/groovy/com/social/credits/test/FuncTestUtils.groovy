package com.social.credits.test

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import org.apache.commons.lang3.RandomStringUtils

import java.security.SecureRandom

class FuncTestUtils {
    static DEFAULT_BASE_URL = System.getProperty("sc-base-url")
    static final DEFAULT_PLAY_BASE_URL = "http://localhost:8080"
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

    static RESTClient playRestClient() {
        def restClient = new RESTClient(getPlayBaseUrl())
        restClient.getClient().getParams().setParameter("http.connection.timeout", new Integer(TEN_SECONDS))
        restClient.getClient().getParams().setParameter("http.socket.timeout", new Integer(TEN_SECONDS))
        return restClient
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
}