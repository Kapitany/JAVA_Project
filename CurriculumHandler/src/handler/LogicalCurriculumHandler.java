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

    private final File logCurrFile;
    private final String name;
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
    private int creditOverflow;
    
    private final int numExtraReqs;
    private final ArrayList<String> listExtraReqs;
    private ArrayList<Map<String, Boolean>> isExtraCompleted;

    LogicalCurriculumHandler() throws Exception {
        List<String> tmpList = new ArrayList<>();
        String[] tmpArray;
        logCurrFile = new File("MI_logikai.txt");
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
            System.out.println("The 'MI_logikai.txt' file should be in the project's root directory!");
        } catch (IOException ex) {
            System.out.println("IOException has occured, please check the input file and rerun the program!");
        }
        tmpArray = tmpList.get(0).split(":");
        name = tmpArray[1];
        tmpList.remove(0);

        tmpArray = tmpList.get(0).split(":");
        pathSubjects = tmpArray[1];
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
        for (int i = 0; i < numExtraReqs; i++) {
            tmpArray = tmpList.get(0).split(":");
            listExtraReqs.add(tmpArray[2]);
            
            Map<String, Boolean> extraReq = new HashMap<>();
            extraReq.putIfAbsent(listExtraReqs.get(i), Boolean.FALSE);
            isExtraCompleted.add(extraReq);
            
            tmpList.remove(0);
        }
        
        creditOverflow = 0;

        if (!tmpList.isEmpty()) {
            throw new Exception("Curriculum File is corrupted! Please update your files!");
        }
        System.out.println(name + " initialised!");
    }

    public void list() {
        System.out.println("******************************************");
        System.out.println("LISTING DATA:");
        System.out.println("******************************************");
        System.out.println("Name: " + name);
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
    
}
