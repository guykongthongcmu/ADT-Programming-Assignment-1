import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

// 672115045 Virwait Kongthong ADT Programming Assignment 1

public class studentMain {
    public static void main(String[] args) throws IOException{
        userChoice();
    }

    public static Vector<studentClass> fileReading(String fileName) throws IOException{
        File f = new File(fileName);
        Scanner readFile = new Scanner(f);

        for (int i = 0; i < 7; i++) {
            readFile.nextLine();
        }

        Vector<studentClass> studentInfo = new Vector<studentClass>();

        while (readFile.hasNextLine()) {
            String dataLine = readFile.nextLine();
            StringTokenizer studentToken = new StringTokenizer(dataLine.trim(), ",");
            studentToken.nextToken();
            studentInfo.addElement(new studentClass(studentToken.nextToken().trim(), studentToken.nextToken().trim(), studentToken.nextToken().trim()));
        }

        return studentInfo;
    }

    public static void userChoice() throws IOException{
        
        Scanner input = new Scanner(System.in);
        Vector<String> temp = new Vector<String>();
        String userInput = "";
        String choice = "";
        String fileName = "";
        String searchFN = "";

        userInput = input.nextLine();
        StringTokenizer userInputs = new StringTokenizer(userInput.trim(), " ");
        choice = userInputs.nextToken();
        if (choice.equals("-s")) {
            searchFN = userInputs.nextToken();
            fileName = userInputs.nextToken();
        } else {
            fileName = userInputs.nextToken();
        }

        switch (choice.toLowerCase()) {
            case "-n":
                for (int i = 0; i < fileReading(fileName).size(); i++) {
                    temp.add(fileReading(fileName).elementAt(i).getSID());
                }
                bubbleSort(fileReading(fileName), temp);
                break;
            case "-f":
                for (int i = 0; i < fileReading(fileName).size(); i++) {
                    temp.add(fileReading(fileName).elementAt(i).getFirstName());
                }
                bubbleSort(fileReading(fileName), temp);
                break;
            case "-l":
                for (int i = 0; i < fileReading(fileName).size(); i++) {
                    temp.add(fileReading(fileName).elementAt(i).getLastName());
                }
                bubbleSort(fileReading(fileName), temp);
                break;
            case "-s":
                int searchResult = linearSearch(fileReading(fileName), searchFN);
                if (searchResult != -1) {
                    System.out.println("The name " + searchFN + " was found at index " + searchResult);
                } else {
                    System.out.println("The name " + searchFN + " is either spelt incorrectly or not in the database");
                }
                break;
            case "-e":
                System.out.println("Exiting Program!");
                break;
            default:
                System.out.println("Invalid input! Please try again");
                break;
        }
        System.out.println();
    }

    public static void bubbleSort(Vector<studentClass> vector, Vector<String> temp) {

        int boundary = temp.size() - 1;
        boolean sorted = false;

        while (boundary > 0) {
            for (int i = 0; i < boundary; i++) {
                if (temp.elementAt(i).compareTo(temp.elementAt(i + 1)) > 0) {
                    // swapping temp vector
                    String tempValue = temp.elementAt(i);
                    temp.set(i, temp.elementAt(i + 1));
                    temp.set(i + 1, tempValue);

                    // swap real vector
                    studentClass realTempValue = vector.elementAt(i);
                    vector.set(i, vector.elementAt(i + 1));
                    vector.set(i + 1, realTempValue);
                    sorted = true;
                }
            }
            boundary--;
            if (!sorted) {
                break;
            }
        }

        for (studentClass i : vector) {
            System.out.println(i);
        }
    }

    public static int linearSearch(Vector<studentClass> vector, String search) {
        int index = -1;
        for (int i = 0; i < vector.size(); i++) {
            if (search.toLowerCase().trim().equals(vector.elementAt(i).getFirstName().toLowerCase().trim())) {
                index = i;
            }
        }
        return index;
    }
}
