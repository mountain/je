package je.util;

import java.net.URI;
import java.nio.file.*;

public class ResourcesIO {
    public static void copyResourceDir(String src, String dest) {
        try {
            String root = ResourcesIO.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            root = String.format("file:%s/jes.jar", root.substring(0, root.lastIndexOf("/")));
            URI uri = new URI("jar", root, null);
            System.out.println(uri);
            System.out.flush();
            FileSystem fs = FileSystems.newFileSystem(Path.of(uri));
            Files.walk(fs.getPath(src)).forEach(source -> {
                try {
                    if (!source.toFile().isDirectory()) {
                        Files.copy(source, Paths.get(dest, source.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
