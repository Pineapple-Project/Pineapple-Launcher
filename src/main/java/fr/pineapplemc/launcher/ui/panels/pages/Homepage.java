package fr.pineapplemc.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.pineapplemc.launcher.PineappleLauncher;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.ui.panel.Panel;
import fr.pineapplemc.launcher.utils.NetworkManager;
import fr.pineapplemc.launcher.utils.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
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

        // Player Head Image
        ImageView playerHeadImage = NetworkManager.getPlayerAvatar(PineappleLauncher.getInstance().getMojangGameProfile().getId());

        GridPane.setVgrow(playerHeadImage, Priority.ALWAYS);
        GridPane.setHgrow(playerHeadImage, Priority.ALWAYS);
        setCenterV(playerHeadImage);
        setRight(playerHeadImage);

        playerHeadImage.setFitWidth(40);
        playerHeadImage.setFitHeight(40);
        playerHeadImage.setOpacity(.7f);

        playerHeadImage.setOnMouseEntered(e -> { playerHeadImage.setOpacity(1f); this.layout.setCursor(Cursor.HAND); });
        playerHeadImage.setOnMouseExited(e -> { playerHeadImage.setOpacity(.7f); this.layout.setCursor(Cursor.DEFAULT); });

        Tooltip playerHeadHoverText = new Tooltip("Player Settings");
        playerHeadHoverText.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        Tooltip.install(playerHeadImage, playerHeadHoverText);

        topGameCard.getChildren().add(playerHeadImage);

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

        // Game Card
        GridPane.setVgrow(gameCard, Priority.ALWAYS);
        GridPane.setHgrow(gameCard, Priority.ALWAYS);
        gameCard.setStyle("-fx-background-color: rgb(18, 18, 18);");
    }
}
