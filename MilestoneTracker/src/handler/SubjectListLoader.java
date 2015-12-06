package handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Tomega
 */
public class SubjectListLoader {
    
    private ArrayList<Subject> subjectList;
    private int numSubjects;

    @SuppressWarnings("null")
    public SubjectListLoader(String pathSubjects) throws Exception{
        subjectList = new ArrayList<>();
        numSubjects = 0;
        File folder = new File(pathSubjects);
        if (folder == null) {
            System.out.println("The "+ pathSubjects +" folder should be in the project library!");
            System.out.println("Now exiting...");
        }
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                String[] fileData = new String[6];
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    fileData[i] = line;
                    i++;
                }
                String[] reqirements = fileData[3].split(";");
                subjectList.add(new Subject(fileData[0], fileData[1], Integer.valueOf(fileData[2]), reqirements, fileData[4], Integer.valueOf(fileData[5])));
                numSubjects++;
            }
        }
        System.out.println(numSubjects + " subjects have been loaded!");
    }
    
    public void listSubjects() {
        System.out.println("******************************************");
        System.out.println("LISTING SUBJECTS:");
        System.out.println("******************************************");
        subjectList.stream().forEach((subject) -> {
            System.out.println(subject.toString());
        });
        System.out.println("******************************************");
    }
    
    public ArrayList<Subject> getSubjectList() {
        return subjectList;
    }
 
    public int getNumSubjects() {
        return numSubjects;
    }
    
}
