package com.xxq.spider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 海报下载
 * 
 * @author xu
 *
 */
public class ImgLoader implements Runnable {

	private JsonFilm jsonFilm;

	public ImgLoader() {
	}

	public ImgLoader(JsonFilm jsonFilm) {
		this.jsonFilm = jsonFilm;
	}

	@Override
	public void run() {
		File dir = new File("img");
		if (!dir.exists()) {
			dir.mkdir();
		}
		String imgName = String.format("%s.jpg", jsonFilm.getTitle().split(" ")[0]);
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(dir, imgName)))) {
			byte[] data = new OkHttpClient.Builder().build()
					.newCall(new Request.Builder().url(jsonFilm.getPosterPath()).build()).execute().body().bytes();
			out.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
