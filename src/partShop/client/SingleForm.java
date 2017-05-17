package partShop.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by Александр on 03.05.2017.
 */
public abstract class SingleForm {
    // цель данного класса - добавить
    // стандертные методы для управления виджетами
    // классам-наследникам

    protected final PartShop partShop;
    protected RootPanel panel;

    protected final Widget formRoot;


    public SingleForm(PartShop partShop){
        this.partShop = partShop;
        formRoot=setupWidgets();
//        queryData();
    }

    protected abstract Widget setupWidgets();
    protected void queryData(){}

    public void register(RootPanel panel){
        if(this.panel!=null){
            unregister();
        }
        this.panel=panel;
        panel.add(formRoot);
        queryData();
    }

    public void unregister(){
        if(panel!=null) {
            panel.remove(formRoot);
        }
        panel =null;
    }
}
