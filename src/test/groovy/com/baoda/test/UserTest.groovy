package com.baoda.test

import groovyx.net.http.HttpResponseException
import org.apache.http.Header
import org.apache.http.HttpStatus
import org.apache.http.message.BufferedHeader
import org.jsoup.HttpStatusException
import org.testng.annotations.Test
import test.abstractconfmethod.foo.A

import static com.social.credits.test.FuncTestUtils.API_PATH
import static com.social.credits.test.FuncTestUtils.expectedHttpError
import static com.social.credits.test.FuncTestUtils.*
import static groovyx.net.http.ContentType.JSON

/**
 * Created by admin on 2016/8/5.
 */
@Test
class UserTest {

    @Test
    public void ifregistercuccess(){
        def register=register()
        assert register.status==HttpStatus.SC_OK

    }
//    public void validateisactive(){
//
//       def res=restClient().get(path:API_PATH+"/user/email/activate?email=forever@mailinator.com&code=XXX")
//
//        def response=res.responseData
//        print(response)
//        assert res.status==401001
//
//    }

    public void ifloginsuccess(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def res=restClient().post(path: API_PATH+"/user/login",contentType: JSON,body: payload)
        def response=res.responseData
        print(response)

        assert res.status==HttpStatus.SC_OK

    }

    public void changepwd(){

    }


//public void delete(){
//    def login=login()
//    def getueserinfo=restClient().get(path: API_PATH+"/user/info")
//    print(getueserinfo.header)
//
//    def res=restClient().get(path: API_PATH+"/user/logout",contentType: JSON,headers: getueserinfo.header)
//}



}



