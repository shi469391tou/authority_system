package com.hopu.utils;

/**
 * json工具类
 */
public class ResponseEntity {

	private boolean result = true;
	private String msg = "操作成功";
	private Object data;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResponseEntity success(){
		return new ResponseEntity();
	}

	public static ResponseEntity success(String msg){
		ResponseEntity entity = new ResponseEntity();
		entity.msg = msg;
		return entity;
	}

	public static ResponseEntity success(Object data){
		ResponseEntity entity = new ResponseEntity();
		entity.data = data;
		return entity;
	}

	public static ResponseEntity success(String msg, Object data){
		ResponseEntity entity = new ResponseEntity();
		entity.msg = msg;
		entity.data = data;
		return entity;
	}

	public static ResponseEntity error(){
		ResponseEntity entity = new ResponseEntity();
		entity.result = false;
		entity.msg = "操作失败";
		return entity;
	}

	public static ResponseEntity error(String msg){
		ResponseEntity entity = new ResponseEntity();
		entity.result = false;
		entity.msg = msg;
		return entity;
	}

	public static ResponseEntity error(Object data){
		ResponseEntity entity = new ResponseEntity();
		entity.result = false;
		entity.data = data;
		return entity;
	}

	public static ResponseEntity error(String msg, Object data){
		ResponseEntity entity = new ResponseEntity();
		entity.result = false;
		entity.msg = msg;
		entity.data = data;
		return entity;
	}
}
