package com.dayuzl.coalapp.spider.book;

import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
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
public class ChapterNode implements HtmlBean {

    @Text
    @HtmlField(cssPath="a")
    private String name;

    @Href(click=false)
    @HtmlField(cssPath="a")
    private String href;
}
