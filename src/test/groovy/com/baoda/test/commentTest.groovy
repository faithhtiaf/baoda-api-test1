package com.baoda.test

import org.apache.http.HttpStatus

import static groovyx.net.http.ContentType.JSON
import org.testng.annotations.Test

import static com.social.credits.test.FuncTestUtils.login
/**
 * Created by admin on 2016/8/15.
 */
class commentTest {
//    写评论
    @Test
    public void writeComment(){
        //    POST /api/comment header content-type application/json
        def payload=[
                "username": "gang.zou@socialcredits.cn",
                "password": "123456789"
        ]
        def login= login(payload)
        print(login.responseData.headers)
        def param=["objectId":"ff80818156494127015649501a900003",
                   "type":"NOTE",
                   "content": "asdfasdf"]
        def res=restClient().post(path:"/api/comment",headers:login.responseData.headers,contentType:JSON,body:param)
        res.status==HttpStatus.SC_OK
    }


//删除评论
    @Test
    public void deleteComment(){
        //DELETE /api/comment?id=评论id
    }


    //分页查询当前用户的评论
    @Test
    public void searchCommentOfCurrentUser(){
        //GET /api/comment/mine/page?index=1&size=10
    }


    @Test
    //分页查询笔记下的评论
    public void searchCommentOfNote(){
//GET /api/comment/note/page?objectId=被评论对象id&index=1&size=10

    }


}

import static com.social.credits.test.FuncTestUtils.restClient
