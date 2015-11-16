package updater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

/**
 * This utility extracts files and directories of a standard zip file to a
 * destination directory.
 *
 * @author adamp
 *
 */
public class UnzipUtility {

    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified
     * by destDirectory (will be created if does not exists)
     *
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    public void unzip(String zipFilePath, String destDirectory) throws IOException {

        ArchiveInputStream ais = null;
        InputStream is = null;
        try {
            new File(destDirectory).mkdirs();
            File inputFile = new File(zipFilePath);
            is = new FileInputStream(inputFile);
            ais = new ArchiveStreamFactory().createArchiveInputStream("zip", is);
            ZipEntry entry = null;
            while ((entry = (ZipArchiveEntry) ais.getNextEntry()) != null) {
                
                if (entry.getName().endsWith("/")) {
                    File dir = new File(destDirectory + File.separator + entry.getName());
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    continue;
                }

                File outFile = new File(destDirectory + File.separator + entry.getName());
                
                if (outFile.isDirectory()) {
                    continue;
                }
                
                if (outFile.exists()) {
                    continue;
                }
                
                FileOutputStream out = new FileOutputStream(outFile);
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = ais.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                    out.flush();
                }
                out.close();
            }
        } catch (ArchiveException ex) {
            Logger.getLogger(UnzipUtility.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ais.close();
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(UnzipUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
