package main;

// 从服务器提供服务的实体。它包含所有与视频有关的信息。
public class Video {

	private final Integer id;
	private final String title;
	private final Integer length;
	private final String description;
	private final String director;
	private String language;
	
	public Video(Integer id, String title, Integer length, String description, String director, String language) {
		this.id = id;
		this.title = title;
		this.length = length;
		this.description = description;
		this.director = director;
		this.language = language;
	}
	
	@Override
	public String toString() {
	    return "{"
	        + "\"id\": " + id + ","
	        + "\"title\": \"" + title + "\","
	        + "\"length\": " + length + ","
	        + "\"description\": \"" + description + "\","
	        + "\"director\": \"" + director + "\","
	        + "\"language\": \"" + language + "\","
	        + "}";
	}
}
