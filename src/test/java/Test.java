import me.kleidukos.ConfigurationComponent;
import me.kleidukos.ConfigurationFile;
import me.kleidukos.ConfigurationProvider;
import me.kleidukos.ConfigurationSettings;
import org.spongepowered.configurate.ConfigurateException;

public class Test {

    public static void main(String[] args) {
        ConfigurationSettings settings = new ConfigurationSettings();
        settings.setGenerateFileIfNotExist(true);

        ConfigurationProvider provider = new ConfigurationProvider(settings);

        ConfigurationFile file = provider.getFile("test.yaml");

        ConfigurationComponent component = new ConfigurationComponent("de", "items");
        ConfigurationComponent component1 = new ConfigurationComponent("en", "items");

        component.setValue("language", "de");
        component1.setValue("language", "en");

        component.setValue("sa.bne.gr", 2);
        component1.setValue("sa.bne.gr", 103);

        try {
            file.addDefault(component);
            file.addDefault(component1);
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }

        ConfigurationComponent get = file.getComponent("de", "items");

        System.out.println(get.getString("language"));

        ConfigurationComponent get1 = get.getComponent("sa", "bne");

        System.out.println(get1.getString("gr"));
    }

}
