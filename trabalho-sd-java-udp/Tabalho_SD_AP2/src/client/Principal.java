package client;


import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import client.model.Book;
import client.proxy.ProxyBook;


public class Principal {

	public static void main(String[] args) throws  IOException {
		
		UDPClient updClient = new UDPClient("localhost", 6799);
		Scanner scanner = new Scanner(System.in);
		ProxyBook bookProxy = new ProxyBook(updClient);
		Book book1 = new Book(123, "Comedia", "Giru Biro", 200);
		
		while(true) {
			showInittialMenu();
			String option = scanner.nextLine(); 

			switch (option) {
				case "1":
					System.out.println("Digite o codigo do livro");
					int code = Integer.parseInt(scanner.nextLine());
					
					System.out.println("Digite o genero do livro");
					String genre = scanner.nextLine();
					
					System.out.println("Digite o autor do livro");
					String author = scanner.nextLine();
					
					System.out.println("Digite o numero de copias do livro");
					int num_copies = Integer.parseInt(scanner.nextLine());
					
					Book book = new Book(code, genre, author, num_copies);
					String response = bookProxy.registerBook(book);

					System.out.println("Resposta do servidor: " + response);
					break;
				case "0":
					return;
			default:
					System.out.println("Tente de novo!");
					break;
			}
		}
	}
	
	private static void showInittialMenu(){
		System.out.println("");
		System.out.println("** Livraria Java **");
		System.out.println("Opções: ");
		System.out.println("[1] - Cadastrar livro");
		System.out.println("[0] - Fechar");
	}
}

