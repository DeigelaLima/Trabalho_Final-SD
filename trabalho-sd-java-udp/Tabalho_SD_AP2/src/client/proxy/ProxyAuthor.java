package client.proxy;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import client.UDPClient;
import client.model.Author;
import client.model.Message;
import server.skeleton.AuthorSkeleton;

public class ProxyAuthor {

	private int requestId = 1;
	UDPClient udpclient;
	
	public ProxyAuthor(UDPClient udpclient) {
		super();
		this.udpclient = udpclient;
	}
	
	public String registerAuthor(Author author) {
		Message responseAuthor = doOperation("Cadastro", "Metodo_cadastro_author", author.toJson());

		String response_message = null;
		try {
			response_message = responseAuthor.getArguments().toString();
		} catch (java.lang.NullPointerException e) {
			System.out.println("Servidor não respondeu!, Tente novamente mais tarde.");
		}
		return response_message;
	}
	
	public String listAuthors(){
		Message responseListAuthor = doOperation("List", "Metodo_listar_authors", "");

		String response_message = null;
		try {
			response_message = responseListAuthor.getArguments();
			System.out.println(response_message);
		} catch (java.lang.NullPointerException e) {
			System.out.println("Servidor não respondeu!, Tente novamente mais tarde.");
		}
		return response_message;
		
	}
	
	private Message doOperation(String objectRef, String method, String args) {

		byte[] packedMessage = packMessage(objectRef, method, args);
		udpclient.sendRequest(packedMessage);
		//udpclient.sendRequest("oi tudo bem".getBytes());

		Message reply = null;
		for (int i = 0; i < 3; i++) {
			try {
				byte[] packedReply = udpclient.getReply();
				reply = unpackMessage(packedReply);
				break; // sai do for se operação tem sucesso
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SocketTimeoutException e) {
				udpclient.sendRequest(packedMessage); // tenta de novo
			}
		}

		requestId += 1;
		return reply;
	}
	
	
	private byte[] packMessage(String objectRef, String method, String arguments) {
		Message message = new Message(
			0, // messageType
			requestId,
			objectRef,
			method,
			arguments
		);
		return message.toJson().getBytes();
	}
	
	
	private Message unpackMessage(byte[] args) throws ParseException {
		String jsonMessage = new String (args);
		Message response = Message.buildFromJson(jsonMessage);
		return response;
	}

}
