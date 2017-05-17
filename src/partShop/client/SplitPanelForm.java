package partShop.client;

import com.google.gwt.user.client.ui.*;

/**
 * Created by Александр on 08.05.2017.
 */
public class SplitPanelForm  extends SingleForm {


    public SplitPanelForm(PartShop partShop) {
        super(partShop);
    }

    @Override
    protected Widget setupWidgets() {
        // Create a Split Panel
     /*   SplitLayoutPanel splitPanel = new SplitLayoutPanel(5);
        splitPanel.ensureDebugId("cwSplitLayoutPanel");
        splitPanel.getElement().getSt                                   yle()
                .setProperty("border", "3px solid #e7e7e7");

        // Add text all around.
        splitPanel.addNorth(new Label("North"), 50);
        splitPanel.addSouth(new Label("South"), 50);
        splitPanel.addEast(new Label("East"), 100);
        splitPanel.addWest(new Label("West"), 100);
        /*splitPanel.addNorth(new Label("North"), 50);
        splitPanel.addSouth(new Label("South"), 50);*/

        // Add scrollable text to the center.
   /*     String centerText = "Center";
        for (int i = 0; i < 3; i++) {
            centerText += " " + centerText;
        }*/
   //     Label centerLabel = new Label(centerText);
     //   ScrollPanel centerScrollable = new ScrollPanel(centerLabel);
     //   splitPanel.add(centerLabel);

        return new Label("North");
    }
}
