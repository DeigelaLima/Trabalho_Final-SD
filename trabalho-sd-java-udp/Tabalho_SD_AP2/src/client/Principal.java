package client;

import java.io.IOException;
import java.util.Scanner;

import client.model.Author;
import client.model.Book;
import client.model.Usuario;
import client.proxy.ProxyAuthor;
import client.proxy.ProxyBook;
import client.proxy.ProxyUsuario;
import server.skeleton.AuthorSkeleton;


public class Principal {

	public static void main(String[] args) throws  IOException {
		
		UDPClient udpClient = new UDPClient("localhost", 6799);
		Scanner scanner = new Scanner(System.in);
		ProxyBook bookProxy = new ProxyBook(udpClient);
		ProxyAuthor authorProxy = new ProxyAuthor(udpClient);
		ProxyUsuario usuarioProxy = new ProxyUsuario(udpClient);
		//AuthorSkeleton autskeleton = new AuthorSkeleton();
		while(true) {
			showInittialMenu();
			String option = scanner.nextLine(); 

			switch (option) {
				case "1":
					System.out.println("Digite o codigo do livro");
					int code = Integer.parseInt(scanner.nextLine());
					
					System.out.println("Digite o título do livro");
					String titulo = scanner.nextLine()
;
					System.out.println("Digite o genero do livro");
					String genre = scanner.nextLine();
					
					System.out.println("Digite o autor do livro");
					String author = scanner.nextLine();
					
					System.out.println("Digite o numero de copias do livro");
					int num_copies = Integer.parseInt(scanner.nextLine());
					
					Book book = new Book(code, titulo, genre, author, num_copies);
					String response = bookProxy.registerBook(book);

					System.out.println("Resposta do servidor: " + response);
					break;
					
				case "2":
					System.out.println("Digite o codigo do author");
					int codeAuthor = Integer.parseInt(scanner.nextLine());
					
					System.out.println("Digite o nome do author");
					String nomeAuthor = scanner.nextLine();
					
					System.out.println("Digite o e-mail do autor");
					String emailauthor = scanner.nextLine();
					
					Author authorr = new Author(codeAuthor, nomeAuthor, emailauthor);
					String responseAuthor = authorProxy.registerAuthor(authorr);
					
					System.out.println("Resposta do servidor: " + responseAuthor);
					break;
				case "3":
					System.out.println("Digite o codigo do usuario");
					int codeUser = Integer.parseInt(scanner.nextLine());
					
					System.out.println("Digite o cpf do usuario");
					String cpf = scanner.nextLine();
					System.out.println("Digite o nome do usuario");
					String nomeUser = scanner.nextLine();
					
					System.out.println("Digite o email do usuario");
					String emailUser = scanner.nextLine();
					
					System.out.println("Digite o telefone do usuario");
					String telefoneUser = scanner.nextLine();
					
					Usuario usuario = new Usuario(codeUser, cpf, nomeUser, emailUser, telefoneUser);
					String responseUser = usuarioProxy.registerUsuario(usuario);

					System.out.println("Resposta do servidor: " + responseUser);
					break;
				case "4":
					String responseListBook = bookProxy.listBooks();
					System.out.println("Resposta do servidor: " + responseListBook);
					break;
					
				case "5":
					String responseList = authorProxy.listAuthors();
					System.out.println("Resposta do servidor: " + responseList);
					break;
				case "6":
					String responseListUsuario = usuarioProxy.listUsuarios();
					System.out.println("Resposta do servidor: " + responseListUsuario);
					break;
				case "7":
					System.out.println("Digite o codigo do usuario que deseja deletar");
					int codeUserDelete = Integer.parseInt(scanner.nextLine());
					
					Usuario usuarioDelete = new Usuario(codeUserDelete);
					String responseDeleteUsuario = usuarioProxy.deleteUsuario(usuarioDelete);
					System.out.println("Resposta do servidor: " + responseDeleteUsuario);
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
		System.out.println("[1] - Cadastrar Livro");
		System.out.println("[2] - Cadastrar Autor");
		System.out.println("[3] - Cadastrar Usuário");
		System.out.println("[4] - Listar Livros");
		System.out.println("[5] - Listar Autores");
		System.out.println("[6] - Listar Usuários");
		System.out.println("[7] - Deletar um Usuário");
		
		System.out.println("[0] - Fechar");
	}
}

