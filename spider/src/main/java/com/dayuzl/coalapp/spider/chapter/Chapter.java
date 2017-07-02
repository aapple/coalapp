package com.dayuzl.coalapp.spider.chapter;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by bin.yao on 2017/4/20.
 */
@Getter
@Setter
@NoArgsConstructor
@Gecco(matchUrl="http://www.wuxiaworld.com/cdindex-html/{}/", pipelines={"chapterPipeline"})
public class Chapter implements HtmlBean {

    @Request
    private HttpRequest request;
    @Text
    @HtmlField(cssPath=".entry-header .entry-title")
    private String name;

    @HtmlField(cssPath="div[itemprop='articleBody']")
    private String body;

}