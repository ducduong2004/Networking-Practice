package lab6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ImportData {
	
	public List<Account> loadData(String fileTaiKhoan, String fileNhatKy) throws IOException {
		List<Account> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileTaiKhoan),"UTF-16BE"));
		String line;
		br.read();
		while((line = br.readLine()) != null ) {
			StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");
			int stk = Integer.parseInt(stringTokenizer.nextToken());
			String name = stringTokenizer.nextToken();
			String pass = stringTokenizer.nextToken();
			
			list.add(new Account(stk, name, pass));
			
		}
		
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileNhatKy), "UTF-16BE"));
		br.read();
		
		while((line = br.readLine()) != null) {
			StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");
			int stk = Integer.parseInt(stringTokenizer.nextToken());
			String loaiGiaoDich = stringTokenizer.nextToken();
			String date = stringTokenizer.nextToken();
			Double value = Double.parseDouble(stringTokenizer.nextToken());
			
			for(Account a : list) {
				if(a.getStk() == stk) {
					if(loaiGiaoDich.equals("GUI")) {
						a.setBal(a.getBal() + value);
					} else if(loaiGiaoDich.equals("RUT")) {
						a.setBal(a.getBal() - value);
					}
					break;
				}
			}

		}
		
		br.close();
		
		
		return list;
		
	}
	
	
}
