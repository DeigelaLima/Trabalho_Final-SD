package server.servant;

import java.util.ArrayList;

import server.model.Book;

public class BookServant {
	
	static private ArrayList<Book> books = new ArrayList<>();
	
	public String registerBook(Book book) {
		books.add(book);
		System.out.println(books);
		
		return "Livro inserido com sucesso, "
				+ " lista de livros cadastrados: "
				+ books.toString();
	}
}
