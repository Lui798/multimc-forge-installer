package lui798.multimc_forge_installer.function.json.patch;

public class ForgeVersionJson {
    private String id;
    private String type;
    private String mainClass;
    private ForgeVersionArgument arguments;
    private ForgeVersionLibrary[] libraries;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMainClass() {
        return mainClass;
    }

    public ForgeVersionArgument getArguments() {
        return arguments;
    }

    public ForgeVersionLibrary[] getLibraries() {
        return libraries;
    }
}

