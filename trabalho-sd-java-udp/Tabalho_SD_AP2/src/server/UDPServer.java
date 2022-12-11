package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;

import server.model.Message;
import server.Dispatcher;

public class UDPServer {

	public static void main(String args[]) throws IOException {
		System.out.println("iniciando servidor");
		int port = 6799;
		DatagramSocket clientSocket = new DatagramSocket(port);
		DatagramPacket request = null;
		DatagramPacket reply = null;

		
		//salva todas as msgs recebida pelo servidor,
		//evita que uma msgs com id repetido seja processado novamente.
		Map<Integer, String> history = new HashMap<>();
		Dispatcher dispatcher = new Dispatcher();
		
		
		while (true) {
			
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			
			System.out.println("Esperando requisição");

			request = new DatagramPacket(buffer, bufferSize);

			clientSocket.receive(request);
			System.out.println("Requisição : " + (new String(request.getData())));
			
			Message requestMessage = unpackMessage(request.getData());
	
			if(history.containsKey(requestMessage.getRequestId())) {
				System.out.println("Mensagem duplicada!");
			}
			else {
				// Adiciona a msg no historico
				history.put(requestMessage.getRequestId(), requestMessage.getMethod());
				
				Message responseMessage = dispatcher.invoke(requestMessage);

				sendReply(responseMessage, clientSocket, request);
			}
		
		}
	}
	
	private static void sendReply(Message responseMessage, DatagramSocket clientSocket, DatagramPacket request) {
		
		byte[] packedMessage = packMessage(responseMessage);

		DatagramPacket sendPacket = new DatagramPacket(packedMessage, packedMessage.length, request.getAddress(),
				request.getPort());

		try {
			System.out.println("enviando pacote para o cliente");
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static byte[] packMessage(Message message) {
		return message.toJson().getBytes();
	}

	private static Message unpackMessage(byte[] args) {
		String jsonMessage = new String(args);

		Message response = null;
		try {
			response = Message.buildFromJson(jsonMessage);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	
}
