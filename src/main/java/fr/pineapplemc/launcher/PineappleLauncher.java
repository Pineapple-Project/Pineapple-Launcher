package fr.pineapplemc.launcher;

import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.utils.Utils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class PineappleLauncher extends Application {

    private PanelManager manager;
    private static PineappleLauncher instance;
    private final ILogger logger;
    private final File launcherDir = Utils.Helpers.generateGamePath("PineappleClient");

    public PineappleLauncher() {
        instance = this;
        this.logger = new Logger("[Pineapple-Client]", new File(this.launcherDir, "debug.log"));
        this.launcherDir.mkdirs();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.logger.info("Starting launcher");
        this.manager = new PanelManager(this, stage);
        this.manager.init();
    }

    public ILogger getLogger() {
        return logger;
    }

    public static PineappleLauncher getInstance() {
        return instance;
    }
}
