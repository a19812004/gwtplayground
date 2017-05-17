package partShop.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("partShop/logAndPass")
public interface LogAndPassService extends RemoteService {
	String logAndPassServer(String login, String password) throws IllegalArgumentException;
	void logOut() throws  IllegalArgumentException;

}
