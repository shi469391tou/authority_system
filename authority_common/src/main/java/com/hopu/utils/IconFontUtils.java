package com.hopu.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图标工具类
 * @author Administrator
 *
 */
public class IconFontUtils {

	/**
	 * 获得图标
	 * @return
	 */
	public final static List<String> getIconFont(){
		List<String> data = new ArrayList<String>();
		try {
			// 读取静态资源文件夹下的demo_index.html文件
			Document document = Jsoup.parse(new File(PathUtils.getWebAppPath("static/iconfont/demo_index.html")), "utf-8");
			// 获取文档对象中所有的font-class类元素
			Element element = document.getElementsByClass("font-class").get(0);
			// 获取文档对象中所有的dib类元素
			Elements elements = element.getElementsByClass("dib");
			// 遍历处理，然后添加到list集合中
			for (Element element2 : elements) {
				data.add(element2.getElementsByClass("code-name").text().replace(".", ""));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
