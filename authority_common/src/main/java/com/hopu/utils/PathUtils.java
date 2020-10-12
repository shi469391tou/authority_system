package com.hopu.utils;

public class PathUtils {

	/**
	 * 获得webapp目录
	 * @return
	 */
	public final static String getWebAppPath(String path){
		return getClassPath("").replaceFirst("WEB-INF/classes/", "") + path;
	}

	/**
	 * 获得classPath目录
	 * @param path
	 * @return
	 */
	public final static String getClassPath(String path){
		return PathUtils.class.getResource("/").getPath().replaceFirst("/", "") + path;
	}
}
