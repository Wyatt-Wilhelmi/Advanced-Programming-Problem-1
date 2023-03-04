package pkg;

import java.util.ArrayList;

public class PasswordTests extends ReadingFromFile{

    //Variables
    private static int[][] finalResults;

    //Default Constructor
    public PasswordTests(){

    }

    //Constructor
    public PasswordTests(int[][] finalResults){
        this.setFinalResults(finalResults);
    }

    //Setter
    public void setFinalResults(int[][] finalResults) {
        PasswordTests.finalResults = finalResults;
    }


    //Logic that pulls in requirements and tests the passwords
    public static PasswordTests PasswordTesting(ArrayList<String> arrayList){
        int j = 0;
        int[][] passwordResults = new int[arrayList.size()][8];

        for (String s :arrayList) {

            //Each cell pertains to a specific task. Some are one by default because they pass until they fail
            int[] results = {0,0,1,0,0,0,0,1,0};

            //Discerns whether it is a passphrase or password
            if(getAllowPassPhrase() == 0 || s.length() <= getMinPhraseLength()) {
                results[7] = 0;

                //Checks length constraints for passwords
                if (s.length() <= getMaxLength()) {
                    results[0] = 1;
                }
                if (s.length() >= getMinLength()) {
                    results[1] = 1;
                }

                //Checks that no consecutive letters repeat three times or more
                for (int i = 1; i < s.length() - 1; i++) {

                    if (s.substring(i - 1, i).equals(s.substring(i, i + 1))) {
                        if (s.substring(i, i + 1).equals(s.substring(i + 1, i + 2))) {
                            results[2] = 0;
                            break;
                        }
                    }
                }

                //Signifies a strong Passphrase
            }else{
                results[8] = 1;
            }
            if(getRequireOptionalTests() == 1){

                //Iterates through each character in the string
                for (int i = 0; i < s.length() - 1; i++) {

                    //Uppercase test
                    if(Character.isUpperCase(s.charAt(i))){
                        results[3] = 1;
                    }

                    //Lowercase test
                    if(Character.isLowerCase(s.charAt(i))){
                        results[4] = 1;
                    }

                    //Contains a number using ASCII values
                    if(s.charAt(i) >= 48 && s.charAt(i) <= 57){
                        results[5] = 1;
                    }

                    //Contains a special character using ASCII values
                    if(s.charAt(i) >= 33 && s.charAt(i) <= 47 || s.charAt(i) >= 58 && s.charAt(i) <= 64 || s.charAt(i) >= 91 && s.charAt(i) <= 96 || s.charAt(i) >= 123 && s.charAt(i) <= 126){
                        results[6] = 1;
                    }
                }
            }

            //Adds each test to a row in Array
            passwordResults[j] = results;
            j++;
        }
        return new PasswordTests(passwordResults);
    }



    @Override
    public String toString(){
        StringBuilder temp = new StringBuilder();

        //Turns PasswordTesting results into information
        for (int i = 0; i < finalResults.length; i++) {
            temp.append("Potential Password: ").append(passwords.get(i)).append("\n\n");

            //This makes sure it is not a Passphrase
            if(finalResults[i][7] == 0 || finalResults[i][8] == 0 ) {

                //Checks if there are any Required Test errors before adding them to the String
                if(finalResults[i][0] == 0 || finalResults[i][1] == 0 || finalResults[i][2] == 0) {
                    temp.append("Required Test Errors:\n");
                    if (finalResults[i][0] == 0) {
                        temp.append("\t-The password must be fewer than ").append(getMaxLength()).append(" characters long\n");
                    }
                    if (finalResults[i][1] == 0) {
                        temp.append("\t-The password must be at least ").append(getMinLength()).append(" characters long\n");
                    }
                    if (finalResults[i][2] == 0) {
                        temp.append("\t-The password may not contain sequence of three or more repeated characters\n");
                    }
                    temp.append("\n\n");
                }
            }

            //Makes sure that optional tests are required and that the current String is not a Passphrase
            if(getRequireOptionalTests() == 1 && finalResults[i][7] == 0) {
                temp.append("Optional Test Errors:\n");
                if (finalResults[i][3] == 0) {
                    temp.append("\tThe password must contain at least one lower case letter\n");
                }
                if (finalResults[i][4] == 0) {
                    temp.append("\tThe password must contain at least one upper case letter\n");
                }
                if (finalResults[i][5] == 0) {
                    temp.append("\tThe password must contain at least one number\n");
                }
                if (finalResults[i][6] == 0) {
                    temp.append("\tThe password must contain at least one special character\n");
                }
                temp.append("\n\n");
            }

            //Is the Passphrase strong?
            if(finalResults[i][7] == 1) {
                temp.append("Passphrase? Yes\n\n");
            }
            if(finalResults[i][7] == 0) {
                temp.append("Passphrase? No\n\n");
            }

            //Checks if current String is a Passphrase
            if(finalResults[i][7] == 1){

                //Is the Passphrase Strong?
                if(finalResults[i][8] == 1){
                    temp.append("Strong? Yes\n\n");
                }else{
                    temp.append("Strong? No\n\n");
                }
            }else{

                //Checks if optional tests are required
                if(getRequireOptionalTests() == 1) {

                    //Total count of passed Optional Tests
                    int total = finalResults[i][3] + finalResults[i][4] + finalResults[i][5] + finalResults[i][6];

                    //Is Password, with optional tests, strong?
                    if (finalResults[i][0] + finalResults[i][1] + finalResults[i][2] + finalResults[i][3] + finalResults[i][4] + finalResults[i][5] + finalResults[i][6] >= getMinOptionalTests() + 3) {
                        temp.append("""
                                Strong? Yes

                                Total Optional Tests Pass:\s""").append(total).append("\n\n\n");
                    }else{
                        temp.append("""
                                Strong? No

                                Total Optional Tests Passed:\s""").append(total).append("\n\n\n");
                    }
                }else{

                    //Is Password, without optional tests, strong?
                    if(finalResults[i][0] + finalResults[i][1] + finalResults[i][2] == 3){
                        temp.append("Strong? Yes\n\n\n");
                    }else{
                        temp.append("Strong? No\n\n\n");
                    }
                }
            }
        }
        return temp.toString();
    }


}
