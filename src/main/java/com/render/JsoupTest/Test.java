package com.render.JsoupTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// Jsoup 学习地址： http://www.huqiwen.com/2012/05/03/use-jsoup-analytics-html-document/#

/**
 * Created by Tonlin on 2016/5/8.
 */
public class Test {

    public static void main(String[] args) {
        getDianpingShop();
    }

    private static void getDianpingShop() {
        try {
            String url = "http://www.dianping.com/shop/28611432";
            Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(10000).get();
            Element shopName = doc.select("h1[class=shop-name]").first();
            System.out.println("店名：" + shopName.text().trim());
            Element evaluate = doc.select("div[class=brief-info]").first();
            System.out.print("评价：" + evaluate.select("span[class=mid-rank-stars mid-str50]").first().attr("title"));
            for (Element e : evaluate.select("span[class=item]"))
                System.out.print("    " + e.text().toString().trim());
            System.out.println();
            Element address = doc.select("div[class=expand-info address]").first();
            System.out.println("地址：" + address.select("span[itemprop=locality region]").first().text() + address.select("span[itemprop=street-address]").first().text());
            Element tel = doc.select("p[class=expand-info tel]").first();
            System.out.println(tel.text());
            String recommend = "";
            Elements recommendNames = doc.select("div[class=comment-condition J-comment-condition Fix] a");
            for (Element ee : recommendNames)
                recommend += ee.text().toString().trim() + ";";
            System.out.println(recommend);
//            url = "http://www.dianping.com/ajax/json/shop/wizard/BasicHideInfoAjaxFP?shopId=28611432&_nr_force="+System.currentTimeMillis();
//            Document doc1 = Jsoup.connect(url).ignoreContentType(true).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(10000).get();
//            JSONObject obj = JSONObject.parseObject(doc1.body().text().toString().trim()).getJSONObject("msg");
//            JSONObject shop = obj.getJSONObject("shopInfo");
//            System.out.println(shop.getString("shopName"));
//            url = "http://www.dianping.com/search/category/14/10/o3";
//            Document doc2 = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(10000).get();
//            Elements shop = doc2.select("div[id=shop-all-list] li");
//            for (Element e : shop) {
//                Element x = e.select("div[class=tit] a").first();
//                Element x2 = e.select("div[class=tag-addr] a").first();
//                System.out.println(x.text() + "  url：" + x.attr("href") + "  type：" + x2.text().toString().trim());
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void getBaiduShop() {
        try {
            String url = "https://fz.nuomi.com/326?async_load_page=1&_=" + System.currentTimeMillis();
            Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(10000).get();
            Elements shop = doc.getElementsByTag("li");
            String key = "";
            for (Element e : shop) {
                System.out.println(e.text());
                Element x = e.select("a").first();
                if (x == null)
                    continue;
                System.out.println("url：" + x.attr("href") + "  key：" + x.attr("data-topten").replace("v=", ""));
                if ("".equals(key)) {
                    url = x.attr("href");
                    if (!url.startsWith("http"))
                        url = "https:" + url;
                    key = x.attr("data-topten").replace("v=", "");
                    break;
                }
            }
            Document doc1 = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(10000).get();
            Elements type = doc1.select("div[class=p-detail] li");
            System.out.println(type.get(2).text().replace(">", "").trim());
            url = "https://www.nuomi.com/pcindex/main/shopchain?dealId=" + key + "&pageSize=4&pn=1&cityCode=&districtId=&_=1462875422881";
            Document doc2 = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(10000).get();
            JSONObject obj = JSONObject.parseObject(doc2.body().text().toString().trim()).getJSONObject("data");
            JSONArray shopObj = obj.getJSONArray("shop");
            if (shopObj != null && shopObj.size() > 0) {
                for (int i = 0; i < shopObj.size(); i++) {
                    JSONObject o = shopObj.getJSONObject(i);
                    System.out.println(o.getString("name"));
                    if (i == 0) {
                        url = o.getString("link");
                        if (!url.startsWith("http"))
                            url = "https:" + url;
                        break;
                    }
                }
                Document doc3 = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(10000).get();
                System.out.println("评分：" + doc3.select("span[class=score]").text() + " " + doc3.select("span[class=evaluate]").text()
                        + " " + doc3.select("span[class=price]").first().text());
                Element evaluate = doc3.getElementById("j-label-list");
                System.out.println(evaluate.text());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void getMeituanShop() {
        try {
            String url = "https://fz.nuomi.com/326?async_load_page=1&_=" + System.currentTimeMillis();
            Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(10000).get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
