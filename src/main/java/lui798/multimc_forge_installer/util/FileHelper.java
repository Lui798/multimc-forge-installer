package lui798.multimc_forge_installer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileHelper {

    private static final Logger LOG = LoggerFactory.getLogger(FileHelper.class);

    public static void copyResource(InputStream source, String output) {
        try {
            Files.copy(source, Paths.get(output), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException ex) {
            LOG.warn(ex.toString());
        }
    }

    public static File findFile(File[] files, String[] keywords, String[] no) {
        File outputFile = null;
        for (File f : files) {
            if (!containsKeyword(f.getName(), no)) {
                if (containsKeywords(f.getName(), keywords)) {
                    outputFile = f;
                }
            }
        }
        return outputFile;
    }

    private static boolean containsKeywords(String input, String[] items) {
        boolean result = false;
        for (String item : items) {
            if (input.toLowerCase().contains(item)) {
                result = true;
            }
            else {
                return false;
            }
        }
        return result;
    }

    private static boolean containsKeyword(String input, String[] items) {
        boolean result = true;
        for (String item : items) {
            if (input.toLowerCase().contains(item)) {
                return true;
            }
            else {
                result = false;
            }
        }
        return result;
    }
}
