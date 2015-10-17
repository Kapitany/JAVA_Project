package handler;

/**
 *
 * @author tomega
 */

//A kikommentezett sorok egyenként is sok sor kimenettel rendelkeznek. Szemléltető jellegűek, a tesztelést szolgálják/ták.

public class MainClass {
    
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        try {
            LogicalCurriculumHandler handler = new LogicalCurriculumHandler();
            handler.list();
            SubjectListLoader loader = new SubjectListLoader(handler.getSubjPath());
//            loader.listSubjects();
//            DependenceViewer viewer = new DependenceViewer(loader.getSubjectList());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Initialisation aborted!");
        }
    }
    
}
