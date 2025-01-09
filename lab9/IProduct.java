package lab9;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public interface IProduct extends Remote {
public boolean checkUserName (String userName) throws RemoteException ;
public Product MA (int  MaSP) throws RemoteException ;
public List<Product> TEN (String TenSP) throws RemoteException ;
public int getLoggin (String userName , String pass) throws RemoteException;
public String getBanner () throws RemoteException;
public String MUA (int idSP1 , int idSP2) throws RemoteException;
}
