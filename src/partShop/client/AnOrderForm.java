package partShop.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import partShop.shared.Entities.Part;

public class AnOrderForm extends SingleForm {
	
	private final AnOrderFormServiceAsync anOrderFormServiceServiceAsync = GWT.create(AnOrderFormService.class);
	
	//таймер обновления данных
	private  Timer refreshTimer;
	
	//интервал обновления данных по складу
	private static final int REFRESH_INTERVAL=5000;
	
	//коллекция товаров в списке
	ArrayList<String> productInList=new ArrayList<String>();
	
    //коллекция доступных товаров
	ArrayList<Part> productList=new ArrayList<>();
	
	 //компоненты формы заказа формы
	 private VerticalPanel mainPanel;
	 private Label infoLabel;
	 private FlexTable flexTable;
	 private HorizontalPanel addPanel;
	 private MultiWordSuggestOracle oracle;
	 private SuggestBox symbolTextBox;
	 private Button addButton;
	 private Label lastUpdateLabel;
	 private Button logOutButton;
	 
	 int res;//переменная для получения данных о складских запасах
	
	
	public AnOrderForm(PartShop partShop) {

		super(partShop);

		queryData();//запрашиваем доступный список продуктов и список значений для suggestBox
		refreshTimer = new Timer (){
			public void run() {
				refreshWidgets();
		}
		};
		refreshTimer.scheduleRepeating(REFRESH_INTERVAL);

	}
	
	final DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.HOUR24_MINUTE_SECOND);
	private void refreshWidgets(){
		
		
		for (int i=1;i<flexTable.getRowCount();i++){
			String s=flexTable.getText(i, 1);
			stockResQuery(i,s);
		}	
		
		lastUpdateLabel.setText("Последнее обновление: "
		+format.format(new Date()));
	}

	//метод-обработчик текста их TextBox
	private void addProduct (){

		final String symbol=symbolTextBox.getText();
		final int row=flexTable.getRowCount();

		ArrayList <String> productListString=new ArrayList<>();
		for(Part part:productList){
			productListString.add(part.getPartName12345());
			}

		
		//проверка наличия товара в списке
		if (!productListString.contains(symbol)){
			infoLabel.setText("Нет такого товара");
			symbolTextBox.setText("");
			return;}
		if(productInList.contains(symbol)){
			infoLabel.setText("Товар уже добавлен");
			symbolTextBox.setText("");
			return;
			}
		else{
			productInList.add(symbol);}
		
		flexTable.setText(row, 0, symbol);
		flexTable.setText(row, 1, String.valueOf(1));
		Button increaseButton = new Button("+");
		Button reduceButton = new Button("-");
		Button removeButton=new Button("x");


		removeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

					final int removedIndex=productInList
							  .indexOf(symbol);
					productInList.remove(removedIndex);
						flexTable.removeRow(removedIndex+1);
			}
		});
		
		increaseButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int i=Integer.valueOf(flexTable.getText(row,1));
				flexTable.setText(row, 1, String.valueOf(i+1));
			}
		});
		
		reduceButton.addClickHandler(new ClickHandler () {
			
			@Override
			public void onClick(ClickEvent event) {
				int i=Integer.valueOf(flexTable.getText(row,1));
				if(i>1){
					flexTable.setText(row, 1, String.valueOf(i-1));
				}
			}
		});
		
		flexTable.setWidget(row, 3, increaseButton);
		flexTable.setWidget(row, 4, reduceButton);
		flexTable.setWidget(row, 5, removeButton);
		//запрашиваем данные о количестве
		stockResQuery(row,symbol);
		flexTable.setText(row, 2, "???");
		
        symbolTextBox.setFocus(true);
        symbolTextBox.setText("");
        infoLabel.setText("Товар "+symbol+" добавлен.");
	}
	
	
	
	private void stockResQuery(final int pos,final String symbol) {
		
		anOrderFormServiceServiceAsync.stockReserveQuery(symbol,
				new AsyncCallback<Integer>() {
			@Override
			public void onFailure(Throwable caught) {
				infoLabel.setText("Нет данных по запасам на складе - "+lastUpdateLabel.getText());
				res=0;
			}

			@Override
			public void onSuccess(Integer result) {
				flexTable.setText(pos, 2, result.toString());
				//res=result;
			}
		});
		
		//return res ;
	}

	@Override
	protected Widget setupWidgets() {

		mainPanel=new VerticalPanel();
		infoLabel = new Label("Добавьте товар");
		flexTable=new FlexTable();
		addPanel=new HorizontalPanel();
		oracle = new MultiWordSuggestOracle();
		symbolTextBox=new SuggestBox(oracle);
		addButton=new Button("Добавить");
		lastUpdateLabel = new Label("---------");
		logOutButton = new Button("Выход");

		symbolTextBox.addKeyUpHandler(new productTextBoxKeyHandler());//добавили слушатель клавиши Enter к suggestBox

		Label autorizedUser=new Label("Авторизован ползователь: "+ partShop.getLogin());
		mainPanel.add(autorizedUser);

		//настройка заголовка таблицы из формы заказа
		flexTable.setText(0,0, "Товар");
		flexTable.setText(0, 1, "Количество");
		flexTable.setText(0, 2, "На складе");
		flexTable.setText(0, 3, "Добавить");
		flexTable.setText(0, 4, "Убавить");
		flexTable.setText(0, 5, "Удалить");

		//добавляем компоненты формы заказа
		//панель добавления нового товара
		addPanel.add(symbolTextBox);
		addPanel.add(addButton);
		//добавление элементов на главную панель
		mainPanel.add(infoLabel);
		mainPanel.add(flexTable);
		mainPanel.add(addPanel);
		mainPanel.add(addButton);
		mainPanel.add(lastUpdateLabel);
		mainPanel.add(logOutButton);
		//добавляем главную панель на HTML страницу
		//RootPanel.get("logAndPassPanel").add(mainPanel);
		//переводим фокус на текстовое поле
		symbolTextBox.setFocus(true);

		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				addProduct ();
			}
		});

		logOutButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				partShop.switchForm(new LogAndPassForm(partShop));

			}
		});

	/**/
		return mainPanel;
	}

	@Override
	protected void queryData() {

		//super.queryData();
		anOrderFormServiceServiceAsync.aviableProductQuery(
				new AsyncCallback<ArrayList<Part>>() {

					@Override
					public void onSuccess(ArrayList<Part> result) {
						oracle.clear();
						for(Part part:result){
							oracle.add(part.getPartName12345());
							productList.add(part);
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}
				});
	}

	class productTextBoxKeyHandler implements KeyUpHandler{
		@Override
		public void onKeyUp(KeyUpEvent event) {
			if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER){addProduct();}
		}
	}

}
