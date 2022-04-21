package fr.pineapplemc.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.pineapplemc.launcher.PineappleLauncher;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.ui.panel.Panel;
import fr.pineapplemc.launcher.utils.Utils;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Homepage extends Panel {

    private GridPane gameCardContainer = new GridPane();
    private GridPane gameCard = new GridPane();
    private GridPane topGameCard = new GridPane();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStylesheetPath() {
        return "css/app.css";
    }

    @Override
    public void init(PanelManager manager) {
        super.init(manager);

        // Background
        GridPane background = new GridPane();
        setCanTakeAllSize(background);
        background.getStyleClass().add("background");
        background.setStyle("-fx-background-image: url('"+ Utils.getCurrentDynamicBackground() +"');");
        this.layout.add(background, 0, 0);

        // Game Card Container
        gameCardContainer.setMaxWidth(400);
        gameCardContainer.setMinWidth(400);
        gameCardContainer.setMaxHeight(580);
        gameCardContainer.setMinHeight(580);

        GridPane.setVgrow(gameCardContainer, Priority.ALWAYS);
        GridPane.setHgrow(gameCardContainer, Priority.ALWAYS);
        setCenterV(gameCardContainer);
        setRight(gameCardContainer);

        RowConstraints gameCardConstraints = new RowConstraints();
        gameCardConstraints.setValignment(VPos.TOP);
        gameCardConstraints.setMaxHeight(55);
        gameCardContainer.getRowConstraints().addAll(gameCardConstraints, new RowConstraints());
        gameCardContainer.add(topGameCard, 0, 0);
        gameCardContainer.add(gameCard, 0, 1);

        this.layout.getChildren().add(gameCardContainer);

        // Top Game Card
        GridPane.setVgrow(topGameCard, Priority.ALWAYS);
        GridPane.setHgrow(topGameCard, Priority.ALWAYS);
        topGameCard.setStyle("-fx-background-color: rgb(18, 18, 18); -fx-opacity: 50%;");
        topGameCard.setPadding(new Insets(0, 10, 0, 10));

        // Disconnect Button
        FontAwesomeIconView disconnectButton = new FontAwesomeIconView(FontAwesomeIcon.USER_TIMES);

        GridPane.setVgrow(disconnectButton, Priority.ALWAYS);
        GridPane.setHgrow(disconnectButton, Priority.ALWAYS);
        setCenterV(disconnectButton);
        setLeft(disconnectButton);

        disconnectButton.setFill(Color.WHITE);
        disconnectButton.setSize("38px");
        disconnectButton.setOpacity(.7f);
        disconnectButton.setOnMouseEntered(e -> { disconnectButton.setOpacity(1f); this.layout.setCursor(Cursor.HAND); });
        disconnectButton.setOnMouseExited(e -> { disconnectButton.setOpacity(.7f); this.layout.setCursor(Cursor.DEFAULT); });
        disconnectButton.setOnMouseClicked(e -> PineappleLauncher.getInstance().getAuthManager().disconnect());

        Tooltip disconnectButtonHoverText = new Tooltip("Disconnect");
        disconnectButtonHoverText.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        Tooltip.install(disconnectButton, disconnectButtonHoverText);

        topGameCard.getChildren().add(disconnectButton);

        // Player Head ImageView
        /*StackPane settingsPaneButton = new StackPane();
        ImageView settingsButton = Utils.getPlayerHead(PineappleLauncher.getInstance().getMicrosoftGameProfile().getId());

        GridPane.setVgrow(settingsPaneButton, Priority.ALWAYS);
        GridPane.setHgrow(settingsPaneButton, Priority.ALWAYS);
        setCenterH(settingsPaneButton);
        setRight(settingsPaneButton);

        settingsPaneButton.setMaxWidth(45);
        settingsPaneButton.setMaxHeight(45);
        settingsPaneButton.setOpacity(1f);

        settingsButton.setFitWidth(35);
        settingsButton.setFitHeight(35);

        settingsPaneButton.setOnMouseEntered(e -> { settingsPaneButton.setOpacity(1f); this.layout.setCursor(Cursor.HAND); });
        settingsPaneButton.setOnMouseExited(e -> { settingsPaneButton.setOpacity(.7f); this.layout.setCursor(Cursor.DEFAULT); });

        Tooltip settingsButtonHoverText = new Tooltip("Settings");
        settingsButton.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        Tooltip.install(settingsPaneButton, settingsButtonHoverText);

        settingsPaneButton.getChildren().add(settingsButton);
        topGameCard.getChildren().add(settingsPaneButton);*/

        // Game Settings Label
        Label gameSettingsLabel = new Label(Utils.Constants.HOMEPAGE_GAMESETTINGS_LABEL);

        GridPane.setVgrow(gameSettingsLabel, Priority.ALWAYS);
        GridPane.setHgrow(gameSettingsLabel, Priority.ALWAYS);
        setCenterH(gameSettingsLabel);
        setTop(gameSettingsLabel);

        gameSettingsLabel.setPadding(new Insets(30, 0, 0, 0));
        gameSettingsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");

        gameCard.getChildren().add(gameSettingsLabel);

        // Version ComboBox
        Label gameVersionLabel = new Label(Utils.Constants.HOMEPAGE_GAMEVERSION_LABEL);
        ComboBox<String> versionComboBox = new ComboBox<String>(Utils.Constants.versions());

        GridPane.setVgrow(gameVersionLabel, Priority.ALWAYS);
        GridPane.setHgrow(gameVersionLabel, Priority.ALWAYS);

        gameVersionLabel.setTranslateX(30);
        gameVersionLabel.setTranslateY(-75);
        gameVersionLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        GridPane.setVgrow(versionComboBox, Priority.ALWAYS);
        GridPane.setHgrow(versionComboBox, Priority.ALWAYS);

        versionComboBox.setTranslateX(400 / 2);
        versionComboBox.setTranslateY(-75);
        versionComboBox.setMaxWidth(175);
        versionComboBox.getStyleClass().add("version-box");

        gameCard.getChildren().addAll(gameVersionLabel, versionComboBox);

        // CheckBox Optifine Mod
        CheckBox isOptifine = new CheckBox(Utils.Constants.HOMEPAGE_ISOPTIFINE_LABEL);

        GridPane.setVgrow(isOptifine, Priority.ALWAYS);
        GridPane.setHgrow(isOptifine, Priority.ALWAYS);

        isOptifine.setTranslateX(30);
        isOptifine.setTranslateY(75);
        isOptifine.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        // CheckBox ModLoader Included
        CheckBox isModLoader = new CheckBox(Utils.Constants.HOMEPAGE_ISMODLOADER_LABEL);

        GridPane.setVgrow(isModLoader, Priority.ALWAYS);
        GridPane.setHgrow(isModLoader, Priority.ALWAYS);

        isModLoader.setTranslateX(400 - 195);
        isModLoader.setTranslateY(73.75);
        isModLoader.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        gameCard.getChildren().addAll(isOptifine, isModLoader);

        // Launch Game Button
        Button launchButton = new Button(Utils.Constants.HOMEPAGE_LAUNCHBUTTON_LABEL);

        GridPane.setVgrow(launchButton, Priority.ALWAYS);
        GridPane.setHgrow(launchButton, Priority.ALWAYS);
        setCenterH(launchButton);
        setBottom(launchButton);

        launchButton.setTranslateY(-30);
        launchButton.setMinWidth(250);
        launchButton.setMinHeight(45);
        launchButton.getStyleClass().add("launchButton");

        launchButton.setOnMouseEntered(e -> this.layout.setCursor(Cursor.HAND));
        launchButton.setOnMouseExited(e -> this.layout.setCursor(Cursor.DEFAULT));

        gameCard.getChildren().add(launchButton);

        // Game Card
        GridPane.setVgrow(gameCard, Priority.ALWAYS);
        GridPane.setHgrow(gameCard, Priority.ALWAYS);
        gameCard.setStyle("-fx-background-color: rgb(18, 18, 18);");
    }
}
