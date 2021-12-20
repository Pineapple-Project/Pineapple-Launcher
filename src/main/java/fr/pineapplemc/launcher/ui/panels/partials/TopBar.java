package fr.pineapplemc.launcher.ui.panels.partials;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.ui.panel.Panel;
import fr.pineapplemc.launcher.utils.Utils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TopBar extends Panel {

    private GridPane topBar;

    @Override
    public String getName() {
        return "TopBar";
    }

    @Override
    public String getStylesheetPath() {
        return null;
    }

    @Override
    public void init(PanelManager manager) {
        super.init(manager);
        this.topBar = this.layout;
        this.layout.setStyle("-fx-background-color: rgb(35, 40, 40);");

        // Topbar left side
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("images/icon.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(30);
        setLeft(imageView);
        this.layout.getChildren().add(imageView);

        // Topbar center side
        Label title = new Label(Utils.Constants.LAUNCHER_TITLE);
        title.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 18f));
        title.setStyle("-fx-text-fill: white;");
        setCenterH(title);
        this.layout.getChildren().add(title);

        // Topbar right side
        GridPane topbarButtons = new GridPane();
        topbarButtons.setMinWidth(100d);
        topbarButtons.setMaxWidth(100d);
        setCanTakeAllSize(topbarButtons);
        setRight(topbarButtons);
        this.layout.getChildren().add(topbarButtons);

        // Topbar buttons config
        FontAwesomeIconView closeIcon = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_CLOSE);
        FontAwesomeIconView fullscreenIcon = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_MAXIMIZE);
        FontAwesomeIconView minimizeIcon = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_MINIMIZE);
        setCanTakeAllWidth(closeIcon, fullscreenIcon, minimizeIcon);

        closeIcon.setFill(Color.WHITE);
        closeIcon.setOpacity(.7f);
        closeIcon.setSize("18px");
        closeIcon.setOnMouseEntered(e -> closeIcon.setOpacity(1.f));
        closeIcon.setOnMouseExited(e -> closeIcon.setOpacity(.7f));
        closeIcon.setOnMouseClicked(e -> Runtime.getRuntime().exit(0));
        closeIcon.setTranslateX(70f);

        fullscreenIcon.setFill(Color.WHITE);
        fullscreenIcon.setOpacity(0.70f);
        fullscreenIcon.setSize("14px");
        fullscreenIcon.setOnMouseEntered(e -> fullscreenIcon.setOpacity(1.f));
        fullscreenIcon.setOnMouseExited(e -> fullscreenIcon.setOpacity(0.7f));
        fullscreenIcon.setOnMouseClicked(e -> {
            this.manager.getMainStage().setMaximized(!this.manager.getMainStage().isMaximized());
        });
        fullscreenIcon.setTranslateX(50.0d);

        minimizeIcon.setFill(Color.WHITE);
        minimizeIcon.setOpacity(0.70f);
        minimizeIcon.setSize("18px");
        minimizeIcon.setOnMouseEntered(e -> minimizeIcon.setOpacity(1.f));
        minimizeIcon.setOnMouseExited(e -> minimizeIcon.setOpacity(0.7f));
        minimizeIcon.setOnMouseClicked(e -> {
            this.manager.getMainStage().setIconified(true);
        });
        minimizeIcon.setTranslateX(26.0d);

        topbarButtons.getChildren().addAll(closeIcon, fullscreenIcon, minimizeIcon);
    }
}
