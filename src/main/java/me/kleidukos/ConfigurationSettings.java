package me.kleidukos;

public class ConfigurationSettings {

    private boolean generateFileIfNotExist = false;



    public void setGenerateFileIfNotExist(boolean generateFileIfNotExist) {
        this.generateFileIfNotExist = generateFileIfNotExist;
    }

    public boolean isGenerateFileIfNotExist() {
        return generateFileIfNotExist;
    }
}
