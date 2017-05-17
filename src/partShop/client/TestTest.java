package partShop.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by Александр on 15.05.2017.
 */
public class TestTest extends SingleForm {
    public TestTest(PartShop partShop) {
        super(partShop);
    }

    @Override
    protected Widget setupWidgets() {
        return new Label("TEST");
    }
}
