package partShop.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import partShop.shared.Entities.Part;

@RemoteServiceRelativePath("partShop/AnOrderForm")
public interface AnOrderFormServiceAsync {

	void aviableProductQuery(AsyncCallback<ArrayList<Part>> callback);
	void stockReserveQuery(String symbol, AsyncCallback<Integer> callback);

	//AnOrderForm
	//void aviableProductQuery(AsyncCallback<ArrayList<Part>> async);
}
