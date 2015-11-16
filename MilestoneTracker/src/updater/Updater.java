/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updater;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kapitany & adamp
 */
public class Updater {

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

    public Updater(boolean proxy, String proxyAddress, int proxyPort, String currentVersion) {
        this.useProxy = proxy;
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
        this.currentVerison = currentVersion;
    }

    public boolean isUpToDate() {
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

        try {
            URL link = new URL(url);
            InputStream in;

            if (isProxy) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
                in = new BufferedInputStream(link.openConnection(proxy).getInputStream());
            } else {
                in = new BufferedInputStream(link.openStream());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            int n = 0;
            while ((n = in.read(buf)) != -1) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(response);
            fos.close();

            System.out.println(filePath + " downloaded");

            return filePath;
        } catch (MalformedURLException ex) {
            System.out.println("Failed to connect to '" + url + "'");
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void update() {
        String filePath = download(zipFileDestination, zipFileAddress, useProxy);
        updateDestination += updateVersion;
        if (new File(updateDestination).isDirectory()) {
            try {
                delete(new File(updateDestination));
            } catch (IOException ex) {
                Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        unzipper(filePath, updateDestination);
        currentVerison = updateVersion;
    }

    private void unzipper(String source, String dest) {
        try {
            UnzipUtility zipFile = new UnzipUtility();
            zipFile.unzip(source, dest);
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
            System.out.println(f.getName() + " is deleted!");
        }
        if (!f.delete()) {
            throw new FileNotFoundException("Failed to delete file: " + f);
        }
    }

}
