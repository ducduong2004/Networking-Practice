package denamngoai;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ProductImp extends UnicastRemoteObject implements IProduct {
	private Dao dao ;
	public  ProductImp() throws RemoteException {
		dao = new Dao();
		
	}
	@Override
	public boolean checkUserName(String userName) throws RemoteException {
		try {
		return dao.checkUserName(userName);	
		} catch (Exception e) {
		throw new RemoteException(e.getMessage());
		}
	}
	@Override
	public int getLoggin(String userName, String pass) throws RemoteException {
		try {
			return dao.getLoggin(userName,pass);	
			} catch (Exception e) {
			throw new RemoteException(e.getMessage());
			}
	}
	@Override
	public String getBanner() throws RemoteException {
		// TODO Auto-generated method stub
		return "xin chao mung .. ";
	}
	@Override
	public Product MA(int MaSP) throws RemoteException {
		try {
			return dao.getProdudctByID(MaSP);	
			} catch (Exception e) {
			throw new RemoteException(e.getMessage());
			}
	}
	@Override
	public List<Product> TEN(String TenSP) throws RemoteException {
		try {
			return dao.getProdudctByName(TenSP);	
			} catch (Exception e) {
			throw new RemoteException(e.getMessage());
			}
	}
	@Override
	public String MUA(int idSP1, int idSP2) throws RemoteException {
		try {
			return dao.Mua(idSP1 , idSP2);	
			} catch (Exception e) {
			throw new RemoteException(e.getMessage());
			}
	}

}
