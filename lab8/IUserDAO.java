package lab8;

public interface IUserDAO {
	boolean checkUser(String username);

	boolean login(String username, String password);
}
