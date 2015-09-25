package filesystem_tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tomega
 */
public class FileSystem_tester {

    private final ArrayList<Subject> subjectList = new ArrayList<>();

    @SuppressWarnings("null")
    private boolean subjectListLoader() throws FileNotFoundException, IOException {
        File folder = new File("baszkodok");
        if (folder == null) {
            System.out.println("The baszkodok folder should be in the project library!");
            System.out.println("Now exiting...");
            return false;
        }
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                String[] fileData = new String[5];
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    fileData[i] = line;
                    i++;
                }
                String[] reqirements = fileData[3].split(";");
                subjectList.add(new Subject(fileData[0],fileData[1],Integer.valueOf(fileData[2]),reqirements,fileData[4]));
            }
        }
        return true;
    }

    private void start() throws FileNotFoundException, IOException {
        if (subjectListLoader()) {
            for (Subject subject : subjectList){
                System.out.println(subject.toString());
                System.out.println("\n");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileSystem_tester tester = new FileSystem_tester();
        tester.start();
    }

}
