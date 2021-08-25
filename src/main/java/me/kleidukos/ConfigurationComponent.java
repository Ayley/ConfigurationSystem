package me.kleidukos;

import org.spongepowered.configurate.ConfigurationNode;

import java.util.HashMap;

public class ConfigurationComponent {

    private ConfigurationNode rootNode;

    private HashMap<String, Object> values;

    private String[] basePath;

    protected ConfigurationComponent(ConfigurationNode node){
        this.rootNode = node;
    }

    public ConfigurationComponent(String... path){
        values = new HashMap<>();
        this.basePath = path;
    }

    public final ConfigurationComponent getComponent(String... path){
        return new ConfigurationComponent(rootNode.node(path));
    }

    public String getString(String path){
        if(!path.contains(".")){
            return rootNode.node(path).getString();
        }else {
            String[] paths = path.split("\\.");
            return rootNode.node(paths).getString();
        }
    }

    public String getString(String path, String defaultValue){
        if(!path.contains(".")){
            return rootNode.node(path).getString(defaultValue);
        }else {
            String[] paths = path.split("\\.");
            return rootNode.node(paths).getString(defaultValue);
        }
    }

    public int getInt(String path){
        if(!path.contains(".")){
            return rootNode.node(path).getInt();
        }else {
            String[] paths = path.split("\\.");
            return rootNode.node(paths).getInt();
        }
    }

    public int getInt(String path, int defaultValue){
        if(!path.contains(".")){
            return rootNode.node(path).getInt(defaultValue);
        }else {
            String[] paths = path.split("\\.");
            return rootNode.node(paths).getInt(defaultValue);
        }
    }

    public void setValue(String name, Object value){
        values.put(name, value);
    }

    public ConfigurationNode getNode() {
        return rootNode;
    }

    protected HashMap<String, Object> getValues() {
        return values;
    }

    protected String[] getBasePath() {
        return basePath;
    }
}
