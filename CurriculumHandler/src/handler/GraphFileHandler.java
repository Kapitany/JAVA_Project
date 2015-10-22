/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Otti
 */
public class GraphFileHandler {

    private final ArrayList<ArrayList<Subject>> graphContainer;
    private final String graphName;
    private final int numSemesters;
    private final int[] creditPerSemester;
    private final int numExtraCreditTypes;

    public GraphFileHandler(ArrayList<Subject> subjectList) throws Exception {
        graphContainer = new ArrayList<>();

        File file = new File("graf.txt");

        BufferedReader reader;
        String line;
        ArrayList<String> tmpList = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                tmpList.add(line);
            }

        } catch (FileNotFoundException ex) {
            System.err.println("File graf.txt should be in the project library!");
        } catch (IOException ex) {
            System.err.println("Unexpected IOException occured!");
        }

        String[] tmpArray = null;
        tmpArray = tmpList.get(0).split(":");
        graphName = tmpArray[1];
        tmpList.remove(0);

        tmpArray = tmpList.get(0).split(":");
        numSemesters = Integer.valueOf(tmpArray[1]);
        tmpList.remove(0);

        creditPerSemester = new int[numSemesters];
        for (int i = 0; i < numSemesters; i++) {
            graphContainer.add(new ArrayList<Subject>());

            tmpArray = tmpList.get(0).split(":");
            creditPerSemester[i] = Integer.valueOf(tmpArray[3]);
            int numberOfSubject = Integer.valueOf(tmpArray[4]);
            tmpList.remove(0);
            tmpArray = tmpList.get(0).split(":");
            tmpList.remove(0);

            File actualSemester = new File(tmpArray[1]);
            ArrayList<String> actualSemesterSubjectList = new ArrayList<>();
            try {
                BufferedReader semesterReader = new BufferedReader(new FileReader(actualSemester));
                while ((line = semesterReader.readLine()) != null) {
                    actualSemesterSubjectList.add(line);
                }
                if (actualSemesterSubjectList.size() != numberOfSubject) {
                    throw new Exception("File " + tmpArray[1] + " semester file is corrupted!");
                }
                for (int j = 0; j < actualSemesterSubjectList.size(); j++) {
                    boolean isFound = false;
                    for (Subject actSubject : subjectList) {
                        if(actualSemesterSubjectList.get(j).equals(actSubject.getSubjectCode())){
                            graphContainer.get(i).add(actSubject);
                            isFound = true;
                        }
                    }
                    if(!isFound){
                        String [] tmp = actualSemesterSubjectList.get(j).split(":");
                        Subject blankSubject = new Subject(tmp[0], "-", Integer.valueOf(tmp[1]), new String[0], tmp[0], 0);
                        graphContainer.get(i).add(blankSubject);
                    }
                }
            } catch (FileNotFoundException ex) {
                System.err.println("File " + tmpArray[1] + " not found!");
            } catch (IOException ex) {
                System.err.println("Unexpected IOException occured!");
            }
        }
        
        tmpArray = tmpList.get(0).split(":");
        numExtraCreditTypes = Integer.valueOf(tmpArray[1]);
        tmpList.remove(0);
        
        //TODO grafredek tárolása
        
    }

}
