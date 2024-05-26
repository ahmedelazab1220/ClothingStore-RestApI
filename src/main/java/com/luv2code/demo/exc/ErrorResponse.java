package com.luv2code.demo.exc;

import java.util.List;

public class ErrorResponse {

	private int status;

	private String title;

	private String message;

	private List<String> detail;

	private String path;

	private long timeStamp;

	public ErrorResponse() {

	}

	public ErrorResponse(int status, String title, String message, List<String> detail, String path, long timeStamp) {
		this.status = status;
		this.title = title;
		this.message = message;
		this.detail = detail;
		this.path = path;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetail() {
		return detail;
	}

	public void setDetail(List<String> detail) {
		this.detail = detail;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
