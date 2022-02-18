package fr.pineapplemc.launcher.game;

public class GameVersion {

    private String version;
    private String code;

    public GameVersion(String version, String code) {
        this.version = version;
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.version;
    }
}
