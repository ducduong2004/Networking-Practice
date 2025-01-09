package lab9;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRemote {
public static void main(String[] args) throws RemoteException {
	Registry reg = LocateRegistry.createRegistry(5918);
	IProduct ip = new ProductImp();
	reg.rebind("STORE", ip);
	
}
}
