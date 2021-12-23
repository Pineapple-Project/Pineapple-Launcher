package fr.pineapplemc.launcher.utils;

import fr.flowarg.flowcompat.Platform;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

    public class Helpers {
        public static File generateGamePath(String folderName) {
            Path path;
            switch(Platform.getCurrentPlatform()) {
                case WINDOWS:
                    path = Paths.get(System.getenv("APPDATA"));
                    break;
                case MAC:
                    path = Paths.get(System.getProperty("user.home"), "/Library/Application Support/");
                    break;
                case LINUX:
                    path = Paths.get(System.getProperty("user.home"), ".local/share");
                    break;
                default:
                    path = Paths.get(System.getProperty("user.home"));
            }

            path = Paths.get(path.toString(), folderName);
            return path.toFile();
        }
    }

    public class Constants {
        public static String LAUNCHER_NAME = "Pineapple Client";
        public static String LAUNCHER_VERSION = "1.0.0";
        public static String LAUNCHER_AUTHOR = "mrlulu51.jar";

        public static String LAUNCHER_TITLE = LAUNCHER_NAME + " v" + LAUNCHER_VERSION;

        public static String LOGIN_NOACCOUNT_LABEL = "You don't have an account ?";
        public static String LOGIN_REGISTER_LABEL = "Register here";
        public static String LOGIN_CONNECTBUTTON_LABEL = "Login";
        public static String LOGIN_LABEL = "Pineapple Client - Login";
        public static String LOGIN_USERNAME_LABEL = "Username or E-mail";
        public static String LOGIN_PASSWORD_LABEL = "Password";
        public static String LOGIN_CONNECTWITHMICROSOFT_LABEL = "Connect with Microsoft ?";
        public static String LOGIN_CONNECTWITHMOJANG_LABEL = "Connect with Mojang ?";
        public static String LOGIN_ADVERTISEMENT_LABEL = "Pineapple Project is not affiliated with Mojang AB or Microsoft.";
    }
}
