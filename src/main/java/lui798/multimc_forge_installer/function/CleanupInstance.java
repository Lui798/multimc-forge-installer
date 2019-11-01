package lui798.multimc_forge_installer.function;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class CleanupInstance extends Function {
    private final Logger LOG = LoggerFactory.getLogger(CleanupInstance.class);

    @Override
    public String runFunction(String instanceDir, JFrame frame) {
        String minecraftDir = instanceDir + File.separator + ".minecraft" + File.separator;

        try {
            FileUtils.deleteDirectory(new File(minecraftDir + "versions"));
            FileUtils.deleteDirectory(new File(minecraftDir + "libraries"));
            FileUtils.forceDelete(new File(minecraftDir + "launcher_profiles.json"));
        }
        catch (IOException ex) {
            LOG.error(ExceptionUtils.getStackTrace(ex));
        }

        return instanceDir;
    }
}
