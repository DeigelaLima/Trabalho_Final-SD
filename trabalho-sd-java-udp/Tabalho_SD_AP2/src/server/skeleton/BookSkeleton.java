package server.skeleton;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.model.Book;
import server.model.Usuario;
import server.servant.BookServant;

public class BookSkeleton {

	private BookServant bookServant;

	public BookSkeleton() {
		this.bookServant = new BookServant();
	}

	public String registerBook(String args) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		
		try {
			jsonObject = (JSONObject) parser.parse(args);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Book book = new Book(
			((Long) jsonObject.get("code")).intValue(),
			(String) jsonObject.get("titulo"),
			(String) jsonObject.get("genre"),
			(String) jsonObject.get("author"),
			((Long)  jsonObject.get("num_copies")).intValue()
		);
		
		return bookServant.registerBook(book);
	}
	
	public String listBooks(String args) {
		
		ArrayList<Book> bookList = bookServant.listBooks();
		
		//transformando o array author em string Json
		JSONArray array = new JSONArray();//cria um array vazio em json
	
		for(Book book: bookList) {
			array.add(book.toJson());
		}
        return array.toJSONString();
		
	}
}
