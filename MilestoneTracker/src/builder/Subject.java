package builder;

import java.util.Arrays;

/**
 *
 * @author Tomega
 */
public class Subject {

    private final String subjectName;
    private final String subjectCode;
    private final int creditValue;
    private final String[] requirements;
    private final String subjectType;
    private final int creditRequirement;

    public Subject(String subjectName, String subjectCode, int creditValue, String[] requirements, String subjectType, int creditRequirement) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.creditValue = creditValue;
        this.requirements = requirements;
        this.subjectType = subjectType;
        this.creditRequirement = creditRequirement;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public int getCreditValue() {
        return creditValue;
    }

    public String[] getRequirements() {
        return requirements;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public int getCreditRequirement() {
        return creditRequirement;
    }

    @Override
    public String toString() {
        return subjectName + "\n"
                + subjectCode + "\n"
                + creditValue + "\n"
                + Arrays.toString(requirements) + "\n"
                + subjectType + "\n"
                + creditRequirement;
    }
}
