package fr.pineapplemc.launcher.ui.panels.pages;

import fr.pineapplemc.launcher.PineappleLauncher;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.ui.panel.Panel;
import fr.pineapplemc.launcher.utils.Utils;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Login extends Panel {

    private final GridPane loginContainer = new GridPane();
    private final GridPane loginCard = new GridPane();
    private final GridPane bottomLoginCard = new GridPane();

    private final TextField usernameField = new TextField();
    private final Label usernameErrorLabel = new Label();

    private final PasswordField passwordField = new PasswordField();
    private final Label passwordErrorLabel = new Label();

    private final Button connectButton = new Button(Utils.Constants.LOGIN_CONNECTBUTTON_LABEL);

    private final ImageView microsoftIcon = new ImageView("images/login/microsoftIcon.png");

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStylesheetPath() {
        return "css/login.css";
    }

    @Override
    public void init(PanelManager manager) {
        super.init(manager);

        // Icons Resize
        microsoftIcon.setFitWidth(20);
        microsoftIcon.setFitHeight(20);

        // Background Image
        GridPane background = new GridPane();
        setCanTakeAllSize(background);
        background.setStyle("-fx-background-image: url('" + Utils.getCurrentDynamicBackground() + "');");
        background.getStyleClass().add("background");
        this.layout.add(background, 0, 0);

        // Background
        loginContainer.setMaxWidth(400);
        loginContainer.setMinWidth(400);
        loginContainer.setMaxHeight(580);
        loginContainer.setMinHeight(580);

        GridPane.setVgrow(loginContainer, Priority.ALWAYS);
        GridPane.setHgrow(loginContainer, Priority.ALWAYS);
        GridPane.setValignment(loginContainer, VPos.CENTER);
        GridPane.setHalignment(loginContainer, HPos.CENTER);

        RowConstraints bottomConstraints = new RowConstraints();
        bottomConstraints.setValignment(VPos.BOTTOM);
        bottomConstraints.setMaxHeight(55);
        loginContainer.getRowConstraints().addAll(new RowConstraints(), bottomConstraints);
        loginContainer.add(loginCard, 0, 0);
        loginContainer.add(bottomLoginCard, 0, 1);

        this.layout.getChildren().add(loginContainer);

        // Bottom Login Card
        GridPane.setVgrow(bottomLoginCard, Priority.ALWAYS);
        GridPane.setHgrow(bottomLoginCard, Priority.ALWAYS);
        bottomLoginCard.getStyleClass().add("bottomLoginCard");

        // "No Account ?" label
        Label noAccount = new Label(Utils.Constants.LOGIN_NOACCOUNT_LABEL);
        GridPane.setVgrow(noAccount, Priority.ALWAYS);
        GridPane.setHgrow(noAccount, Priority.ALWAYS);
        setTop(noAccount);
        setCenterH(noAccount);

        noAccount.getStyleClass().add("noAccountLabel");
        noAccount.setTranslateX(10);

        // "Register Here" Label
        Label registerHereLabel = new Label(Utils.Constants.LOGIN_REGISTER_LABEL);
        GridPane.setVgrow(registerHereLabel, Priority.ALWAYS);
        GridPane.setHgrow(registerHereLabel, Priority.ALWAYS);
        setBottom(registerHereLabel);
        setCenterH(registerHereLabel);

        registerHereLabel.getStyleClass().add("registerHereLabel");
        registerHereLabel.setTranslateX(15);
        registerHereLabel.setTranslateY(-10);
        registerHereLabel.setUnderline(true);

        registerHereLabel.setOnMouseEntered(e -> this.layout.setCursor(Cursor.HAND));
        registerHereLabel.setOnMouseExited(e -> this.layout.setCursor(Cursor.DEFAULT));
        registerHereLabel.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://pineapple-mc.fr/register"));
            }catch (IOException | URISyntaxException ex) {
                this.logger.error(ex.getMessage());
            }
        });

        // Login Card
        GridPane.setVgrow(loginCard, Priority.ALWAYS);
        GridPane.setHgrow(loginCard, Priority.ALWAYS);
        loginCard.getStyleClass().add("loginCard");

        // Login Label
        Label loginLabel = new Label(Utils.Constants.LOGIN_LABEL);
        GridPane.setVgrow(loginLabel, Priority.ALWAYS);
        GridPane.setHgrow(loginLabel, Priority.ALWAYS);
        setTop(loginLabel);
        setCenterH(loginLabel);
        loginLabel.getStyleClass().add("loginLabel");

        // Username Field
        Label usernameLabel = new Label(Utils.Constants.LOGIN_USERNAME_LABEL);
        GridPane.setVgrow(usernameLabel, Priority.ALWAYS);
        GridPane.setHgrow(usernameLabel, Priority.ALWAYS);
        setLeft(usernameLabel);
        setTop(usernameLabel);

        usernameLabel.setStyle("-fx-text-fill: #95bad3; -fx-font-size: 14px;");
        usernameLabel.setTranslateX(37.5);
        usernameLabel.setTranslateY(120);
        usernameField.getStyleClass().add("usernameField");

        usernameField.focusedProperty().addListener((_a, oldValue, newValue) -> {
            if(!newValue) updateLoginButtonState(usernameField, usernameErrorLabel);
        });

        GridPane.setVgrow(usernameField, Priority.ALWAYS);
        GridPane.setHgrow(usernameField, Priority.ALWAYS);
        setLeft(usernameField);
        setTop(usernameField);

        usernameField.setMaxWidth(325);
        usernameField.setMaxHeight(40);
        usernameField.setTranslateX(37.5);
        usernameField.setTranslateY(150);

        GridPane.setVgrow(usernameErrorLabel, Priority.ALWAYS);
        GridPane.setHgrow(usernameErrorLabel, Priority.ALWAYS);
        setLeft(usernameErrorLabel);
        setTop(usernameErrorLabel);

        usernameErrorLabel.setStyle("-fx-text-fill: red;");
        usernameErrorLabel.setTranslateX(37.5);
        usernameErrorLabel.setTranslateY(160 + 30);

        // Password Field
        Label passwordLabel = new Label(Utils.Constants.LOGIN_PASSWORD_LABEL);

        GridPane.setVgrow(passwordLabel, Priority.ALWAYS);
        GridPane.setHgrow(passwordLabel, Priority.ALWAYS);
        setLeft(passwordLabel);
        setTop(passwordLabel);

        passwordLabel.setStyle("-fx-text-fill: #95bad3; -fx-font-size: 14px;");
        passwordLabel.setTranslateX(37.5);
        passwordLabel.setTranslateY(240);

        passwordField.getStyleClass().add("passwordField");

        GridPane.setVgrow(passwordField, Priority.ALWAYS);
        GridPane.setHgrow(passwordField, Priority.ALWAYS);
        setLeft(passwordField);
        setTop(passwordField);

        passwordField.setMaxWidth(325);
        passwordField.setMaxHeight(40);
        passwordField.setTranslateX(37.5);
        passwordField.setTranslateY(270);

        passwordField.focusedProperty().addListener((_a, oldValue, newValue) -> {
            if(!newValue) updateLoginButtonState(passwordField, passwordErrorLabel);
        });

        GridPane.setVgrow(passwordErrorLabel, Priority.ALWAYS);
        GridPane.setHgrow(passwordErrorLabel, Priority.ALWAYS);
        setLeft(passwordErrorLabel);
        setTop(passwordErrorLabel);

        passwordErrorLabel.setStyle("-fx-text-fill: red;");
        passwordErrorLabel.setTranslateX(37.5);
        passwordErrorLabel.setTranslateY(280 + 30);

        // Advertisement Zone
        Label advertisementLabel = new Label(Utils.Constants.LOGIN_ADVERTISEMENT_LABEL);
        GridPane.setVgrow(advertisementLabel, Priority.ALWAYS);
        GridPane.setHgrow(advertisementLabel, Priority.ALWAYS);
        setLeft(advertisementLabel);
        setTop(advertisementLabel);

        advertisementLabel.setStyle("-fx-text-fill: white; -fx-font-size: 10px");
        advertisementLabel.setTranslateX(57.5);
        advertisementLabel.setTranslateY(280 + 30 + 50);

        // Login Button
        GridPane.setVgrow(connectButton, Priority.ALWAYS);
        GridPane.setHgrow(connectButton, Priority.ALWAYS);
        setLeft(connectButton);
        setBottom(connectButton);

        connectButton.setTranslateX(37.5);
        connectButton.setTranslateY(-30);
        connectButton.setMinWidth(325);
        connectButton.setMinHeight(50);
        connectButton.getStyleClass().add("connectButton");
        connectButton.setGraphic(microsoftIcon);

        connectButton.setOnMouseEntered(e -> this.layout.setCursor(Cursor.HAND));
        connectButton.setOnMouseExited(e -> this.layout.setCursor(Cursor.DEFAULT));
        connectButton.setOnMouseClicked(e -> PineappleLauncher.getInstance().getAuthManager().connect(usernameField.getText(), passwordField.getText()));

        bottomLoginCard.getChildren().clear();
        bottomLoginCard.getChildren().addAll(noAccount, registerHereLabel);
        loginCard.getChildren().clear();
        loginCard.getChildren().addAll(loginLabel, usernameLabel, usernameField, usernameErrorLabel, passwordLabel, passwordField, passwordErrorLabel, advertisementLabel, connectButton);
    }

    public void updateLoginButtonState(TextField textField, Label errorLabel) {
        if(textField.getText().length() == 0) {
            errorLabel.setText("Please fill the field.");
        }else {
            errorLabel.setText("");
        }

        connectButton.setDisable(!(usernameField.getText().length() > 0 && passwordField.getText().length() > 0));
    }
}
