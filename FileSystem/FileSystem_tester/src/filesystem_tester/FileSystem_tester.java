package filesystem_tester;

import java.util.ArrayList;

/**
 *
 * @author Tomega
 */
public class FileSystem_tester {

    private final ArrayList <Subject> subjectList = new ArrayList<>();
    
     private void subjectListLoader() {
         
     }
    
    private void start() {
        subjectListLoader();
    }
    
    public static void main(String[] args) {
        FileSystem_tester tester = new FileSystem_tester();
        tester.start();
    }
    
}
