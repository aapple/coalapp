package com.dayu.spider.home;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by bin.yao on 2017/4/20.
 */
@Getter
@Setter
@NoArgsConstructor
@Gecco(matchUrl="http://www.wuxiaworld.com", pipelines={"consolePipeline", "homePipeline"})
public class Home implements HtmlBean {

    private static final long serialVersionUID = -7127412585200687225L;

    @Request
    private HttpRequest request;

    @HtmlField(cssPath="#menu-item-12207 > ul li")
    private List<BookNode> bookNode;

}