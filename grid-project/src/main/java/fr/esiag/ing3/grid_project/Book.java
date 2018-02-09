package fr.esiag.ing3.grid_project;

import java.io.Serializable;

public class Book implements Serializable{
	String title;
	String author;
	
	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}
	public Book() {
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + "]";
	}
	
	
}
