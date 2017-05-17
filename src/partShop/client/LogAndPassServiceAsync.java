package partShop.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("partShop/logAndPass")
public interface LogAndPassServiceAsync {

	void logAndPassServer(String login, String password, AsyncCallback<String> callback);

    void logOut(AsyncCallback<Void> async);
}
