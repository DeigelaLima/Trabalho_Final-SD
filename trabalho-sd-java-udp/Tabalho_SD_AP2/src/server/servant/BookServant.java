package server.servant;

import java.util.ArrayList;

import server.model.Book;
import server.model.Usuario;

public class BookServant {
	
	static private ArrayList<Book> books = new ArrayList<>();
	
	public String registerBook(Book book) {
		books.add(book);
		
		
		return "Livro inserido com sucesso, "
				+ " lista de livros cadastrados: "
				+ books.toString();
	}
	
	public ArrayList<Book> listBooks() {

		return books;
	}
	
}
