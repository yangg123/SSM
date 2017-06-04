package com.render;

import com.ssm.dao.JobDao;
import com.ssm.dao.impl.JobDaoImpl;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;

/**
 * Created by yg on 2017/5/2.
 */
public class JobProcessor implements PageProcessor {

    //抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);
    //用户数量
    private static int num = 0;

    //数据库持久化对象，用于将用户信息存入数据库
    private JobDao zhihuDao = new JobDaoImpl();

    @Override
    public void process(Page page) {
        String name = page.getHtml().xpath("//div[@class='title-section ellipsis']/span[@class='name']/text()").get();
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
        long startTime ,endTime;
        System.out.println("========51Job信息小爬虫【启动】喽！=========");
        startTime = new Date().getTime();
        Spider.create(new ZhiHuUserPageProcessor()).addUrl("http://search.51job.com/list/110200,000000,0000,00,9,99,%2B,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=").thread(5).run();
        endTime = new Date().getTime();
        System.out.println("========51Job用户信息小爬虫【结束】喽！ =========");
        System.out.println("一共爬到"+num+"个用户信息！用时为："+(endTime-startTime)/1000+"s");

    }
}
