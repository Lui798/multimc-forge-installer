package lui798.multimc_forge_installer.function.json.serialclass;

public class ForgePatchJson {
    private int formatVersion;
    private String name;
    private String uid;
    private String mainClass;
    private String type;
    private String version;
    private String minecraftArguments;
    private ForgeVersionLibrary[] libraries;

    public int getFormatVersion() {
        return formatVersion;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getMainClass() {
        return mainClass;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public String getMinecraftArguments() {
        return minecraftArguments;
    }

    public ForgeVersionLibrary[] getLibraries() {
        return libraries;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setMinecraftArguments(String minecraftArguments) {
        this.minecraftArguments = minecraftArguments;
    }

    public void setLibraries(ForgeVersionLibrary[] libraries) {
        this.libraries = libraries;
    }
}
