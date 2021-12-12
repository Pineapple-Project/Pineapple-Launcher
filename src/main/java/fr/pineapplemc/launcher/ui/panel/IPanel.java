package fr.pineapplemc.launcher.ui.panel;

import fr.pineapplemc.launcher.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {

    void init(PanelManager manager);
    GridPane getLayout();
    void onShow();
    String getName();
    String getStylesheetPath();
}
