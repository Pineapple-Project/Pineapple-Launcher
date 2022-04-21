package fr.pineapplemc.launcher.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.flowarg.flowcompat.Platform;
import fr.pineapplemc.launcher.PineappleLauncher;
import fr.pineapplemc.launcher.game.GameVersion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils {

    public class Helpers {
        public static File generateGamePath(String folderName) {
            Path path = switch (Platform.getCurrentPlatform()) {
                case WINDOWS -> Paths.get(System.getenv("APPDATA"));
                case MAC -> Paths.get(System.getProperty("user.home"), "/Library/Application Support/");
                case LINUX -> Paths.get(System.getProperty("user.home"), ".local/share");
            };

            path = Paths.get(path.toString(), folderName);
            return path.toFile();
        }

        public static File getFileFromResources(String filename) throws URISyntaxException {
            URL resource = PineappleLauncher.class.getClassLoader().getResource(filename);
            if(resource == null) PineappleLauncher.getInstance().getLogger().error("File not found!", IllegalArgumentException::new);

            assert resource != null;
            return new File(resource.toURI());
        }
    }

    public static String getCurrentDynamicBackground() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        return "/images/backgrounds/dynamic/" + dateFormat.format(date) + ".png";
    }

    public static ImageView getPlayerHead(String playerUUID) {
        return new ImageView("https://mc-heads.net/avatar/" + playerUUID);
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
        public static String HOMEPAGE_GAMEVERSION_LABEL = "Game Version:";
        public static String HOMEPAGE_ISOPTIFINE_LABEL = "Optifine";
        public static String HOMEPAGE_ISMODLOADER_LABEL = "Blueberry Modloader";

        public static ObservableList<String> versions() {
            Gson g = new Gson();
            ObservableList v = FXCollections.emptyObservableList();

            try {
                GameVersion object = g.fromJson(new FileReader(Helpers.getFileFromResources("versions.json")), GameVersion.class);
                v = FXCollections.observableList(object.getVersions());
            } catch (FileNotFoundException | URISyntaxException e) {
                PineappleLauncher.getInstance().getLogger().error(e);
            }

            return v;
        }
    }
}
