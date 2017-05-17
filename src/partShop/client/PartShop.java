package partShop.client;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.*;
import partShop.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PartShop implements EntryPoint {

/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final LogAndPassServiceAsync logAndPassService = GWT.create(LogAndPassService.class);

	public LogAndPassServiceAsync getLogAndPassService() {
		return logAndPassService;
	}

	/**
	 * This is the entry point method.
	 */

	SingleForm currentForm=null;
	RootPanel panelForForms;
	String login;

	//public PartShop (){}

	public void setLogin(String login) {
		this.login = login;
	}

	public void switchForm(SingleForm newForm){
		if(currentForm!=null){
			currentForm.unregister();
		}
		newForm.register(panelForForms);
		currentForm=newForm;
	}

	public void onModuleLoad() {

		panelForForms=RootPanel.get("logAndPassPanel");
		switchForm(new LogAndPassForm(this));
	}



	public String getLogin() {
		return login;
	}
	}


