package server;

import server.model.Message;
import server.skeleton.AuthorSkeleton;
import server.skeleton.BookSkeleton;
import server.skeleton.UsuarioSkeleton;

public class Dispatcher {
	
	BookSkeleton bookSkeleton;
	AuthorSkeleton authorSkeleton;
	UsuarioSkeleton usuarioSkeleton;
	
    public Dispatcher() {
    	bookSkeleton = new BookSkeleton();
    	authorSkeleton = new AuthorSkeleton();
    	usuarioSkeleton = new UsuarioSkeleton();
	}

	public Message invoke(Message request) {
		
		switch (request.getObjectReference()) {
		case "Cadastro": 
			if (request.getMethod().equals("Metodo_cadastro_livro")) {
				String skeletonResult = bookSkeleton.registerBook(request.getArguments());
				Message response = request;
				response.setArguments(skeletonResult);
				return response;
			}
			if (request.getMethod().equals("Metodo_cadastro_author")) {
				String skeletonResult = authorSkeleton.registerAuthor(request.getArguments());
				Message response = request;
				response.setArguments(skeletonResult);
				return response;
			}
			if (request.getMethod().equals("Metodo_cadastro_usuario")) {
				String skeletonResult = usuarioSkeleton.registerUsuario(request.getArguments());
				Message response = request;
				response.setArguments(skeletonResult);
				return response;
			}
		case "List":
			if (request.getMethod().equals("Metodo_listar_authors")) {
				String skeletonResult = authorSkeleton.listAuthors(request.getArguments());
				Message response = request;
				response.setArguments(skeletonResult);
				return response;
			}
			if (request.getMethod().equals("Metodo_listar_usuarios")) {
				String skeletonResult = usuarioSkeleton.listUsarios(request.getArguments());
				Message response = request;
				response.setArguments(skeletonResult);
				return response;
			}
			if (request.getMethod().equals("Metodo_listar_books")) {
				String skeletonResult = bookSkeleton.listBooks(request.getArguments());
				Message response = request;
				response.setArguments(skeletonResult);
				return response;
			}
		case "Delete":
			if (request.getMethod().equals("Metodo_deletar_usuario")) {
				String skeletonResult = usuarioSkeleton.deleteUsuarios(request.getArguments());
				Message response = request;
				response.setArguments(skeletonResult);
				return response;
			}
			
		break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + request.getObjectReference());
		}
		
		return null;
    }
}
