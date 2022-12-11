package server.model;

import org.json.simple.JSONObject;

public class Book {
	private int code;
	private String genre;
	private String titulo;
	private String author;
	private int num_copies;

	public Book(int code, String titulo, String genre, String author, int num_copies) {
		super();
		this.code = code;
		this.titulo = titulo;
		this.genre = genre;
		this.author = author;
		this.num_copies = num_copies;
	}

	public String toJson() {
		JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("titulo", titulo);
        obj.put("genre", genre);
        obj.put("author", author);
        obj.put("num_copies", num_copies);
		return obj.toJSONString();
	}

	public Book RegisterBook() {
		return this;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getNum_copies() {
		return num_copies;
	}
	public void setNum_copies(int num_copies) {
		this.num_copies = num_copies;
	}

	@Override
	public String toString() {
		System.out.println("asdnn");
		return "Book [code=" + code + ", titulo=" + titulo + ", genre=" + genre + ", author=" + author + ", num_copies=" + num_copies + "]";
	}
}
