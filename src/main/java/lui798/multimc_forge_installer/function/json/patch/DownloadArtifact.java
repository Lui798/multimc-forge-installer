package lui798.multimc_forge_installer.function.json.patch;

public class DownloadArtifact {
    private String path;
    private String url;
    private String sha1;
    private int size;

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }

    public String getSha1() {
        return sha1;
    }

    public int getSize() {
        return size;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
