package me.kleidukos;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.util.Arrays;
import java.util.Map;

public class ConfigurationFile {

    private final YamlConfigurationLoader loader;
    private CommentedConfigurationNode node;

    protected ConfigurationFile(YamlConfigurationLoader loader){
        this.loader = loader;

        try {
            node = loader.load();
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }

    }

    public ConfigurationComponent getComponent(String... path){
        return new ConfigurationComponent(node.node(path));
    }

    public void addDefault(ConfigurationComponent component) throws ConfigurateException {
        ConfigurationNode defaultNode = node.node(component.getBasePath());

        if(!defaultNode.empty()){
            return;
        }

        for (Map.Entry<String, Object> entry : component.getValues().entrySet()){
            if(!entry.getKey().contains(".")){
                defaultNode.node(entry.getKey()).set(entry.getValue());
            }else {
                String[] paths = entry.getKey().split("\\.");
                defaultNode.node(paths).set(entry.getValue());
            }
        }

        loader.save(node);
    }
}
