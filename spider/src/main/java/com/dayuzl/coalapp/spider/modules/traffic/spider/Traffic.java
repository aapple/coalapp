package com.dayuzl.coalapp.spider.modules.traffic.spider;

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
@Gecco(matchUrl="http://www.meilaoban.com/index.php/Mobile/RoadCondition/index.html?rid={}", pipelines={"consolePipeline", "trafficPipeline"})
public class Traffic implements HtmlBean {

    private static final long serialVersionUID = -7127412585200687225L;

    @Request
    private HttpRequest request;

    @HtmlField(cssPath=".qian")
    private List<TrafficNode> qianNode;

    @HtmlField(cssPath=".shen")
    private List<TrafficNode> shenNode;

}