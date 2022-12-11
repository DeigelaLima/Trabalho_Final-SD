package client.model;

import org.json.simple.JSONObject;

public class Author {

	private int code;
	private String nome;
	private String email;
	
	
	public Author(int code, String nome, String email) {
		super();
		this.code = code;
		this.nome = nome;
		this.email = email;
		
	}
	
	public String toJson() {
		JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("nome", nome);
        obj.put("email", email);
		return obj.toJSONString();
	}
	
	public Author RegisterAuthor() {
		return this;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
