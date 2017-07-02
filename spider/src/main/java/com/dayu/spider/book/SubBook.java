package com.dayu.spider.book;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * Created by bin.yao on 2017/4/20.
 */
public class SubBook implements HtmlBean {

    @Text
    @HtmlField(cssPath="a")
    private String name;

    @HtmlField(cssPath="#menu-item-12207 > ul li")
    private List<ChapterNode> chapterNodes;
}
