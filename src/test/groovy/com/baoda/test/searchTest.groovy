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
    //搜索
    public void search(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def login=login(payload)
        def load=searchDataForAShare()

       try{
           def res=restClient().post(path:API_PATH+"/report/search",headers:login.responseData.headers,contentType: JSON,body:load)
           assert res.status==HttpStatus.SC_OK
       } catch (HttpResponseException e){
        res = e.getResponse();
    }
    }


   @Test
   //获取文档信息
    public void DocDetailInfo(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def docRes;

        def login=login(payload)
        def load=searchDataForAShare()
        def searchRes=restClient().post(path:API_PATH+"/report/search",headers:login.responseData.headers,contentType: JSON,body:load)
        for(i in 0..9){
            def id=searchRes.responseData.data.id[i]
            print(searchRes.responseData.data.id[i]+"\n")
               docRes = restClient().get(path: API_PATH+"/report/"+id,headers:login.responseData.headers,queryString :"keyword=股东大会")
               assert docRes.status==HttpStatus.SC_OK
        }
    }





    @Test
    //获取章节信息 (fail 掉，等待修改)
    public void  chapterInfo(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
//        def query=[
//                "keyword":login.keyword
//        ]
        //登陆
        def login=login(payload)
        def load=searchDataForAShare()
        //搜索
        def searchRes=restClient().post(path:API_PATH+"/report/search",headers:login.responseData.headers,contentType: JSON,body:load)
        def id=searchRes.responseData.data.id[0]
        print(searchRes.responseData.data.id[0])
        def reqBody=["text":"公司或公司的子公司（包括公司的附属企业）不以赠与、垫资、担保、补偿或贷款等形式，对购买或者拟购买公司股份的人提供任何资助",
                     "page":5]

        res=restClient().post(path: API_PATH+"/report/"+id+"/outline",headers:login.responseData.headers,contentType: JSON,body: reqBody)
        print(res.responseData)


    }


    @Test
            //页内关键字查找
    public void  searchKeyWordInDoc(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def login=login(payload)
        def load=searchDataForAShare()
        def searchRes=restClient().post(path:API_PATH+"/report/search",headers:login.responseData.headers,contentType: JSON,body:load)
        def id=searchRes.responseData.data.id[0]
       // print(id)
        def res=restClient().post(path: API_PATH+"/report/"+id+"/search",headers:login.responseData.headers,contentType: JSON,queryString: "keyword=股东大会"  )
       // print(res.responseData)
        res.status==HttpStatus.SC_OK

    }

    //记录搜索历史
    @Test
    public void searchHistory(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def load=[
                "market_id": "市场类型id",
                "market_name": "市场类型名称",
                "notice_id": "公告类型id",
                "notice_name": "公告类型名称",
                "plate_id": "板块类型id",
                "plate_name": "板块类型名称",
                "industry_id": "行业类型id",
                "industry_name": "行业类型名称",
                "region_id": "地区类型id",
                "region_name": "地区类型名称",
                "stock_code": "股票代码",
                "title_must": "标题必须包含关键字",
                "title_possible": "标题可能包含关键字",
                "title_cannot": "标题不能包含关键字",
                "content_must": "内容必须包含关键字",
                "content_possible": "内容可能包含关键字",
                "content_cannot": "内容不能包含关键字",
                "start_date": "开始日期",
                "end_date": "结束日期",
        ]
        def login=login(payload)
        def res=restClient().post(path: API_PATH+"/search/history",headers: login.reponseData.headers,contentType: JSON,body: load)
    }


}
