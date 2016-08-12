package com.baoda.test

import org.testng.annotations.Test

import static com.social.credits.test.FuncTestUtils.API_PATH
import static com.social.credits.test.FuncTestUtils.login
import static com.social.credits.test.FuncTestUtils.restClient
import static groovyx.net.http.ContentType.JSON

/**
 * Created by admin on 2016/8/10.
 */
class NoteTest {
    @Test
    //写笔记
    public void writeNote(){
        def load=[
                "articleId": "22ca5a1787ed84e7a2ecc0dc8eab8a208bd8bcbf3f6ec7cd120b62bf44edbbb6",
                "articleName": "报告标题",
                "chapterId": "章id",
                "chapterName": "章名称",
                "sectionId": "节id",
                "sectionName": "节名称",
                "pageNumber": 12,
                "position": 20,
                "original": "选中的原始文字",
                "content": "笔记内容",
                "isPublic": true
        ]
    }

    @Test
    //分页查询某个报告上公开的笔记
    public void getPublicNoteList(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def docRes;

        def login=login(payload)
        def res=restClient().get(path:API_PATH+"/note/page/40289ff45673f70601567caf57bf00d1",headers:login.responseData.headers)
        print(res.responseData)

    }
    @Test
    //查询笔记及其相关评论
    public void getNote(){
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def login=login(payload)
        def param=["id":"40289ff45673f70601567caf57bf00d1",
        "size":10]
        def res=restClient().get(path:API_PATH+"/note/page/40289ff45673f70601567caf57bf00d1",headers:login.responseData.headers,contentType: JSON,query:param)
        print(res.responseData)
    }

}
