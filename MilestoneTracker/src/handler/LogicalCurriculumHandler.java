package handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tomega
 */
public class LogicalCurriculumHandler {

    private String currPath;
    private final String curriculumVersion;
    private final String curriculumName;
    private final String pathSubjects;

    private final int numGlobalReqs;                //mindig 3
    private int numCompletedGlobalReqs;

    private final int creditsToReceive;
    private int creditsReceived;

    private final int numberOfCreditTypes;
    private final ArrayList<Map<String, Integer>> creditTypes;
    //numberOfCreditTypes elemszámú lista, ami kulcs-érték párokat tartalmaz -> kredittípus és a mennyiségi követelmény
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Map<String, Integer>> perTypeCounter;

    private final int numExtraReqs;
    private final ArrayList<String> listExtraReqs;
    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "FieldMayBeFinal"})
    private ArrayList<Map<String, Boolean>> isExtraCompleted;

    private final String creditOverflowTo;
    
    private ArrayList<Subject> completedSubjects;

    public LogicalCurriculumHandler(String currPath) throws Exception {
        this.currPath = currPath;
        List<String> tmpList = new ArrayList<>();
        String[] tmpArray;
        File logCurrFile = new File(currPath + "//logikai.txt");
        numCompletedGlobalReqs = 0;
        creditsReceived = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(logCurrFile));
            @SuppressWarnings("UnusedAssignment")
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                tmpList.add(tmp);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("The 'logikai.txt' file should be in the project's root directory!");
        } catch (IOException ex) {
            System.out.println("IOException has occured, please check the input file and rerun the program!");
        }
        
        tmpArray = tmpList.get(0).split(":");
        curriculumVersion = tmpArray[1];
        tmpList.remove(0);
        
        tmpArray = tmpList.get(0).split(":");
        curriculumName = tmpArray[1];
        tmpList.remove(0);

        tmpArray = tmpList.get(0).split(":");
        pathSubjects = currPath + "//" + tmpArray[1];
        tmpList.remove(0);

        tmpArray = tmpList.get(0).split(":");
        numGlobalReqs = Integer.valueOf(tmpArray[1]);
        tmpList.remove(0);

        tmpArray = tmpList.get(0).split(":");
        creditsToReceive = Integer.valueOf(tmpArray[2]);
        tmpList.remove(0);

        tmpArray = tmpList.get(0).split(":");
        numberOfCreditTypes = Integer.valueOf(tmpArray[2]);
        tmpList.remove(0);

        creditTypes = new ArrayList<>();
        perTypeCounter = new ArrayList<>();
        for (int i = 0; i < numberOfCreditTypes; i++) {
            tmpArray = tmpList.get(0).split(":");
            Map<String, Integer> typePlusQuantity = new HashMap<>();
            typePlusQuantity.putIfAbsent(tmpArray[2], Integer.valueOf(tmpArray[3]));
            creditTypes.add(typePlusQuantity);

            typePlusQuantity = new HashMap<>();
            typePlusQuantity.putIfAbsent(tmpArray[2], 0);
            perTypeCounter.add(typePlusQuantity);
            tmpList.remove(0);
        }

        tmpArray = tmpList.get(0).split(":");
        numExtraReqs = Integer.valueOf(tmpArray[2]);
        tmpList.remove(0);

        listExtraReqs = new ArrayList<>();
        isExtraCompleted = new ArrayList<>();
        for (int i = 0; i < numExtraReqs; i++) {
            tmpArray = tmpList.get(0).split(":");
            listExtraReqs.add(tmpArray[2]);

            Map<String, Boolean> extraReq = new HashMap<>();
            extraReq.putIfAbsent(listExtraReqs.get(i), Boolean.FALSE);
            isExtraCompleted.add(extraReq);

            tmpList.remove(0);
        }

        tmpArray = tmpList.get(0).split(":");
        creditOverflowTo = tmpArray[1];
        tmpList.remove(0);

        if (!tmpList.isEmpty()) {
            throw new Exception("Curriculum File is corrupted! Please update your files!");
        }
        System.out.println(curriculumName + " initialised!");
    }

    public void list() {
        System.out.println("******************************************");
        System.out.println("LISTING DATA:");
        System.out.println("******************************************");
        System.out.println("Name: " + curriculumName);
        System.out.println("Version: "+ curriculumVersion);
        System.out.println("Source folder for the subjects: " + pathSubjects + "/");
        System.out.println("Number of global requirements to 100% completion: " + numGlobalReqs);
        System.out.println("Number of credits to receive: " + creditsToReceive);
        System.out.println("Number of credit types: " + numberOfCreditTypes);
        System.out.println("The types and the quantity to collect are: ");
        System.out.println(creditTypes);
        System.out.println("The number of extra completable subjects are: " + numExtraReqs);
        if (numExtraReqs > 0) {
            System.out.println("The codes of these subjects are the following:");
            System.out.println(listExtraReqs);
        }
        System.out.println("Extra credits are reallocated to '" + creditOverflowTo + "' block.");
        System.out.println("******************************************");
    }

    public String getSubjPath() {
        return pathSubjects;
    }

    public void completeGlobalReq() {
        numCompletedGlobalReqs++;
    }

    public void receiveCredits(int creditValue) {
        creditsReceived += creditValue;
    }

    public int getCreditsReceived() {
        return creditsReceived;
    }

    public void addSpecificCredit(String creditType, int creditValue) {
        receiveCredits(creditValue);
        for (int i = 0; i < numberOfCreditTypes; i++) {
            if (perTypeCounter.get(i).containsKey(creditType) && perTypeCounter.get(i).get(creditType) + creditValue <= creditTypes.get(i).get(creditType)) {
                perTypeCounter.get(i).replace(creditType, perTypeCounter.get(i).get(creditType), perTypeCounter.get(i).get(creditType) + creditValue);
            } else if (perTypeCounter.get(i).containsKey(creditType)) {
                int difference = perTypeCounter.get(i).get(creditType) + creditValue - creditTypes.get(i).get(creditType);
                perTypeCounter.get(i).replace(creditType, perTypeCounter.get(i).get(creditType), creditTypes.get(i).get(creditType));
                for (int j = 0; j < numberOfCreditTypes; j++) {
                    if (perTypeCounter.get(j).containsKey(creditOverflowTo)) {
                        perTypeCounter.get(j).replace(creditOverflowTo, perTypeCounter.get(j).get(creditOverflowTo), perTypeCounter.get(j).get(creditOverflowTo) + difference);
                    }
                }
            }
        }
    }

    public void completeExtra(String subjectCode) {
        for (int i = 0; i < numExtraReqs; i++) {
            if (isExtraCompleted.get(i).containsKey(subjectCode)) {
                isExtraCompleted.get(i).replace(subjectCode, Boolean.FALSE, Boolean.TRUE);
            }
        }
    }
    
    public boolean completeSubject(Subject subject){
        if(!completedSubjects.contains(subject)){
            completedSubjects.add(subject);
            return true;
        }
        return false;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public String getCurrPath() {
        return currPath;
    }
    
}
