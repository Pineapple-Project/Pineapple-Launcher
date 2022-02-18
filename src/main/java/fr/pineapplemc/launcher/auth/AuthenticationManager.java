package fr.pineapplemc.launcher.auth;

import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import fr.litarvan.openauth.model.AuthProfile;
import fr.pineapplemc.launcher.PineappleLauncher;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.ui.panels.pages.Login;
import fr.theshark34.openlauncherlib.util.Saver;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class AuthenticationManager {

    private Saver saver;
    private File launcherDir;
    private Logger logger;

    private PanelManager panelManager;
    private PineappleLauncher instance;

    private AuthProfile mojangProfile;
    private MinecraftProfile microsoftProfile;

    public AuthenticationManager(Saver saver, File launcherDir, Logger logger, PanelManager panelManager, PineappleLauncher instance, MinecraftProfile microsoftProfile, AuthProfile mojangProfile) {
        this.saver = saver;
        this.launcherDir = launcherDir;
        this.logger = logger;
        this.panelManager = panelManager;
        this.instance = instance;
        this.mojangProfile = mojangProfile;
        this.microsoftProfile = microsoftProfile;
    }

    public void disconnect() {
        if(saver.get("accessToken") != null && saver.get("clientToken") != null) {
            saver.remove("accessToken");
            saver.remove("clientToken");
            mojangProfile = null;

            this.panelManager.showPanel(new Login());
            logger.info("User Disconnected !");
        }else if(saver.get("refreshToken") != null && saver.get("clientToken") != null) {
            saver.remove("refreshToken");
            saver.remove("clientToken");
            microsoftProfile = null;

            this.panelManager.showPanel(new Login());
            logger.info("User Disconnected !");
        }
    }
}
