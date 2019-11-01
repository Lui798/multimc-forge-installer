package lui798.multimc_forge_installer.function;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lui798.multimc_forge_installer.function.json.serialclass.DownloadArtifact;
import lui798.multimc_forge_installer.function.json.serialclass.ForgePatchJson;
import lui798.multimc_forge_installer.function.json.serialclass.ForgeVersionJson;
import lui798.multimc_forge_installer.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.*;
import java.nio.file.FileSystems;

public class ForgePatch extends Function {
    private final Logger LOG = LoggerFactory.getLogger(ForgePatch.class);

    @Override
    public String runFunction(String instanceDir, JFrame frame) {
        //Create directory if it doesn't exist
        File dir = new File(instanceDir + File.separator + "patches");
        if (!dir.exists())
            dir.mkdir();

        //Copy blank patch to patches folder
        String filename = "net.minecraftforge.json";
        InputStream forgePatch = getClass().getClassLoader().getResourceAsStream(filename);
        FileUtils.copyResource(forgePatch, instanceDir + File.separator + dir.getName() + File.separator + filename);
        try {
            forgePatch.close();
        }
        catch (IOException ex) {
            LOG.error(ex.toString());
        }

        String[] keywords = {"forge"};
        String[] no = {};
        File[] files = FileSystems.getDefault().getPath(instanceDir + File.separator + ".minecraft" + File.separator + "versions").toFile().listFiles();

        File forgeVersion = FileUtils.findFile(files, keywords, no);

        if (forgeVersion == null) {
            int choice = JOptionPane.showOptionDialog(frame, "Could not find a forge version in .minecraft/versions" +
                            "Please run the installer again and check \nthat the forge installer is installing to the .minecraft of your MultiMC instance.",
                    "Version Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"OK"}, "OK");
            if (choice == 0)
                System.exit(0);
        }
        else {
            try {
                File patchFile = new File(instanceDir + File.separator + dir.getName() + File.separator + filename);
                FileReader versionReader = new FileReader(forgeVersion.getPath() + File.separator + forgeVersion.getName() + ".json");
                FileReader patchReader = new FileReader(patchFile);
                ForgeVersionJson version = new Gson().fromJson(versionReader, ForgeVersionJson.class);
                ForgePatchJson patch = new Gson().fromJson(patchReader, ForgePatchJson.class);

                patch.setMainClass(version.getMainClass());
                patch.setType(version.getType());
                patch.setVersion(version.getId().substring(version.getId().lastIndexOf("-") + 1));
                String args = "";
                for (String arg : version.getArguments().getGame()) {
                    args += arg + " ";
                }
                patch.setMinecraftArguments(patch.getMinecraftArguments() + args.substring(0, args.length()-1));
                patch.setLibraries(version.getLibraries());
                DownloadArtifact forgeArtifact = patch.getLibraries()[0].getDownloads().getArtifact();
                forgeArtifact.setUrl("https://files.minecraftforge.net/maven/"
                        + forgeArtifact.getPath().substring(0, forgeArtifact.getPath().length() - 4) + "-launcher.jar");

                versionReader.close();
                patchReader.close();

                FileWriter patchWriter = new FileWriter(patchFile);
                Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
                patchWriter.write(gsonPretty.toJson(patch));
                patchWriter.close();
            }
            catch (IOException ex) {
                LOG.error(ex.toString());
            }
        }

        return instanceDir;
    }
}
