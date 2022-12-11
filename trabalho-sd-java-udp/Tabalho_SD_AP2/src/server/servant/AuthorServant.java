package server.servant;

import java.util.ArrayList;

import server.model.Author;


public class AuthorServant {

	static private ArrayList<Author> authors = new ArrayList<>();
	
	public String registerAuthor(Author author) {
		authors.add(author);
		System.out.println(authors);
		
		return "Autor inserido com sucesso, "
				+ " lista de autores cadastrados: "
				+ authors.toString();
	}
	
	public ArrayList<Author> listAuthors() {

		return authors;
	}
	
	
	

}
