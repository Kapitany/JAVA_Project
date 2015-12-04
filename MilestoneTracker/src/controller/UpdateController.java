package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    Button btnSave;
    @FXML
    TextField txtFieldProxy;
    @FXML
    TextField txtFieldPort;

    private String currentVersion = "2012_01_01";
    private Updater updater;
    private String proxyAddress;
    private int proxyPort;
    private boolean useProxy = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnSave.setOnAction((ActionEvent event) -> {
            save();
        });

        btnUpdate.setOnAction((ActionEvent event) -> {
            update();
        });

    }

    
    //TODO
    // + valahova el kell helyezni a programba az aktuális verziót 
    // + a progi letölt egy update.zip-et, és kicsomagolja egy mappába aminek
    // a neve az update "verziója"
    private void update() {
        new Thread() {
            public void run() {
                updater = new Updater(useProxy, proxyAddress, proxyPort, currentVersion);
                if (!updater.isUpToDate()) {
                    updater.update();
                } else {
                    System.out.println("Your program is up to date!");
                }
            }
        }.start();
    }

    
    //TODO
    //szállnak az ikszepsönök mint állat ha rosszak az értékek
    private void save() {
        proxyPort = Integer.parseInt(txtFieldPort.getText());
        proxyAddress = txtFieldProxy.getText();
        if (proxyPort > 0 && proxyPort < 65536 && !proxyAddress.isEmpty()) {
            useProxy = true;
        } else {
            useProxy = false;
        }
    }

}
