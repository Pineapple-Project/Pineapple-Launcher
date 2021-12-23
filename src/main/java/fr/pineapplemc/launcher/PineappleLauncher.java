package fr.pineapplemc.launcher;

import fr.flowarg.flowlogger.ILogger;
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

        this.manager.showPanel(new Login());
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
}
