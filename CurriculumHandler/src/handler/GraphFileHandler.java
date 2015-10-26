package handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Otti
 */
public class GraphFileHandler {

    private final ArrayList<ArrayList<Subject>> graphContainer;
    private final String graphName;
    private final int numSemesters;
    private final int[] creditsPerSemester;
    private final int numExtraCreditTypes;
    private final String[] extraCreditTypes;

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

        @SuppressWarnings("UnusedAssignment")
        String[] tmpArray = null;
        tmpArray = tmpList.get(0).split(":");
        graphName = tmpArray[1];
        tmpList.remove(0);

        tmpArray = tmpList.get(0).split(":");
        numSemesters = Integer.valueOf(tmpArray[1]);
        tmpList.remove(0);

        creditsPerSemester = new int[numSemesters];
        for (int i = 0; i < numSemesters; i++) {
            graphContainer.add(new ArrayList<>());

            tmpArray = tmpList.get(0).split(":");
            creditsPerSemester[i] = Integer.valueOf(tmpArray[3]);
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
                for (String wantedSubject : actualSemesterSubjectList) {
                    boolean isFound = false;
                    for (Subject s : subjectList) {
                        if (wantedSubject.equals(s.getSubjectCode())) {
                            graphContainer.get(i).add(s);
                            isFound = true;
                        }
                    }
                    if (!isFound) {
                        String[] tmp = wantedSubject.split(":");
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

        extraCreditTypes = new String[numExtraCreditTypes];

        for (int i = 0; i < numExtraCreditTypes; i++) {
            graphContainer.add(new ArrayList<>());
            tmpArray = tmpList.get(0).split(":");
            extraCreditTypes[i] = tmpArray[1];
            int numSubjects = Integer.valueOf(tmpArray[2]);
            tmpList.remove(0);

            tmpArray = tmpList.get(0).split(":");
            File subjListFile = new File(tmpArray[1]);
            ArrayList<String> actualExtraCreditTypeList = new ArrayList<>();
            BufferedReader fileReader = new BufferedReader(new FileReader(subjListFile));
            if (subjListFile.exists()) {
                fileReader = new BufferedReader(new FileReader(subjListFile));
                while((line = fileReader.readLine()) != null) {
                    actualExtraCreditTypeList.add(line);
                }
                if (actualExtraCreditTypeList.size() != numSubjects) {
                    throw new Exception("File " + tmpArray[1] + " semester file is corrupted!");
                }
                for (String wantedSubject : actualExtraCreditTypeList) {
                    for (Subject s : subjectList) {
                        if (wantedSubject.equals(s.getSubjectCode())) {
                            graphContainer.get(numSemesters+i).add(s);
                        }
                    }
                }
            } else {
                throw new Exception("The " + subjListFile + " file does not exsists! The 'graf.txt' file is corrupted!");
            }
            tmpList.remove(0);
        }
        if (!tmpList.isEmpty()) {
            throw new Exception("The 'graf.txt' file is corrupted! Please update your files!");
        }
    }

}
