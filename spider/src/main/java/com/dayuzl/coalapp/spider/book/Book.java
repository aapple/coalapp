package com.dayuzl.coalapp.spider.book;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
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
@Gecco(matchUrl="http://www.wuxidfgX", pipelines={"consolePipeline", "bookPipeline"})
public class Book implements HtmlBean {

    @Request
    private HttpRequest request;

    @Text
    @HtmlField(cssPath="a")
    private String index;

    @Text
    @HtmlField(cssPath="a")
    private String introduction;

//    @HtmlField(cssPath="#menu-item-12207 > ul li")
//    private List<SubBook> subBooks;\

    @HtmlField(cssPath="#menu-item-12207 > ul li")
    private List<String> subBookNames;

    @HtmlField(cssPath="#menu-item-12207 > ul li")
    private List<ChapterNode> chapterNodes;
}
