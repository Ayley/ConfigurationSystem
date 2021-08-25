package me.kleidukos;

import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigurationProvider {

    private final ConfigurationSettings settings;

    public ConfigurationProvider() {
        this.settings = defaultSettings();
    }

    public ConfigurationProvider(ConfigurationSettings settings) {
        this.settings = settings;
    }

    public ConfigurationFile getFile(String name) {
        if (isFileExist(name)) {
            YamlConfigurationLoader loader = YamlConfigurationLoader.builder().path(getPath(name)).build();
            return new ConfigurationFile(loader);
        }

        if (settings.isGenerateFileIfNotExist()) {
            try {
                Files.createFile(getPath(name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            YamlConfigurationLoader loader = YamlConfigurationLoader.builder().path(getPath(name)).build();
            return new ConfigurationFile(loader);
        }

        return null;
    }

    public boolean isFileExist(String name) {
        return getPath(name).toFile().exists();
    }

    private Path getPath(String fileName) {
        return Paths.get(ConfigurationProvider.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1) + fileName);
    }

    private ConfigurationSettings defaultSettings() {
        return new ConfigurationSettings();
    }

}
