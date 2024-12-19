// 672115045 Virwait Kongthong ADT Programming Assignment 1

public class studentClass {
    private String SID;
    private String firstName;
    private String lastName;

    public studentClass(String SIDf, String firstNamef, String lastNamef) {
        SID = SIDf;
        firstName = firstNamef;
        lastName = lastNamef;
    }

    public String getSID() {
        return SID;
    }

    public  String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return SID + " " + firstName + " " + lastName;
    }
}
