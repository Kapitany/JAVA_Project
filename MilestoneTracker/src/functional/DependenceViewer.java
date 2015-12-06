package functional;

import builder.Subject;
import java.util.ArrayList;

/**
 *
 * @author Tomega
 */
public class DependenceViewer {

    public DependenceViewer(ArrayList<Subject> subjectList) {
        for (Subject s : subjectList) {
            String[] requirement = s.getRequirements();
            for (int i = 0; i < s.getRequirements().length; i++) {
                for (Subject st : subjectList) {
                    if (requirement[i].equals(st.getSubjectCode()) && i > 0) {
                        System.out.print(", " + st.getSubjectName());
                    } else if (requirement[i].equals(st.getSubjectCode())) {
                        System.out.print("\n" + st.getSubjectName());
                    } else if (requirement[i].equals("(" + st.getSubjectCode() + ")") && i > 0) {
                        System.out.print(", " + st.getSubjectName() + "[parallel]");
                    } else if (requirement[i].equals("(" + st.getSubjectCode() + ")")) {
                        System.out.print("\n" + st.getSubjectName() + "[parallel]");
                    }
                }
            }
            if (s.getCreditRequirement() == 75) {
                System.out.print("\n75 credits");
            }
            System.out.print(" -> " + s.getSubjectName() + "\n");
        }
    }

}
