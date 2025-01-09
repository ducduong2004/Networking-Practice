package lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.StringTokenizer;


public class ClientRemote {
	public static void main(String[] args) throws NotBoundException, IOException {
		Registry client = LocateRegistry.getRegistry(5918);
		Remote remote = client.lookup("STORE");
		IProduct products = (IProduct) remote;
		System.out.println(products.getBanner());
		String line;
		String response = null;
		String userName = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean isLoggin = false;
		while (!isLoggin) {
			line = reader.readLine();
			if (line.equalsIgnoreCase("EXIT")) {
				System.out.println("Ket thuc chuong trinh !");
				break;
			}
			StringTokenizer token = new StringTokenizer(line);
			String command = token.nextToken().toUpperCase();
			switch (command) {
			case "TEN" -> {
				try {
					String param = line.substring(command.length() + 1);
					if (products.checkUserName(param)) {
						userName = param;
						response = "OK";
					} else
						response = "INCORRECT USER NANME";
				} catch (Exception e) {
				response = "Nhập vào tên ";
				}
			}
			case "MATKHAU" -> {
				if (userName == null) {
					response = "USER NAME IS NOT EXIST !";
				} else {
					try {
						String param = line.substring(command.length() + 1);
						int session = products.getLoggin(userName, param);
						if (session != -1) {
							isLoggin = true;
							response = "LOGGIN SUCCESS";
						} else
							response = "INCORRECT USER NANME OR PASSWORD";
					} catch (Exception e) {
						response ="MẬT KHẨU RỖNG";
					}
					
					
				}

			}

			default -> response = "INVALID COMMAND";
			}
			System.out.println(response);
		}
		while (isLoggin) {
			line = reader.readLine();
			if (line.equalsIgnoreCase("EXIT")) {
				System.out.println("Ket thuc chuong trinh");
				break;
			}
			StringTokenizer token = new StringTokenizer(line, "\t");
			String command = token.nextToken().toUpperCase();
			switch (command) {
			case "MA" -> {
				
				try {
					if (!token.hasMoreTokens()) {
						System.out.println("Vui lòng nhập mã sản phẩm  !");
						break;
					}
					int id = Integer.parseInt(token.nextToken());
					Product p = products.MA(id);
					System.out.println(p);
					
				} catch (Exception e) {
					System.out.println("Vui lòng nhập tên sản phẩm ");
				}
				
			}
			case "TEN" -> {
				
				try {
					String ten = token.nextToken();
					List<Product> list = products.TEN(ten);
					if (list.isEmpty()) {
						System.out.println("Khong tim thay san pham !");
					}
					else {
						list.forEach(System.out::println);
					}
				} catch (Exception e) {
					System.out.println("Vui lòng nhập tên sản phẩm ");
				}
				
			}
			case "MUA" -> {
				try {
					int id1 = Integer.parseInt(token.nextToken());
					int id2 = Integer.parseInt(token.nextToken());
					System.out.println(products.MUA(id1, id2));
				} catch (Exception e) {
				System.out.println("Vui lòng nhập id cả 2 sản phẩm");
				}
			}
			default -> System.out.println("INVALID COMMAND !");
			}

		}
	}
}
