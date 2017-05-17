package partShop.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import java.util.Date;

/**
 * Created by Александр on 07.05.2017.
 */
public class WidgetExampleForm extends SingleForm {

    //компоненты для Date Picker
    public static class MyDateValueChangeHandler implements ValueChangeHandler<Date> {
        private final Label text;

        public MyDateValueChangeHandler(Label text) {
            this.text = text;
        }

        public void onValueChange(ValueChangeEvent<Date> event) {
            Date date = event.getValue();
            String dateString = DateTimeFormat.getMediumDateFormat().format(date);
            text.setText(dateString);
        }
    }

    public WidgetExampleForm(PartShop partShop) {
        super(partShop);
    }

    @Override
    protected Widget setupWidgets() {
        VerticalPanel verticalPanel = new VerticalPanel();
        Label label = new Label("Панель для изучения виджетов");
        Button backStepButton = new Button("<-Назад");
        backStepButton.addClickHandler(new ClickHandler() {
            //возврат к начальной форме
            @Override
            public void onClick(ClickEvent event) {
                partShop.switchForm(new LogAndPassForm(partShop));
            }
        });
        verticalPanel.add(label);
        verticalPanel.add(backStepButton);
        //CheckBox
        verticalPanel.add(new HTML("<h4>CheckBox Example</h4>"));
        HorizontalPanel horisontalPanelForCheckBox = new HorizontalPanel();
        horisontalPanelForCheckBox.setSpacing(10);
        CheckBox checkBox = new CheckBox("CheckBox");
        Label labelForCheckBox = new Label("  Не выбрано");
        checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                if (checkBox.getValue() == true) {
                    labelForCheckBox.setText("Выбран");
                } else {
                    labelForCheckBox.setText("Не выбран");
                }
            }
        });
        horisontalPanelForCheckBox.add(checkBox);
        horisontalPanelForCheckBox.add(labelForCheckBox);
        verticalPanel.add(horisontalPanelForCheckBox);
        verticalPanel.add(new HTML("<HR>"));

        //RadioButton
        verticalPanel.add(new HTML("<h4>RadioButton Example</h4>"));
        HorizontalPanel horizontalPanelForRadioButton = new HorizontalPanel();
        horizontalPanelForRadioButton.setSpacing(10);
        Label labelForRadioButtons = new Label("");
        for (int i = 1; i < 4; i++) {
            RadioButton radioButton = new RadioButton("numbers", String.valueOf(i));
            horizontalPanelForRadioButton.add(radioButton);
            if (i == 1) {
                radioButton.setValue(true);
            }
            radioButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (radioButton.getValue() == true) {
                        labelForRadioButtons.setText(radioButton.getText());
                    }
                }
            });
        }
        horizontalPanelForRadioButton.add(labelForRadioButtons);
        verticalPanel.add(horizontalPanelForRadioButton);
        verticalPanel.add(new HTML("<HR>"));

        //Custom Button
        verticalPanel.add(new HTML("<h4>Custom Button Example</h4>"));
        HorizontalPanel pushPanel = new HorizontalPanel();
        pushPanel.setSpacing(10);
        // Add a normal PushButton
        PushButton normalPushButton = new PushButton(
                new Image("greenRect.jpg"));
        pushPanel.add(normalPushButton);


        verticalPanel.add(pushPanel);
        verticalPanel.add(new HTML("<HR>"));

        //File Upload
        verticalPanel.add(new HTML("<h4>File Upload Example</h4>"));
        HorizontalPanel fileUploadHorisontalPanel = new HorizontalPanel();
        fileUploadHorisontalPanel.setSpacing(10);
        final FileUpload fileUpload = new FileUpload();

        // Add a button to upload the file
        Button uploadButton = new Button("Загрузить");
        uploadButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                String filename = fileUpload.getFilename();
                if (filename.length() == 0) {
                    Window.alert("Файл не выбран.");
                } else {
                    Window.alert("Загрузка файла " + filename);
                }
            }
        });

        fileUploadHorisontalPanel.add(fileUpload);
        fileUploadHorisontalPanel.add(uploadButton);

        verticalPanel.add(fileUploadHorisontalPanel);
        verticalPanel.add(new HTML("<HR>"));

        //Date Picker
        verticalPanel.add(new HTML("<h4>Date Picker Example</h4>"));
        // create a date picker where years and months are selectable with drop down lists and where we
        // can navigate trough the years
        DatePicker advancedDatePicker = new DatePicker();
        advancedDatePicker.setYearArrowsVisible(true);
        advancedDatePicker.setYearAndMonthDropdownVisible(true);
        // show 100 years in the years dropdown. The range of years is centered on the selected date
        advancedDatePicker.setVisibleYearCount(100);
        final Label text2 = new Label();
        // Set the value in the text box when the user selects a date
        advancedDatePicker.addValueChangeHandler(new MyDateValueChangeHandler(text2));
        // Set the default value
        advancedDatePicker.setValue(new Date(), true);

        DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
        DateBox dateBox = new DateBox();
        dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
        dateBox.getDatePicker().setYearArrowsVisible(true);

        HorizontalPanel datePickerHorizontalPanel = new HorizontalPanel();
        datePickerHorizontalPanel.setSpacing(20);

        datePickerHorizontalPanel.add(advancedDatePicker);
        datePickerHorizontalPanel.add(dateBox);

        verticalPanel.add(text2);
        verticalPanel.add(datePickerHorizontalPanel);
        verticalPanel.add(new HTML("<HR>"));

        //Hyperlink
        verticalPanel.add(new HTML("<h4>Hyperlink Example</h4>"));
        verticalPanel.add(new HTML("<br>Не разобрался<br>"));
        verticalPanel.add(new HTML("<HR>"));

        //List Box
        verticalPanel.add(new HTML("<h4>List Box Example</h4>"));

        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(20);

        // Add a drop box with the list types
        final ListBox dropBox = new ListBox(false);
        String[] listTypes = {"Звери", "Рыбы", "Птицы"};
        for (int i = 0; i < listTypes.length; i++) {
            dropBox.addItem(listTypes[i]);
        }

        VerticalPanel dropBoxPanel = new VerticalPanel();
        dropBoxPanel.setSpacing(4);
        dropBoxPanel.add(dropBox);
        hPanel.add(dropBoxPanel);

        // Add a list box with multiple selection enabled
        final ListBox multiBox = new ListBox(true);

        multiBox.setWidth("11em");
      //  multiBox.setVisibleItemCount(4);
        VerticalPanel multiBoxPanel = new VerticalPanel();
        multiBoxPanel.setSpacing(4);
        multiBoxPanel.add(multiBox);
        hPanel.add(multiBoxPanel);

        // Add a handler to handle drop box events
        dropBox.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                showCategory(multiBox, dropBox.getSelectedIndex());
            }
        });
        // Show default category
        showCategory(multiBox, 0);
        verticalPanel.add(hPanel);
        verticalPanel.add(new HTML("<HR>"));

        return verticalPanel;
    }
    private void showCategory(ListBox listBox, int category) {
        // метод для List Box
        listBox.clear();
        String[] listData = null;
        switch (category) {

            case 0:
                listData = new String[]{"Заяц", "Волк", "Медведь"};
                break;
            case 1:
                listData = new String[]{"Карась", "Щука", "Окунь"};
                break;
            case 2:
                listData = new String[]{"Воробей", "Голубь", "Ворона"};
                break;
        }
        for (int i = 0; i < listData.length; i++) {
            listBox.addItem(listData[i]);
        }
    }
}



