package fr.pineapplemc.launcher.ui;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import fr.flowarg.flowcompat.Platform;
import fr.pineapplemc.launcher.PineappleLauncher;
import fr.pineapplemc.launcher.ui.panel.IPanel;
import fr.pineapplemc.launcher.ui.panels.partials.TopBar;
import fr.pineapplemc.launcher.utils.Utils;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.Logger;

public class PanelManager {

    private final PineappleLauncher instance;
    private final Stage mainStage;

    protected final Logger logger = PineappleLauncher.getInstance().getLogger();

    private GridPane layout;

    private final TopBar topBar = new TopBar();
    private final GridPane contentPane = new GridPane();

    public PanelManager(PineappleLauncher instance, Stage mainStage) {
        this.instance = instance;
        this.mainStage = mainStage;
    }

    public void init() {
        this.mainStage.setTitle(Utils.Constants.LAUNCHER_NAME);
        this.mainStage.setMinWidth(854);
        this.mainStage.setMinHeight(480);
        this.mainStage.setWidth(1280);
        this.mainStage.setHeight(720);
        this.mainStage.initStyle(StageStyle.UNDECORATED);
        this.mainStage.centerOnScreen();
        this.mainStage.getIcons().add(new Image("images/icon.png"));

        this.layout = new GridPane();

        if(Platform.isOnLinux()) {
            Scene scene = new Scene(this.layout);
            this.mainStage.setScene(scene);
        }else {
            BorderlessScene scene = new BorderlessScene(this.mainStage, StageStyle.UNDECORATED, this.layout);
            scene.setResizable(true);
            scene.setMoveControl(this.topBar.getLayout());
            scene.removeDefaultCSS();

            this.mainStage.setScene(scene);

            RowConstraints topPanelConstraints = new RowConstraints();
            topPanelConstraints.setValignment(VPos.TOP);
            topPanelConstraints.setMinHeight(30);
            topPanelConstraints.setMaxHeight(30);
            this.layout.getRowConstraints().addAll(topPanelConstraints, new RowConstraints());
            this.layout.add(this.topBar.getLayout(), 0, 0);
            this.topBar.init(this);
        }
        this.layout.add(this.contentPane, 0, 1);
        GridPane.setVgrow(this.contentPane, Priority.ALWAYS);
        GridPane.setHgrow(this.contentPane, Priority.ALWAYS);

        this.mainStage.show();
        logger.info("Window initialized");
    }

    public void showPanel(IPanel panel) {
        this.contentPane.getChildren().clear();
        this.contentPane.getChildren().add(panel.getLayout());
        if(panel.getStylesheetPath() != null) {
            this.mainStage.getScene().getStylesheets().clear();
            this.mainStage.getScene().getStylesheets().add(panel.getStylesheetPath());
        }
        panel.init(this);
        panel.onShow();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public PineappleLauncher getLauncher() {
        return instance;
    }
}
