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
    }
}
