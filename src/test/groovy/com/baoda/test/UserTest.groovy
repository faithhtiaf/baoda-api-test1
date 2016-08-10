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
import static com.social.credits.test.FuncTestUtils.login
import static groovyx.net.http.ContentType.JSON

/**
 * Created by admin on 2016/8/5.
 */
@Test
class UserTest {

    @Test
    public void ifregistercuccess(){
        try{
        def register=register()
        print(register)
        assert register.status==HttpStatus.SC_OK}
        catch (Exception e){

        }

    }

    @Test
    public void validateIsActive(){
        def param =[
                "email":"ejBhb3ZlcndmMUBtYWlsaW5hdG9yLmNvbQ==",
                "code":"OWM4NjUzNjItMGNmOC00YmJmLTkwN2YtNTBjNzdmZWJlZTk4"
        ];
      def res=isActive(API_PATH+"/user/email/activate",param)
       assert res.status==403

    }

    @Test
    public void loginSuccess(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
       def login = login(payload)
        assert login.status==HttpStatus.SC_OK
    }


    @Test
    public void pwdRest(){

        def payload=[
                "username": "forever@mailinator.com",  //邮箱或手机号
                "password": "12345678",    //新密码
                "captcha":  "459821"         //验证码
        ]
        def pw = randomString();
        def modifypwd=[
                "oldPassword": "12345678",
                "newPassword": "87654321"
        ]
        if(login(payload).responseData.status==HttpStatus.SC_OK){
            def response=restClient().put(path:API_PATH+"/user/password/reset",contentType: JSON,body: payload)
            response.responseDtat.status==HttpStatus.SC_OK
            print("password reset success and is going to modify password")
            def  modifyres=restClient().put(path: API_PATH+"/user/password/modify",contentType: JSON,body: modifypwd)
            print(modifyres)
            response.modifyres.status==HttpStatus.SC_OK
        }
        else {
            def login = login(payload)
        }

    }

    @Test
    public void pwdModify(){
        def pw = randomString();
        def payload=[
                "oldPassword": "12345678",
                "newPassword": pw
        ]

    }

//    @Test
//   public void delete(){
//    def login=login()
//    def getueserinfo=restClient().get(path: API_PATH+"/user/info")
//    print(restClient())
//    def res=restClient().get(path: API_PATH+"/user/logout",contentType: JSON,headers: getueserinfo.header)
//}



}



