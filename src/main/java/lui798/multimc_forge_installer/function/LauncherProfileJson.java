package lui798.multimc_forge_installer.function;

import lui798.multimc_forge_installer.util.FileHelper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LauncherProfileJson extends Function {
    private final Logger LOG = LoggerFactory.getLogger(LauncherProfileJson.class);

    @Override
    public String runFunction(String instanceDir, JFrame frame) {
        String filename = "launcher_profiles.json";

        InputStream launcherProfiles = getClass().getClassLoader().getResourceAsStream(filename);

        FileHelper.copyResource(launcherProfiles, instanceDir + File.separator + ".minecraft" + File.separator + filename);
        try {
            launcherProfiles.close();
        }
        catch (IOException ex) {
            LOG.error(ExceptionUtils.getStackTrace(ex));
        }

        return instanceDir;
    }
}
