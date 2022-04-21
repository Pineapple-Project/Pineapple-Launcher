package fr.pineapplemc.launcher;

import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import fr.pineapplemc.launcher.auth.AuthenticationManager;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.ui.panels.pages.Homepage;
import fr.pineapplemc.launcher.utils.Saver;
import fr.pineapplemc.launcher.utils.Utils;
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
    private final Saver saver = new Saver(new File(launcherDir, "config.properties").toPath());

    private AuthenticationManager authManager;
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

        this.authManager = new AuthenticationManager(this.saver, this.launcherDir, this.logger, this.manager, instance, microsoftGameProfile);
        authManager.restoreSession();
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
    public MinecraftProfile getMicrosoftGameProfile() {
        return microsoftGameProfile;
    }
    public AuthenticationManager getAuthManager() {
        return authManager;
    }
}
