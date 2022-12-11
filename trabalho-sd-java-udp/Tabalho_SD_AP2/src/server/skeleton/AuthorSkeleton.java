package server.skeleton;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.model.Author;
import server.servant.AuthorServant;

public class AuthorSkeleton {

	private AuthorServant authorServant;
	
	public AuthorSkeleton() {
		this.authorServant = new AuthorServant();
	}
	
	public String registerAuthor(String args) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		
		try {
			jsonObject = (JSONObject) parser.parse(args);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Author author = new Author(
			((Long) jsonObject.get("code")).intValue(),
			(String) jsonObject.get("nome"),
			(String) jsonObject.get("email")
		);
		
		return authorServant.registerAuthor(author);
	}
	
	public String listAuthors(String args) {
		
		ArrayList<Author> authorList = authorServant.listAuthors();
		
		//transformando o array author em string Json
		JSONArray array = new JSONArray();//cria um array vazio em json
	
		for(Author author: authorList) {
			array.add(author.toJson());
		}
        return array.toJSONString();
		
	}
	
	
	
	
	
	
	

}
