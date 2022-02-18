package fr.pineapplemc.launcher.auth;

import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import fr.litarvan.openauth.model.AuthProfile;
import fr.litarvan.openauth.model.response.RefreshResponse;
import fr.pineapplemc.launcher.PineappleLauncher;
import fr.pineapplemc.launcher.ui.PanelManager;
import fr.pineapplemc.launcher.ui.panels.pages.Homepage;
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

    public void restoreSession() {
        if(saver.get("accessToken") != null && saver.get("clientToken") != null) {
            Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);
            try {
                RefreshResponse response = authenticator.refresh(saver.get("accessToken"), saver.get("clientToken"));
                saver.set("accessToken", response.getAccessToken());
                saver.set("clientToken", response.getClientToken());
                this.mojangProfile = response.getSelectedProfile();

                this.panelManager.showPanel(new Homepage());
                logger.info("User Session Restored !");
            }catch(AuthenticationException e) {
                saver.remove("accessToken");
                saver.remove("clientToken");

                this.panelManager.showPanel(new Login());
                logger.warn(e.getMessage());
            }
        }else if(saver.get("refreshToken") != null && saver.get("accessToken") != null) {
            MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
            try {
                MicrosoftAuthResult response = authenticator.loginWithRefreshToken(saver.get("refreshToken"));
                saver.set("refreshToken", response.getRefreshToken());
                saver.set("accessToken", response.getAccessToken());
                this.microsoftProfile = response.getProfile();

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
        if(saver.get("accessToken") != null && saver.get("clientToken") != null) {
            saver.remove("accessToken");
            saver.remove("clientToken");
            mojangProfile = null;

            this.panelManager.showPanel(new Login());
            logger.info("User Disconnected !");
        }else if(saver.get("refreshToken") != null && saver.get("accessToken") != null) {
            saver.remove("refreshToken");
            saver.remove("accessToken");
            microsoftProfile = null;

            this.panelManager.showPanel(new Login());
            logger.info("User Disconnected !");
        }
    }
}
