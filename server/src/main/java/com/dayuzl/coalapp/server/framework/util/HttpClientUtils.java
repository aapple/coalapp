package com.dayuzl.coalapp.server.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by Crown-liu
 * Date: 2015/1/15.
 */
public class HttpClientUtils {

    private static final CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";

    static {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(30000)
                .setSocketTimeout(15000)
                .build();
        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .build();
    }

    public static String httpGet(String url){
        return httpGet(url, null,CHARSET);
    }

    public static String httpGet(String url, Map<String, String> params){
        return httpGet(url, params,CHARSET);
    }


    /**
     * @param url http://taobao.com/test.action
     * @param params 参数,编码之前的参数
     * @return
     */
    public static String httpGet(String url, Map<String, String> params,String charset) {
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpget);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpget.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }


    /**
     * 获取URL的查询字符串信息，不会对url进行编码
     * @param url
     * @return
     */
    public static String getQueryString(String url) throws Exception{
        url = URLDecoder.decode(url,CHARSET);
        int index = url.indexOf("?");
        if(index == -1) {
            return StringUtils.EMPTY;
        }
        return StringUtils.substring(url,index + 1);
    }

    /**
     * 获取url查询字符串，以map方式返回，编码方式默认为“utf-8”
     * @param url
     * @return
     * @throws Exception
     */
    public Map<String,String> getQueryParameters(String url) throws Exception{
        List<NameValuePair> pairs = URLEncodedUtils.parse(new URI(url), CHARSET);
        if(CollectionUtils.isEmpty(pairs)) {
            return Collections.EMPTY_MAP;
        }
        Map<String,String> params = new HashMap<>(pairs.size() * 2);
        for (NameValuePair pair : pairs) {
            params.put(pair.getName(),pair.getValue());
        }
        return params;
    }

}