package fr.pineapplemc.launcher.utils;

import fr.pineapplemc.launcher.PineappleLauncher;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Saver {

    private final Path file;
    private final Properties properties;

    public Saver(Path path) {
        this.file = path;
        this.properties = new Properties();

        if(Files.exists(this.file)) this.load();
        else {
            try {
                Files.createDirectories(this.file.getParent());
                Files.createFile(this.file);
            }catch (Throwable e) {
                PineappleLauncher.getInstance().getLogger().catching(e);
            }
        }
    }

    public void set(String key, String value) {
        this.properties.setProperty(key, value);
        this.save();
    }

    public String get(String key) { return this.properties.getProperty(key); }

    public void save() {
        try {
            final BufferedWriter writer = Files.newBufferedWriter(this.file);
            this.properties.store(writer, "");
            writer.close();
        }catch (Throwable e) {
            PineappleLauncher.getInstance().getLogger().error("Can't save the properties !", e);
        }
    }

    public void load() {
        try {
            this.properties.load(Files.newInputStream(this.file));
        }catch (Throwable e) {
            PineappleLauncher.getInstance().getLogger().error("Can't load the properties !", e);
        }
    }

    public void remove(String key) {
        this.properties.remove(key);
        this.save();
    }
}
