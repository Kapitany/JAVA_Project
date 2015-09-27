package filesystem_tester;

import java.util.Arrays;

/**
 *
 * @author Tomega
 */
class Subject {
    private final String subjactName;
    private final String subjactCode;
    private final int creditValue;
    private final String[] requirements;
    private final String subjactType;
    private final int creditRequirement;

    public Subject(String subjactName, String subjactCode, int creditValue, String[] requirements, String subjactType, int creditRequirement) {
        this.subjactName = subjactName;
        this.subjactCode = subjactCode;
        this.creditValue = creditValue;
        this.requirements = requirements;
        this.subjactType = subjactType;
        this.creditRequirement = creditRequirement;
    }

    public String getSubjactName() {
        return subjactName;
    }

    public String getSubjactCode() {
        return subjactCode;
    }

    public int getCreditValue() {
        return creditValue;
    }

    public String[] getRequirements() {
        return requirements;
    }

    public String getSubjactType() {
        return subjactType;
    }
    
    public int getCreditRequirement() {
        return creditRequirement;
    }

    @Override
    public String toString() {
        return subjactName + "\n" +
                subjactCode + "\n" +
                creditValue + "\n" +
                Arrays.toString(requirements) + "\n" +
                subjactType + "\n" +
                creditRequirement;
    }
    
    
    
}
