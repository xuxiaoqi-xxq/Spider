package com.xxq.spider;

/**
 * 电影信息
 * 
 * @author xu
 *
 */
public class JsonFilm {
	// 电影名
	private String title;
	// 导演
	private String directors;
	// 演员
	private String actors;
	// 上映时间
	private String screenTime;
	// 片长
	private String runtime;
	// IMDb链接
	private String IMDb;
	// 海报路径
	private String posterPath;
	// 评分
	private double mark;
	// 评分人数
	private int markCount;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getScreenTime() {
		return screenTime;
	}

	public void setScreenTime(String screenTime) {
		this.screenTime = screenTime;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getIMDb() {
		return IMDb;
	}

	public void setIMDb(String iMDb) {
		IMDb = iMDb;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public int getMarkCount() {
		return markCount;
	}

	public void setMarkCount(int markCount) {
		this.markCount = markCount;
	}

}
