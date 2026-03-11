package com.example.model;

public class MusicianVO {
	private Integer id;
	private String name;
	private String genre;

	public MusicianVO() {}

	public Integer getId() { return id; };
	public void setId(Integer id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getGenre() { return genre; }
	public void setGenre(String genre) { this.genre = genre; }
}
