package fr.pineapplemc.launcher;

import fr.flowarg.flowlogger.ILogger;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.utils.Utils;
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
    }

    public Logger getLogger() {
        return logger;
    }

    public static PineappleLauncher getInstance() {
        return instance;
    }
}
