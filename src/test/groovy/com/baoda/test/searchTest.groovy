package com.baoda.test

import groovyx.net.http.HttpResponseException
import org.apache.http.HttpStatus
import org.testng.annotations.Test
import static com.social.credits.test.DataNeedForUse.*
import static com.social.credits.test.FuncTestUtils.*
import static groovyx.net.http.ContentType.JSON
/**
 * Created by admin on 2016/8/10.
 */
class SearchTest {
    @Test
    public void search(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def login=login(payload)
        def load=searchDataForAShare()

       try{
           def res=restClient().post(path:API_PATH+"/report/search",headers:login.responseData.headers,contentType: JSON,body:load)
           print(res.responseData.data.id[0])
           assert res.status==HttpStatus.SC_OK
       } catch (HttpResponseException e){
        resp = e.getResponse();
    }
    }


  /*  @Test
    public void DocDetailInfo(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def docRes;

        def login=login(payload)
        def load=searchDataForAShare()
        def searchRes=restClient().post(path:API_PATH+"/report/search",headers:login.responseData.headers,contentType: JSON,body:load)
      //  print(searchRes.responseData.data.id[0])
       def  param=["keyword":searchDataForAShare().keyword]
        //try{
            docRes = restClient().get(path: API_PATH+"/report/22ca5a1787ed84e7a2ecc0dc8eab8a208bd8bcbf3f6ec7cd120b62bf44edbbb6",param)
            print(docRes)
            assert docRes.status==HttpStatus.SC_OK

      //  }
//         catch (HttpResponseException e){
//             docRes = e.getResponse();
//             print(docRes)
//        }

        
    }*/
}
