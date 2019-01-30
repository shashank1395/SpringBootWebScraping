package com.boot.webscraping.model;

public class NewScrapperItem {

	private String title;
	private String author;
	private String description;

	public NewScrapperItem() {
		super();
	}

	public NewScrapperItem(String title, String author, String description) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
