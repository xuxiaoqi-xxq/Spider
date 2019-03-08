package com.xxq.spider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class app {
	public static void main(String[] args) {
		List<JsonFilm> jsonFilms = Collections.synchronizedList(new LinkedList<>());
		ExecutorService pool = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			String url = String.format("https://movie.douban.com/top250?start=%d&filter=", 25 * i);
			pool.execute(new Spider(url, jsonFilms));
		}
		pool.shutdown();

		// 所有线程结束后，写json
		while (true) {
			if (pool.isTerminated()) {
				writeData(jsonFilms);
				break;
			} else {
				try {
					// 防止cpu占用
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将信息转化为json格式写入本地文件，并下载海报
	 * 
	 * @param jsonFilms
	 */
	private static void writeData(List<JsonFilm> jsonFilms) {
		String json = new Gson().toJson(jsonFilms);
		try {
			FileWriter writer = new FileWriter(new File("top250films.json"));
			writer.write(json);
			writer.close();
			System.out.println("写 json 完毕");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExecutorService pool = Executors.newFixedThreadPool(8);
		for (JsonFilm jsonFilm : jsonFilms) {
			pool.execute(new ImgLoader(jsonFilm));
		}
		pool.shutdown();
		System.out.println("海报下载完毕");
	}
}
