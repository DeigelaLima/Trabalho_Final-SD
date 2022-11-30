package server;

import server.model.Message;
import server.skeleton.BookSkeleton;

public class Dispatcher {
	
	BookSkeleton bookSkeleton;
	
    public Dispatcher() {
    	bookSkeleton = new BookSkeleton();
	}

	public Message selectSkeleton(Message request) {
		
		switch (request.getObjectReference()) {
		case "Cadastro": 
			if (request.getMethod().equals("Metodo_cadastro_livro")) {
				String skeletonResult = bookSkeleton.registerBook(request.getArguments());
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
