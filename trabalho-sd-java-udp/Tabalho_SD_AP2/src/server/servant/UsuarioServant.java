package server.servant;

import java.util.ArrayList;

import server.model.Author;
import server.model.Usuario;

public class UsuarioServant {

static private ArrayList<Usuario> usuarios = new ArrayList<>();
	
	public String registerUsuario(Usuario usuario) {
		
			usuarios.add(usuario);
		
			return "Usuário inserido com sucesso, "
					+ " lista de usuários cadastrados: "
					+ usuarios.toString();

	}
	
	public ArrayList<Usuario> listUsuarios() {

		return usuarios;
	}
	
	public String deleteUsuario(int usuarioCode) {
		
		for(int i=0; i < usuarios.size();i++) {
			if(usuarios.get(i).getCode() == usuarioCode) {
				System.out.println(usuarios.get(i).getNome());
				usuarios.remove(i);
				return "Removido com sucesso!";
			}
		}
		
		return "Usuário não encontrado :(";
		
	}
}
