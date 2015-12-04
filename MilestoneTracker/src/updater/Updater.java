/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updater;

import handler.Launcher;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author Kapitany & adamp
 */
public class Updater extends Task {

    private final boolean useProxy;
    private final String proxyAddress;
    private final int proxyPort;

    private String updateDestination = "";
    private final String tempDirectory = "tmp";
    private final String zipFileDestination = tempDirectory + "\\update.zip";
    private final String versionFileDestination = tempDirectory + "\\update.ver";

    private String updateDescription = "";

    private String zipFileAddress;
    private final String versionAddress = "http://users.atw.hu/tgraf/version.txt";

    private String currentVerison;
    private String updateVersion;

    public String getUpdateVersion() {
        return updateVersion;
    }

    private String message = "";

    public Updater(boolean proxy, String proxyAddress, int proxyPort, String currentVersion) {
        this.useProxy = proxy;
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
        this.currentVerison = currentVersion;
    }

    private boolean isUpToDate() {
        BufferedReader reader = null;
        try {
            String filePath = download(versionFileDestination, versionAddress, useProxy);
            File versionFile = new File(filePath);
            reader = new BufferedReader(new FileReader(versionFile));
            StringBuilder sb = new StringBuilder();
            updateVersion = reader.readLine();
            zipFileAddress = reader.readLine();
            String inputLine = reader.readLine();
            while (inputLine != null) {
                sb.append(inputLine);
                sb.append(System.lineSeparator());
                inputLine = reader.readLine();
            }
            reader.close();
            updateDescription = sb.toString();
            message += ("Current version: " + currentVerison + "\nUpdate version: " + updateVersion + "\n" + updateDescription + "\n");
            updateMessage(message);

            if (!(currentVerison.equalsIgnoreCase(updateVersion))) {
                return false;
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    private String download(String filePath, String url, boolean isProxy) {

        new File(tempDirectory).mkdir();

        HttpURLConnection conn = null;

        try {

            URL link = new URL(url);

            conn = (HttpURLConnection) link.openConnection();
            conn.setRequestMethod("HEAD");
            int size = conn.getContentLength();
            conn.disconnect();
            updateProgress(0, size);
            message += ("Connecting to: " + url + "\n");
            updateMessage(message);

            InputStream in;

            if (isProxy) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
                in = new BufferedInputStream(link.openConnection(proxy).getInputStream());
            } else {
                in = new BufferedInputStream(link.openStream());
            }
            message += ("Successfully connected!" + "\n");
            updateMessage(message);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            int n = 0;
            message += ("Downloading..." + "\n");
            updateMessage(message);
            int bytesRead = 0;
            int bytesCount = 0;
            int filesize = 0;

            while ((bytesRead = in.read(buf, 0, 1024)) != -1 && !isCancelled()) {
                //        System.out.println(((double)bytesCount/(double)filesize)*100);
                out.write(buf, 0, bytesRead);
                updateProgress(bytesCount, filesize);
                bytesCount += bytesRead;
                out.flush();
            }
            updateProgress(filesize, filesize);
            message += ("Download completed!" + "\n");
            updateMessage(message);
//            
//            while ((n = in.read(buf)) != -1) {
//                out.write(buf, 0, n);
//                updateProgress(n, n);
//            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(response);
            fos.close();

            System.out.println(filePath + " downloaded");

            return filePath;
        } catch (MalformedURLException ex) {
            message += ("Malformed URL!" + "\n");
            updateMessage(message);
        } catch (IOException ex) {
            message += ("IOException" + "\n");
            updateMessage(message);
        } catch (Exception ex) {
            message += ("Exception (timeout?)" + "\n");
            updateMessage(message);
        }
        return null;
    }

    private void update() {
        if (!isUpToDate()) {
            message += ("Starting download new files (" + updateVersion + ")..." + "\n");
            updateMessage(message);
            String filePath = download(zipFileDestination, zipFileAddress, useProxy);

            updateMessage(message);
            updateDestination += updateVersion;
            if (new File(updateDestination).isDirectory()) {
                try {
                    message += ("Starting delete old direcotry (" + updateDestination + ")..." + "\n");
                    updateMessage(message);
                    delete(new File(updateDestination));
                    message += ("Deleted!" + "\n");
                    updateMessage(message);
                } catch (IOException ex) {
                    Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            unzipper(filePath, updateDestination);
            currentVerison = updateVersion;
            Launcher.getHandler().setCurriculumVersion(currentVerison);
        } else {
            message += ("Your curriculum is up to date" + "\n");
            updateMessage(message);
        }
    }

    private void unzipper(String source, String dest) {
        try {
            UnzipUtility zipFile = new UnzipUtility();
            zipFile.unzip(source, dest);
            message += ("File '" + source + "' unzipped to '" + dest + "'" + "\n");
            updateMessage(message);
            System.out.println("File '" + source + "' unzipped to '" + dest + "'");
        } catch (IOException ex) {
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                delete(new File(tempDirectory));
            } catch (IOException ex) {
                Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
            message += (f.getName() + " deleted!" + "\n");
            updateMessage(message);
            System.out.println(f.getName() + " is deleted!");
        }
        if (!f.delete()) {
            throw new FileNotFoundException("Failed to delete file: " + f);
        }
    }

    @Override
    protected Object call() throws Exception {

        update();

        return null;
    }

}
