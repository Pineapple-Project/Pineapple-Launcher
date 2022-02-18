package fr.pineapplemc.launcher;

import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import fr.litarvan.openauth.model.AuthProfile;
import fr.litarvan.openauth.model.response.RefreshResponse;
import fr.pineapplemc.launcher.auth.AuthenticationManager;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.ui.panels.pages.Homepage;
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

    private final Logger logger = LogManager.getLogger(PineappleLauncher.class);
    private final File launcherDir = Utils.Helpers.generateGamePath("Pineapple Client");
    private final Saver saver = new Saver(new File(launcherDir, "config.properties"));

    private AuthenticationManager authManager;
    private AuthProfile mojangGameProfile = null;
    private MinecraftProfile microsoftGameProfile = null;

    public PineappleLauncher() {
        instance = this;
        this.launcherDir.mkdirs();

    }

    @Override
    public void start(Stage stage) throws Exception {
        Thread.currentThread().setName("Pineapple Client Main Thread");

        logger.info("Java Version: " + System.getProperty("java.version"));
        logger.info("JavaFX version: " + System.getProperty("javafx.version"));

        this.manager = new PanelManager(this, stage);
        this.manager.init();

        this.authManager = new AuthenticationManager(this.saver, this.launcherDir, this.logger, this.manager, instance, microsoftGameProfile, mojangGameProfile);
        authManager.restoreSession();
    }

    public void setMojangGameProfile(AuthProfile mojangGameProfile) {
        this.mojangGameProfile = mojangGameProfile;
    }
    public void setMicrosoftGameProfile(MinecraftProfile microsoftGameProfile) {
        this.microsoftGameProfile = microsoftGameProfile;
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
    public AuthProfile getMojangGameProfile() {
        return mojangGameProfile;
    }
    public MinecraftProfile getMicrosoftGameProfile() {
        return microsoftGameProfile;
    }
    public AuthenticationManager getAuthManager() {
        return authManager;
    }
}
