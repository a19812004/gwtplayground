package partShop.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import partShop.shared.Entities.Part;

@RemoteServiceRelativePath("partShop/AnOrderForm")
public interface AnOrderFormService extends RemoteService {
	//AnOrderForm
	ArrayList<Part> aviableProductQuery  () throws IllegalArgumentException;
	Integer stockReserveQuery (String symbol) throws IllegalArgumentException;

}
