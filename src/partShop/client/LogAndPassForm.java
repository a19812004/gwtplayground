package partShop.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;



/**
 * Created by Александр on 03.05.2017.
 */
public class LogAndPassForm extends SingleForm {

    // компоненты авторизации
    Label infoLabel;
    TextBox loginTextBox;
    PasswordTextBox passTextBox;

    public LogAndPassForm(PartShop partShop) {
        super(partShop);
    }

    @Override
    protected Widget setupWidgets() {
        //компоненты для авторизации
        VerticalPanel logAndPassPanel=new VerticalPanel();
        infoLabel=new Label("Введите логин и пароль");
        loginTextBox=new TextBox();
        passTextBox=new PasswordTextBox();
        Button logAndPassSubmitButton=new Button("Ввод");

        Button testPanelButton=new Button("Test Panel");
        testPanelButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                partShop.switchForm(new WidgetExampleForm(partShop));
            }
        });

        Button splitPanelButton=new Button("splitPanelButtonName");
        splitPanelButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doSwitch();
            }
        });


        //заглушка для экономии времени при вводе пароля
        loginTextBox.setText("Alex");
        passTextBox.setText("123");

        logAndPassPanel.add(infoLabel);
        logAndPassPanel.add(loginTextBox);
        logAndPassPanel.add(passTextBox);
        logAndPassPanel.add(logAndPassSubmitButton);
        logAndPassPanel.add(testPanelButton);
        logAndPassPanel.add(splitPanelButton);
        logAndPassSubmitButton.addClickHandler(new LogAndPassCheckHandler());
 //       logAndPassPanel.add(createSplitOutPanel());
        return logAndPassPanel;
    }

    void doSwitch(){
        SplitPanelForm form = new SplitPanelForm(LogAndPassForm.this.partShop);
       // TestTest test = new TestTest(partShop);
        LogAndPassForm.this.partShop.switchForm(form );
    }

    class LogAndPassCheckHandler implements ClickHandler {
        // слушатель проверки логина и пароля

        @Override
        public void onClick(ClickEvent event) {
            String s="";
            partShop.getLogAndPassService().logAndPassServer(loginTextBox.getText(),passTextBox.getText(),
                    new AsyncCallback<String>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            infoLabel.setText("Ошибка: "+caught.getMessage());
                        }

                        @Override
                        public void onSuccess(String result) {
                            infoLabel.setText(result);
                            partShop.setLogin(loginTextBox.getText());
                            // создаем класс-форму заказа
                            partShop.switchForm( new AnOrderForm(partShop));
                        }
                    });
        }}
}
