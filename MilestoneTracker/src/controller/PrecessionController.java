package controller;

import main.Launcher;
import builder.Subject;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author adamp
 */
public class PrecessionController implements Initializable {

    @FXML
    GridPane pane;
    
    @FXML
    public PieChart dif1;
    
    @FXML
    public PieChart hum;
    
    @FXML
    public PieChart dif2;
    
    @FXML
    public PieChart szab;
    
    @FXML
    public PieChart kot;
    
    @FXML
     public StackedBarChart allDiagramm;
    
    ObservableList<PieChart.Data> kotChartDataInicializer
                = FXCollections.observableArrayList(
                        new PieChart.Data("Kész", 0),
                        new PieChart.Data("Hátra van", 100));
    
    ObservableList<PieChart.Data> szabChartDataInicializer
                = FXCollections.observableArrayList(
                        new PieChart.Data("Kész", 0),
                        new PieChart.Data("Hátra van", 100));
    
    ObservableList<PieChart.Data> dif1ChartDataInicializer
                = FXCollections.observableArrayList(
                        new PieChart.Data("Kész", 0),
                        new PieChart.Data("Hátra van", 100));
    
    ObservableList<PieChart.Data> dif2ChartDataInicializer
                = FXCollections.observableArrayList(
                        new PieChart.Data("Kész", 0),
                        new PieChart.Data("Hátra van", 100));
    
    ObservableList<PieChart.Data> humChartDataInicializer
                = FXCollections.observableArrayList(
                        new PieChart.Data("Kész", 0),
                        new PieChart.Data("Hátra van", 100));
    
    
    
    
    
    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 1;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length ] + ";");
            i++;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dif1.setData(dif1ChartDataInicializer);
        dif2.setData(dif2ChartDataInicializer);
        szab.setData(szabChartDataInicializer);
        kot.setData(kotChartDataInicializer);
        hum.setData(humChartDataInicializer);
        
        applyCustomColorSequence(
                kotChartDataInicializer,
                 "red",
                "green"
        );
        
        applyCustomColorSequence(
                dif2ChartDataInicializer,
                 "red",
                "green"
        );
        
        applyCustomColorSequence(
                dif1ChartDataInicializer,
                 "red",
                "green"
        );
         applyCustomColorSequence(
                szabChartDataInicializer,
                 "red",
                "green"
        );
         
         applyCustomColorSequence(
                humChartDataInicializer,
                 "red",
                "green"
        );
        
        pane.setStyle("-fx-border-color:#000000");

        int numSemesters = Launcher.getGraphHandler().getNumSemesters();
        for (int i = 0; i < numSemesters/2+1; i++) {
            ColumnConstraints column = new ColumnConstraints();
            pane.getColumnConstraints().add(column);
        }

        
        ArrayList<ArrayList<Subject>> subjectsPerSemester = Launcher.getGraphHandler().getGraphContainer();

        for (int semester = 0; semester < numSemesters; semester++) {
            VBox tmpBox = new VBox();
            Label semesterCount = new Label((semester + 1) + ". félév");
            semesterCount.setStyle("-fx-font-weight: bold");
            semesterCount.setUnderline(true);
            tmpBox.getChildren().add(semesterCount);
            
            for (int subj = 0; subj < subjectsPerSemester.get(semester).size(); subj++) {
                Label lblSubjetName;

                String tempName = subjectsPerSemester.get(semester).get(subj).getSubjectName();
                tempName += " - " +subjectsPerSemester.get(semester).get(subj).getCreditValue();
                if(tempName.length() >= 25){ 
                    lblSubjetName = new Label();                  
                    lblSubjetName.setText(tempName);
                    lblSubjetName.setEllipsisString(tempName);
                    lblSubjetName.setFont(new Font(10));
                }else{
                    lblSubjetName = new Label(tempName);
                    lblSubjetName.setFont(new Font(10));
                }
                tmpBox.getChildren().add(lblSubjetName);
                
                tmpBox.setAlignment(Pos.CENTER);
                
            }
            tmpBox.setOnMouseEntered(new EventHandler<MouseEvent>(){
                @Override public void handle(MouseEvent e) {
                tmpBox.setScaleX(1.1);
                tmpBox.setScaleY(1.1);
                //tmpBox.setId("tmpbig");
                tmpBox.toFront();
        }
        });
            tmpBox.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                tmpBox.setScaleX(1);
                tmpBox.setScaleY(1);
                //tmpBox.setId("tmpbig");
                tmpBox.toBack();
        }
        });
            if(semester <= (numSemesters/2)){
                pane.add(tmpBox, semester, 0);
            }else{
                pane.add(tmpBox, semester-(numSemesters/2)-1, 1);
            }
        }
        
        pane.setAlignment(Pos.CENTER);
    }
}
