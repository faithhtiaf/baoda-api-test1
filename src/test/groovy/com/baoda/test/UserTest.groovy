package com.baoda.test

import org.testng.annotations.Test

import static com.social.credits.test.FuncTestUtils.API_PATH
import static com.social.credits.test.FuncTestUtils.restClient

/**
 * Created by admin on 2016/8/5.
 */
@Test
class UserTest {

    @Test
    public void login() {
        restClient().get(path: API_PATH + "/captcha/pic");
        assert 1 == 1;
    }
}
