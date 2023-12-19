package je.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class ResourcesIO {

    public static Map<URI, FileSystem> fsMap = new HashMap<>();

    public static void copyResourceDir(String src, String dest) {
        try {
            String root = ResourcesIO.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            root = String.format("file:%s/jes.jar", root.substring(0, root.lastIndexOf("/")));
            root = String.format("%s!/", root);
            URI uri = new URI("jar", root, null);
            if (!fsMap.containsKey(uri)) {
                Map<String, String> env;
                env = new HashMap<String, String>() {
                    {
                        put("create", "true");
                    }
                };
                FileSystem fs = FileSystems.newFileSystem(uri, env);
                fsMap.put(uri, fs);
            }
            FileSystem fs = fsMap.get(uri);
            Files.walk(fs.getPath(src)).forEach(source -> {
                try {
                    InputStream is = Files.newInputStream(source);
                    new File(dest).mkdirs();
                    File tgt = new File(dest, source.getFileName().toString());
                    if (!Files.exists(tgt.toPath())) {
                        OutputStream os = new FileOutputStream(tgt);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = is.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                        }
                    }
                } catch (java.nio.file.FileSystemException e) {
                    //pass
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
