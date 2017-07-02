package com.skyblue.coalapp.server.utilservice.controller;

import com.skyblue.coalapp.server.framework.util.HttpClientUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yao on 2017/7/2.
 */
@RestController
@RequestMapping("/app/wechatUtil")
public class WechatUtilController {

    @RequestMapping("/loadPageByUrl")
    private String loadPageByUrl(@RequestBody String url){

        String result = HttpClientUtils.httpGet(url);
        result = result.replaceAll("data-src=\"http:", "src=\"https:");
        result = result.replaceAll("0\\?wx_fmt=jpeg", "640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
        result = result.replaceAll("0\\?wx_fmt=png", "640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
        return result;
    }
}
