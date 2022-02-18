package fr.pineapplemc.launcher.utils;

import fr.flowarg.flowcompat.Platform;
import fr.pineapplemc.launcher.game.GameVersion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        public static ObservableList<GameVersion> getVersionList() {
            GameVersion v1181 = new GameVersion("1.18.1", "1181");
            GameVersion v189 = new GameVersion("1.8.9", "189");

            ObservableList<GameVersion> list = FXCollections.observableArrayList(v1181, v189);
            return list;
        }
    }

    public static String getCurrentDynamicBackground() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        String path = "/images/backgrounds/dynamic/" + dateFormat.format(date) + ".png";
        return path;
    }

    public class Constants {
        public static String LAUNCHER_NAME = "Pineapple Client";
        public static String LAUNCHER_VERSION = "1.0.0";
        public static String LAUNCHER_AUTHOR = "mrlulu51.jar";

        public static String LOGIN_NOACCOUNT_LABEL = "You don't have an account ?";
        public static String LOGIN_REGISTER_LABEL = "Register here";
        public static String LOGIN_CONNECTBUTTON_LABEL = "Login";
        public static String LOGIN_LABEL = "Pineapple Client - Login";
        public static String LOGIN_USERNAME_LABEL = "Username or E-mail";
        public static String LOGIN_PASSWORD_LABEL = "Password";
        public static String LOGIN_ADVERTISEMENT_LABEL = "Pineapple Project is not affiliated with Mojang AB or Microsoft.";

        public static String HOMEPAGE_GAMESETTINGS_LABEL = "Game Settings";
        public static String HOMEPAGE_LAUNCHBUTTON_LABEL = "Launch";
    }
}
