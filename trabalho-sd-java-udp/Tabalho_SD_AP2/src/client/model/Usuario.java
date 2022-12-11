package client.model;

import org.json.simple.JSONObject;

public class Usuario {

	private int code;
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private int codigoDelete;
	
	
	public Usuario(int code, String cpf, String nome, String email, String telefone) {
		super();
		this.code = code;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Usuario(int codigo) {
		super();
		this.codigoDelete = codigo;
	}
	
	public String toJson() {
		JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("cpf", cpf);
        obj.put("nome", nome);
        obj.put("email", email);
        obj.put("telefone", telefone);
		return obj.toJSONString();
	}
	public String codDeletetoJson() {
		JSONObject obj = new JSONObject();
        obj.put("code", codigoDelete);
		return obj.toJSONString();
	}
	
	public Usuario RegisterUsuario() {
		return this;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getCodigoDelete() {
		return codigoDelete;
	}

	public void setCodigoDelete(int codigoDelete) {
		this.codigoDelete = codigoDelete;
	}
	
	
}
