package fr.pineapplemc.launcher.utils;

import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkManager {

    public static ImageView getPlayerAvatar(String uuid) {
        try {
            URL headAvatarURL = new URL("https://mc-heads.net/avatar/" + uuid);
            ImageView image = new ImageView(headAvatarURL.toString());
            return image;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
