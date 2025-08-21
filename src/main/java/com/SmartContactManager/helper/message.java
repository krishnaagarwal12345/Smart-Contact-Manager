package com.SmartContactManager.helper;

public class message {
	private String content;
	private String type;
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	public message(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}
	@Override
	public String toString() {
		return "message [content=" + content + ", type=" + type + "]";
	}
	

}
