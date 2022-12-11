package server.skeleton;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.model.Author;
import server.model.Usuario;
import server.servant.UsuarioServant;

public class UsuarioSkeleton {
	
	private UsuarioServant usuarioServant;
	
	public UsuarioSkeleton() {
		this.usuarioServant = new UsuarioServant();
	}

	public String registerUsuario(String args) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		
		try {
			jsonObject = (JSONObject) parser.parse(args);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Usuario usuario = new Usuario(
			((Long) jsonObject.get("code")).intValue(),
			(String) jsonObject.get("cpf"),
			(String) jsonObject.get("nome"),
			(String) jsonObject.get("email"),
			((String)  jsonObject.get("telefone"))
		);
		
		return usuarioServant.registerUsuario(usuario);
	}
	
	public String listUsarios(String args) {
		
		ArrayList<Usuario> usuarioList = usuarioServant.listUsuarios();
		
		//transformando o array usuario em string Json
		JSONArray array = new JSONArray();//cria um array vazio em json
	
		for(Usuario usuario: usuarioList) {
			array.add(usuario.toJson());
		}
        return array.toJSONString();
		
	}
	
	public String deleteUsuarios(String args) {
	
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		
		try {
			jsonObject = (JSONObject) parser.parse(args);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int usuarioCode = ((Long) jsonObject.get("code")).intValue();
		
		return usuarioServant.deleteUsuario(usuarioCode);
		
	}
}
