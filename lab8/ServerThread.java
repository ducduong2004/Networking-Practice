package lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class ServerThread extends Thread {
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	private IUserDAO userDAO = new UserDAO();
	private IProductDAO productDAO = new ProductDAO();

	public ServerThread(Socket socket) {
		this.socket = socket;
		try {
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.pw = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		pw.println("WELCOME TO MANAGE PRODUCT SYSTEM");
		String input = "";
		String username = "";
		String password = "";
		String status = "";
		boolean isLogin = false;
		while (!isLogin) {
			try {
				input = br.readLine();
				if (input.equals("EXIT"))
					break;
				try {
					StringTokenizer stringTokenizer = new StringTokenizer(input, "\t");
					String command = stringTokenizer.nextToken();
					String parameter = stringTokenizer.nextToken();
					switch (command) {
					case "USER":
						username = parameter;
						status = userDAO.checkUser(username) ? "OK" : "FALSE";
						pw.println(status);
						break;
					case "PASS":
						password = parameter;
						status = userDAO.login(username, password) ? "OK" : "FALSE";
						if (status.equals("OK"))
							isLogin = true;
						pw.println(status);
						break;
					default:
						pw.println("Sai cau lenh");
					}
				} catch (NoSuchElementException e) {
					pw.println("Sai cau lenh");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (isLogin) {
			try {
				input = br.readLine();
				if (input.equals("QUIT"))
					break;
				try {
					StringTokenizer stringTokenizer = new StringTokenizer(input, "\t");
					String command = stringTokenizer.nextToken();
					List<String> parameters = new ArrayList<>();
					while (stringTokenizer.hasMoreTokens()) {
						parameters.add(stringTokenizer.nextToken());
					}
					switch (command) {
					case "ADD":
						boolean isAdded = productDAO.add(new Product(parameters.get(0), parameters.get(1),
								Integer.valueOf(parameters.get(2)), Double.valueOf(parameters.get(3))));
						status = isAdded ? "OK" : "ERROR";
						pw.print(status);
						break;
					case "EDIT":
						boolean isEdited = productDAO.edit(new Product(parameters.get(0), parameters.get(1),
								Integer.valueOf(parameters.get(2)), Double.valueOf(parameters.get(3))));
						status = isEdited ? "OK" : "CAN NOT UPDATE";
						pw.print(status);
						break;
					case "VIEW":
						List<Product> products = productDAO.view(parameters.get(0));
						if (products.size() > 0) {
							for (Product product : products) {
								pw.println(product);
							}
						}
						pw.println("THE END");
						break;
					case "REMOVE":
						int count = 0;
						for (String string : parameters) {
							if (productDAO.remove(string))
								count++;
						}
						pw.println(count + " products deleted");
						break;
					default:
						pw.println("SAI CAU LENH");
					}
				} catch (NoSuchElementException e) {
					pw.println("SAI CAU LENH");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
