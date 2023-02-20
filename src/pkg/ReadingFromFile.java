package pkg;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ReadingFromFile {

    private static int maxLength;
    //private int maxLengthB;
    private static int minLength;
    private static int allowPassPhrase;
    private static int minPhraseLength;
    private static int requireOptionalTests;
    private static int minOptionalTests;
    private static int count;

    public static ArrayList<String> passwords;

    public ReadingFromFile(){

    }

    public ReadingFromFile(int maxLength, int minLength, int allowPassPhrase, int requireOptionalTests){
        ReadingFromFile.maxLength = maxLength;
        ReadingFromFile.minLength = minLength;
        ReadingFromFile.allowPassPhrase = allowPassPhrase;
        ReadingFromFile.requireOptionalTests = requireOptionalTests;
    }

    public ReadingFromFile(int maxLength, int minLength, int allowPassPhrase,int minPhraseLength, int requireOptionalTests, int minOptionalTests){
        ReadingFromFile.maxLength = maxLength;
        ReadingFromFile.minLength = minLength;
        ReadingFromFile.allowPassPhrase = allowPassPhrase;
        ReadingFromFile.minPhraseLength = minPhraseLength;
        ReadingFromFile.requireOptionalTests = requireOptionalTests;
        ReadingFromFile.minOptionalTests = minOptionalTests;
    }


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

    public static void setPasswords(ArrayList<String> passwords) {
        ReadingFromFile.passwords = passwords;
    }

    public static int getCount() {
        return count;
    }


    public static ReadingFromFile PasswordRequirements(ArrayList<Object> arrayList){

        count += 1;

                maxLength = (int) arrayList.get(0);
                minLength = (int) arrayList.get(1);
                if(Objects.equals(arrayList.get(2), 1)){
                    allowPassPhrase = (int) arrayList.get(2);
                    minPhraseLength = (int) arrayList.get(3);
                    if(Objects.equals(arrayList.get(4), 1)){
                        requireOptionalTests = (int) arrayList.get(4);
                        minOptionalTests = (int) arrayList.get(5);
                    }
                }
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

    public static void Passwords(ArrayList<Object> arrayList){
        ArrayList<String> passwords = new ArrayList<>();
        if(Objects.equals(arrayList.get(4), "")){
            for (int i = 5; i < arrayList.size(); i++) {
                passwords.add((String) arrayList.get(i));
            }
        }else{
            for (int i = 7; i < arrayList.size(); i++) {
                passwords.add((String) arrayList.get(i));
            }
        }
        setPasswords(passwords);
    }


    public static ArrayList<Object> ImportData(File file){


        ArrayList<Object> list = new ArrayList<>();


        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            String tempLine;
            int values;
            for (int i = 0; i < file.length(); i++) {
                line = reader.readLine();
                if(Objects.equals(line, "")){
                    list.add(line);
                    while(!Objects.equals(tempLine = reader.readLine(), null)){
                        list.add(tempLine);
                    }
                    break;
                }else {
                    values = Integer.parseInt(line);
                    list.add(values);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.toString());
        } catch (IOException e) {
            System.out.println("Unable to read file: " + file.toString());
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
