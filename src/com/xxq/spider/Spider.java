package com.xxq.spider;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬取电影信息
 * 
 * @author xu
 *
 */
public class Spider implements Runnable {

	private String url;
	private List<JsonFilm> jsonFilms;
	private JsonFilm jsonFilm;

	public Spider() {
	}

	public Spider(String url, List<JsonFilm> jsonFilms) {
		this.url = url;
		this.jsonFilms = jsonFilms;
	}

	@Override
	public void run() {
		spider();
	}

	public void spider() {
		try {
			Document document = Jsoup.connect(url).get();
			Elements urls = document.select(".grid_view .info a");
			for (Element u : urls) {
				jsonFilm = new JsonFilm();
				String movieURL = u.attr("href");
				Document doc = Jsoup.connect(movieURL).get();
				// 电影名
				String title = doc.title();
				jsonFilm.setTitle(title.substring(0, title.length()));
				// 导演
				jsonFilm.setDirectors(doc.getElementsByAttributeValue("rel", "v:directedBy").first().text());
				// 主演
				jsonFilm.setActors(doc.select("#info .actor").text());
				// 上映时间
				jsonFilm.setScreenTime(doc.getElementsByAttributeValue("property", "v:initialReleaseDate").text());
				// 片长
				jsonFilm.setRuntime(doc.getElementsByAttributeValue("property", "v:runtime").text());
				// IMDb链接
				jsonFilm.setIMDb(doc.select("#info a").last().attr("href"));
				// 海报路径
				String posterURL = doc.select("#mainpic img").first().attr("src");
				jsonFilm.setPosterPath(posterURL);
				// 评分
				jsonFilm.setMark(Double.parseDouble(doc.select(".rating_self strong").text()));
				// 评分人数
				String markcount = doc.select(".rating_people").text();
				jsonFilm.setMarkCount(Integer.parseInt(markcount.substring(0, markcount.length() - 3)));
				jsonFilms.add(jsonFilm);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
