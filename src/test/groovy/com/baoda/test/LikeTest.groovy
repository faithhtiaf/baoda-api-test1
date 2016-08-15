package com.baoda.test

import org.testng.annotations.Test

import static com.social.credits.test.FuncTestUtils.login
import static com.social.credits.test.FuncTestUtils.restClient

/**
 * Created by admin on 2016/8/15.
 */
class LikeTest {
    //点赞
    @Test
    public void like(){
        //POST /api/like?objectId=被点赞对象的id&likeType=被点赞对象的类型
        def payload=[
                "username": "forever@mailinator.com",
                "password": "12345678"
        ]
        def login= login(payload)
        def param=["objectId":"22ca5a1787ed84e7a2ecc0dc8eab8a208bd8bcbf3f6ec7cd120b62bf44edbbb6",
        "likeType":""]
        //def res=restClient().post(path:"/api/like",)

    }
    //点赞q取消
    @Test
    public void cancleLicked(){
        //DELETE /api/like?objectId=被点赞对象的id

    }
    @Test
//  统计点赞数  、
    public void countOfLicked(){
  //GET /api/like/count?objectId=被点赞对象的id
    }
}
