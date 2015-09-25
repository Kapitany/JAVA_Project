package filesystem_tester;

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

    public Subject(String subjactName, String subjactCode, int creditValue, String[] requirements, String subjactType) {
        this.subjactName = subjactName;
        this.subjactCode = subjactCode;
        this.creditValue = creditValue;
        this.requirements = requirements;
        this.subjactType = subjactType;
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
}
