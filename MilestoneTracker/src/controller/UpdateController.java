package controller;

import main.Launcher;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import updater.Updater;

/**
 * FXML Controller class
 *
 * @author adamp
 */
public class UpdateController implements Initializable {

    @FXML
    Button btnUpdate;
    @FXML
    TextField txtFieldProxy;
    @FXML
    TextField txtFieldPort;
    @FXML
    TextArea textAreaProgress;
    @FXML
    ProgressBar progressBar;

    private String currentVersion;
    
    
//    private Updater updater;
//    private String proxyAddress;
//    private int proxyPort;
//    private boolean useProxy = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnUpdate.setId("regbutton");
        btnUpdate.setOnAction((ActionEvent event) -> {
            currentVersion = Launcher.getHandler().getCurriculumVersion();
            update(currentVersion);
        });

    }

    //TODO
    // + valahova el kell helyezni a programba az aktuális verziót 
    // + a progi letölt egy update.zip-et, és kicsomagolja egy mappába aminek
    // a neve az update "verziója"
    private void update(String ver) {

        int proxyPort = 0;
        String proxyAddress = "";
        boolean useProxy = false;
        Updater u;

        try {
            proxyPort = Integer.parseInt(txtFieldPort.getText());

        } catch (NumberFormatException e) {
            useProxy = false;
            System.err.println("NumberFormatException");
        }
        proxyAddress = txtFieldProxy.getText();
        if (proxyPort > 0 && proxyPort < 65536 && !proxyAddress.isEmpty()) {
            useProxy = true;
        } else {
            useProxy = false;
        }
        
        u = new Updater(useProxy, proxyAddress, proxyPort, ver);
        
        progressBar.progressProperty().bind(u.progressProperty());
        textAreaProgress.textProperty().bind(u.messageProperty());
        
        new Thread(u).start();
        
//        new Thread() {
//            public void run() {
//                                    if (!u.isUpToDate()) {
//                        u.update();
//                    } else {
//                        System.out.println("Your program is up to date!");
//                    }
//
//               
//            }
//        }.start();
    }

    //TODO


}
