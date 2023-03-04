package pkg;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ReadingFromFile {

    //Variables
    private static int maxLength;
    //private int maxLengthB;
    private static int minLength;
    private static int allowPassPhrase;
    private static int minPhraseLength;
    private static int requireOptionalTests;
    private static int minOptionalTests;
    private static int count;
    public static ArrayList<String> passwords;

    //Default Constructor
    public ReadingFromFile() {

    }

    //Constructor
    public ReadingFromFile(int maxLength, int minLength, int allowPassPhrase,int minPhraseLength, int requireOptionalTests, int minOptionalTests){
        ReadingFromFile.maxLength = maxLength;
        ReadingFromFile.minLength = minLength;
        ReadingFromFile.allowPassPhrase = allowPassPhrase;
        ReadingFromFile.minPhraseLength = minPhraseLength;
        ReadingFromFile.requireOptionalTests = requireOptionalTests;
        ReadingFromFile.minOptionalTests = minOptionalTests;
    }

    //Getters
    public static int getMaxLength() {
        return maxLength;
    }

    public static int getMinLength() {
        return minLength;
    }

    public static int getAllowPassPhrase() {
        return allowPassPhrase;
    }

    public static int getRequireOptionalTests() {
        return requireOptionalTests;
    }

    public static int getMinPhraseLength() {
        return minPhraseLength;
    }

    public static int getMinOptionalTests() {
        return minOptionalTests;
    }

    public static ArrayList<String> getPasswords() {
        return passwords;
    }

    public static int getCount() {
        return count;
    }

    //Setter
    public static void setPasswords(ArrayList<String> passwords) {
        ReadingFromFile.passwords = passwords;
    }



    //Extracts testing requirements from a file
    public static ReadingFromFile PasswordRequirements(ArrayList<Object> arrayList){

        //Tracks file number
        count += 1;

        //Max and Min length
        maxLength = (int) arrayList.get(0);
        minLength = (int) arrayList.get(1);

        //Are Passphrases allowed and what is the Min length
        if(Objects.equals(arrayList.get(2), 1)){
            allowPassPhrase = (int) arrayList.get(2);
            minPhraseLength = (int) arrayList.get(3);

            //Checks if optional tests are necessary and if so, how many
            if(Objects.equals(arrayList.get(4), 1)){
                requireOptionalTests = (int) arrayList.get(4);
                minOptionalTests = (int) arrayList.get(5);
            }
        }

        //Same situation as above but passphrases are not allowed
        if(Objects.equals(arrayList.get(2), 0)) {
            allowPassPhrase = (int) arrayList.get(2);
            if(Objects.equals(arrayList.get(3), 1)){
                requireOptionalTests = (int) arrayList.get(3);
                minOptionalTests = (int) arrayList.get(4);
            }else{
                requireOptionalTests = (int) arrayList.get(3);
            }
        }

        return new ReadingFromFile(maxLength, minLength, allowPassPhrase, minPhraseLength, requireOptionalTests, minOptionalTests);
    }

    //Separates Passwords and phrases from requirement inputs
    public static void Passwords(ArrayList<Object> arrayList){
        ArrayList<String> passwords = new ArrayList<>();


        if(Objects.equals(arrayList.get(4), "")) {

            //Case one is when none are required
            for (int i = 5; i < arrayList.size(); i++) {
                passwords.add((String) arrayList.get(i));
            }
        }else if(Objects.equals(arrayList.get(5), "")){

            //Case two is when one is required
            for (int i = 6; i < arrayList.size(); i++) {
                passwords.add((String) arrayList.get(i));
            }
        }else{

            //Case three is where both are required
            for (int i = 7; i < arrayList.size(); i++) {
                passwords.add((String) arrayList.get(i));
            }
        }
        setPasswords(passwords);
    }

    //Brings the data from the file into the program
    public static ArrayList<Object> ImportData(File file){


        ArrayList<Object> list = new ArrayList<>();


        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            String tempLine;
            int values;

            //Loops over each line of the file
            for (int i = 0; i < file.length(); i++) {
                line = reader.readLine();

                //Finds the empty line
                if(Objects.equals(line, "")){

                    //Adds empty line, then adds Strings until the next empty line is found
                    list.add(line);
                    while(!Objects.equals(tempLine = reader.readLine(), null)){
                        list.add(tempLine);
                    }
                    break;
                }else {

                    //adds Integers until empty line is found
                    values = Integer.parseInt(line);
                    list.add(values);
                }
            }

            //Exception Handling
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        } catch (IOException e) {
            System.out.println("Unable to read file: " + file);
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Unable to close file: " + file.toString());
            }
        }
        Passwords(list);
        return list;
    }

    @Override
    public String toString(){
        String temp = "Processing password file #" + getCount() + "\n\n"
                + "Maximum password length: " + getMaxLength() + "\n"
                + "Minimum password length: " + getMinLength() + "\n";
        if(getAllowPassPhrase() == 1){
            temp += "Passphrases are allowed\n";
        }else{
            temp += "Passphrases are NOT allowed\n";
        }
        if(getRequireOptionalTests() == 1){
            temp += "Optional tests are allowed\n\n";
        }else{
            temp += "Optional tests are NOT allowed\n\n";
        }

        return temp;

    }

}
