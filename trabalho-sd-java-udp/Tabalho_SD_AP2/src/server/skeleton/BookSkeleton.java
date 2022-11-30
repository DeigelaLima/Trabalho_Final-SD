package server.skeleton;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.model.Book;
import server.model.Message;
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
			(String) jsonObject.get("genre"),
			(String) jsonObject.get("author"),
			((Long)  jsonObject.get("num_copies")).intValue()
		);
		
		return bookServant.registerBook(book);
	}
}
