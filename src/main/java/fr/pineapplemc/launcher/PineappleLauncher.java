package fr.pineapplemc.launcher;

import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.model.AuthProfile;
import fr.litarvan.openauth.model.response.RefreshResponse;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.ui.panels.pages.Login;
import fr.pineapplemc.launcher.utils.Utils;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class PineappleLauncher extends Application {

    private PanelManager manager;
    private static PineappleLauncher instance;

    private final Logger logger = LogManager.getLogger();
    private final File launcherDir = Utils.Helpers.generateGamePath("PineappleClient");
    private final Saver saver = new Saver(new File(launcherDir, "config.properties"));

    private AuthProfile gameProfile = null;

    public PineappleLauncher() {
        instance = this;
        this.launcherDir.mkdirs();
    }

    @Override
    public void start(Stage stage) throws Exception {
        logger.info("Java Version: " + System.getProperty("java.version"));
        logger.info("JavaFX version: " + System.getProperty("javafx.version"));

        this.manager = new PanelManager(this, stage);
        this.manager.init();

        if(this.isUserAlreadyConnected()) {
            logger.info("Hello " + gameProfile.getName());
        }else {
            this.manager.showPanel(new Login());
        }
    }

    public boolean isUserAlreadyConnected() {
        if(saver.get("accessToken") != null && saver.get("clientToken") != null) {
            Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);
            try {
                RefreshResponse response = authenticator.refresh(saver.get("accessToken"), saver.get("clientToken"));
                saver.set("accessToken", response.getAccessToken());
                saver.set("clientToken", response.getClientToken());
                this.gameProfile = response.getSelectedProfile();

                return true;
            }catch(AuthenticationException e) {
                saver.remove("accessToken");
                saver.remove("clientToken");
                logger.warn(e.getMessage());
            }
        }

        return false;
    }

    public void setGameProfile(AuthProfile gameProfile) {
        this.gameProfile = gameProfile;
    }

    public Logger getLogger() {
        return logger;
    }
    public static PineappleLauncher getInstance() {
        return instance;
    }
    public Saver getSaver() {
        return saver;
    }
    public AuthProfile getGameProfile() {
        return gameProfile;
    }
}
