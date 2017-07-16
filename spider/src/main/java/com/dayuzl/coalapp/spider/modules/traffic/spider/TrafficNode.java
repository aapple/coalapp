package com.dayuzl.coalapp.spider.modules.traffic.spider;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
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
public class TrafficNode implements HtmlBean{

    private static final long serialVersionUID = 3018760488621382659L;

    @Text
    @HtmlField(cssPath="td")
    private List<String> data;

}
