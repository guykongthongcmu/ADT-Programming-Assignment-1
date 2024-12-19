import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

// 672115045 Virwait Kongthong ADT Programming Assignment 1

public class studentMain {
    public static void main(String[] args) throws IOException{
        File f = new File("/Users/guykongthong/Desktop/cmu work/year1term2/adt/programming assignment/assignment 1/class_roaster67.csv");
        Scanner readFile = new Scanner(f);

        for (int i = 0; i < 7; i++) {
            readFile.nextLine();
        }

        Vector<studentClass> studentInfo = new Vector<studentClass>();

        while (readFile.hasNextLine()) {
            String dataLine = readFile.nextLine();
            StringTokenizer studentToken = new StringTokenizer(dataLine.trim(), ",");
            studentToken.nextToken();
            studentInfo.addElement(new studentClass(studentToken.nextToken(), studentToken.nextToken(), studentToken.nextToken()));
        }

        userChoice(studentInfo);
    }

    public static void userChoice(Vector<studentClass> vector) {
        Scanner input = new Scanner(System.in);
        String choice = "";
        do {
            System.out.println("What would you like to do with list of student's names? \n 1. Input -n to sort Students by their ID \n 2. Input -f to sort Students by their first name \n 3. Input -l to sort Students by their last name \n 4. Input -s to Search a Student's Information by their first name \n 5. -e to Exit the Program");
            choice = input.nextLine();
    
            switch (choice.toLowerCase()) {
                case "-n":
                    bubbleSort(vector, 1);
                    break;
                case "-f":
                    bubbleSort(vector, 2);
                    break;
                case "-l":
                    bubbleSort(vector, 3);
                    break;
                case "-s":
                    System.out.println("Please input the first name you want to search");
                    String searchFN = input.nextLine();
                    int searchResult = linearSearch(vector, searchFN);
                    if (searchResult != -1) {
                        System.out.println("The name " + searchFN + " was found at index " + searchResult);
                    } else {
                        System.out.println("The name " + searchFN + " is either spelt incorrectly or not in the database");
                    }
                    break;
                case "-e":
                    System.out.println("Exiting Program!");
                default:
                    throw new AssertionError();
            }
            System.out.println();
        } while (!choice.toLowerCase().equals("-e"));
    }

    public static void bubbleSort(Vector<studentClass> vector, int type) {
        Vector<String> temp = new Vector<String>();

        switch (type) {
            case 1:
                for (int i = 0; i < vector.size(); i++) {
                    temp.add(vector.elementAt(i).getSID());
                }
                break;
            case 2:
                for (int i = 0; i < vector.size(); i++) {
                    temp.add(vector.elementAt(i).getFirstName());
                }
                break;
            case 3:
                for (int i = 0; i < vector.size(); i++) {
                    temp.add(vector.elementAt(i).getLastName());
                }
                break;
            default:
                throw new AssertionError();
        }

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
            if (search == vector.elementAt(i).getFirstName()) {
                index = i;
            }
        }
        return index;
    }
}
