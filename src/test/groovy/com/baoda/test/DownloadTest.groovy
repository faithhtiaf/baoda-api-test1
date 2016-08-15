package com.baoda.test

import groovyx.net.http.HttpResponseException
import org.apache.http.HttpStatus
import org.testng.annotations.Test
import static com.social.credits.test.FuncTestUtils.*
import static com.social.credits.test.FuncTestUtils.login
import static groovyx.net.http.ContentType.JSON

/**
 * Created by admin on 2016/8/15.
 */
class DownloadTest {
    @Test
    //记录报告下载
    public void downloadReport(){
       // POST /api/download?articleId=被下载的报告id&articleName=被下载的报告标题
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
       def login= login(payload)
        def param=[
                "articleId":"22ca5a1787ed84e7a2ecc0dc8eab8a208bd8bcbf3f6ec7cd120b62bf44edbbb6",
                "articleName":"公司章程（2016修订)"
        ]
        def res=restClient().post(path:"/api/download",headers:login.responseData.headers,query:param)
        print(res.responseData)
        assert res.status==HttpStatus.SC_OK

    }
    @Test
    //统计报告下载次数
    public void countOfDownloadReport(){
        //GET /api/download/count?articleId=报告id
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def login= login(payload)
        def param=["articleId":"22ca5a1787ed84e7a2ecc0dc8eab8a208bd8bcbf3f6ec7cd120b62bf44edbbb6"]
        try{
            def res=restClient().get(path:"/api/download/count",headers:login.responseData.headers ,query: param)
        }catch (HttpResponseException e){
            e.response;
        }
        //print(res)
        assert res.status==HttpStatus.SC_OK
    }
}
