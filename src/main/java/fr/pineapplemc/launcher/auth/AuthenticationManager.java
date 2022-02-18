package fr.pineapplemc.launcher.auth;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import fr.pineapplemc.launcher.PineappleLauncher;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.ui.panels.pages.Homepage;
import fr.pineapplemc.launcher.ui.panels.pages.Login;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.scene.control.Alert;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class AuthenticationManager {

    private Saver saver;
    private File launcherDir;
    private Logger logger;

    private PanelManager panelManager;
    private PineappleLauncher instance;

    private MinecraftProfile microsoftProfile;

    public AuthenticationManager(Saver saver, File launcherDir, Logger logger, PanelManager panelManager, PineappleLauncher instance, MinecraftProfile microsoftProfile) {
        this.saver = saver;
        this.launcherDir = launcherDir;
        this.logger = logger;
        this.panelManager = panelManager;
        this.instance = instance;
        this.microsoftProfile = microsoftProfile;
    }

    public void connect(String user, String password) {
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();

        try {
            MicrosoftAuthResult response = authenticator.loginWithCredentials(user, password);

            saver.set("accessToken", response.getAccessToken());
            saver.set("refreshToken", response.getRefreshToken());

            PineappleLauncher.getInstance().setMicrosoftGameProfile(response.getProfile());
            logger.info("User Logged !");

            panelManager.showPanel(new Homepage());
        }catch (MicrosoftAuthenticationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentication Error");
            alert.setHeaderText("An error was occurred during the authentication. Please try again later.");
            alert.setContentText(e.getMessage());
            alert.show();

            logger.warn(e.getMessage());
        }
    }

    public void restoreSession() {
        if(saver.get("refreshToken") != null && saver.get("accessToken") != null) {
            MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
            try {
                MicrosoftAuthResult response = authenticator.loginWithRefreshToken(saver.get("refreshToken"));
                saver.set("refreshToken", response.getRefreshToken());
                saver.set("accessToken", response.getAccessToken());
                instance.setMicrosoftGameProfile(response.getProfile());

                this.panelManager.showPanel(new Homepage());
                logger.info("User Session Restored !");
            }catch (MicrosoftAuthenticationException e) {
                saver.remove("accessToken");
                saver.remove("refreshToken");

                this.panelManager.showPanel(new Login());
                logger.warn(e.getMessage());
            }
        }else {
            this.panelManager.showPanel(new Login());
            logger.warn("Missing Session !");
        }
    }

    public void disconnect() {
        saver.remove("refreshToken");
        saver.remove("accessToken");
        microsoftProfile = null;

        this.panelManager.showPanel(new Login());
        logger.info("User Disconnected !");
    }
}
