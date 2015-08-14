package com.djw.web.common;

public class JsonWebResponse {

	private String error;
	private boolean success;

	JsonWebResponse(boolean success) {
		this.success = success;
		this.error = null;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
