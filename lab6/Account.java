package lab6;

public class Account {
	int stk;
	String name;
	String pass;
	double bal;
	
	
	public Account(int stk, String name, String pass) {
		super();
		this.stk = stk;
		this.name = name;
		this.pass = pass;
	}


	public int getStk() {
		return stk;
	}


	public void setStk(int stk) {
		this.stk = stk;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public double getBal() {
		return bal;
	}


	public void setBal(double bal) {
		this.bal = bal;
	}
	
	
	
	
	
}
