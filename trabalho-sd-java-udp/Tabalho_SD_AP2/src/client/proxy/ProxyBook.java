package client.proxy;

import java.io.ByteArrayOutputStream;
import java.net.SocketTimeoutException;

import org.json.simple.parser.ParseException;

import client.UDPClient;
import client.model.Book;
import client.model.Message;

public class ProxyBook {
	
	private int requestId = 0;
	UDPClient udpclient;
	
	public ProxyBook(UDPClient udpclient) {
		super();
		this.udpclient = udpclient;
	}

	public String registerBook(Book book) {
		Message response = doOperation("Cadastro", "Metodo_cadastro_livro", book.toJson());

		String response_message = null;
		try {
			response_message = response.getArguments().toString();
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


