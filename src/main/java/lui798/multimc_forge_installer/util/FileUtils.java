package lui798.multimc_forge_installer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    public static void copyResource(InputStream source, String output) {
        try {
            Files.copy(source, Paths.get(output), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException ex) {
            LOG.warn(ex.toString());
        }
    }
}
