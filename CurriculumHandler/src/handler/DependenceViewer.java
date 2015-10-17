package handler;

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
                    if (requirement[i].equals(st.getSubjactCode()) && i > 0) {
                        System.out.print(", " + st.getSubjactName());
                    } else if (requirement[i].equals(st.getSubjactCode())) {
                        System.out.print("\n" + st.getSubjactName());
                    } else if (requirement[i].equals("(" + st.getSubjactCode() + ")") && i > 0) {
                        System.out.print(", " + st.getSubjactName() + "[parallel]");
                    } else if (requirement[i].equals("(" + st.getSubjactCode() + ")")) {
                        System.out.print("\n" + st.getSubjactName() + "[parallel]");
                    }
                }
            }
            if (s.getCreditRequirement() == 75) {
                System.out.print("\n75 credits");
            }
            System.out.print(" -> " + s.getSubjactName() + "\n");
        }
    }

}
