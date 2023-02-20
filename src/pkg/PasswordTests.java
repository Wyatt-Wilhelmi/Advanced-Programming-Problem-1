package pkg;

import java.util.ArrayList;
import java.util.Locale;

public class PasswordTests extends ReadingFromFile{

    private static int[][] finalResults;

//    public PasswordTests(){
//        super(getMaxLength(), getMinLength(), getAllowPassPhrase(), getRequireOptionalTests());
//    }
//
//    public PasswordTests(){
//        super(getMaxLength(), getMinLength(), getAllowPassPhrase(), getMinPhraseLength(), getRequireOptionalTests(), getMinOptionalTests());
//    }

    public static int[][] getFinalResults() {
        return finalResults;
    }

    public void setFinalResults(int[][] finalResults) {
        PasswordTests.finalResults = finalResults;
    }

    public static int[][] PasswordTesting(ArrayList<String> arrayList){
        int j = 0;
        int[][] passwordResults = new int[arrayList.size()][8];

        for (String s :arrayList) {
            int[] results = {0,0,1,0,0,0,0,1,0};
            if(getAllowPassPhrase() != 1 || s.length() <= getMinPhraseLength()) {
                results[7] = 0;
                if (s.length() <= getMaxLength()) {
                    results[0] = 1;
                }
                if (s.length() >= getMinLength()) {
                    results[1] = 1;
                }

                for (int i = 1; i < s.length() - 1; i++) {

                    if (s.substring(i - 1, i).equals(s.substring(i, i + 1))) {
                        if (s.substring(i, i + 1).equals(s.substring(i + 1, i + 2))) {
                            results[2] = 0;
                            break;
                        }
                    }
                }
            }else{
                results[8] = 1;
            }
            if(getRequireOptionalTests() == 1){
                int temp = 0;
                for (int i = 0; i < s.length() - 1; i++) {
                    if(Character.isUpperCase(s.charAt(i))){
                        results[3] = 1;
                    }
                    if(Character.isLowerCase(s.charAt(i))){
                        results[4] = 1;
                    }
                    if(s.charAt(i) >= 48 && s.charAt(i) <= 57){
                        results[5] = 1;
                    }
                    if(s.charAt(i) >= 33 && s.charAt(i) <= 47){
                        results[6] = 1;
                    }
                    if(s.charAt(i) >= 58 && s.charAt(i) <= 64){
                        results[6] = 1;
                    }
                    if(s.charAt(i) >= 91 && s.charAt(i) <= 96){
                        results[6] = 1;
                    }
                    if(s.charAt(i) >= 123 && s.charAt(i) <= 126){
                        results[6] = 1;
                    }
                }
            }
            passwordResults[j] = results;
            j++;
        }
        finalResults = passwordResults;
    return passwordResults;
    }

    public static void TestingResults(int[][] finalResults){
        String temp = "";

        for (int i = 0; i < finalResults.length; i++) {
            temp = "Potential Password: " + passwords.get(i) + "\n";

            if(finalResults[i][7] == 0 || finalResults[i][8] == 0 ) {
                if(finalResults[i][0] == 0 || finalResults[i][1] == 0 || finalResults[i][2] == 0) {
                    temp += " Required Test Errors:\n";
                    if (finalResults[i][0] == 0) {
                        temp += "\t-The password must be fewer than " + getMaxLength() + " characters long\n";
                    }
                    if (finalResults[i][1] == 0) {
                        temp += "\t-The password must be at least " + getMinLength() + " characters long\n";
                    }
                    if (finalResults[i][2] == 0) {
                        temp += "\t-The password may not contain sequence of three or more repeated characters\n";
                    }
                    temp += "\n\n";
                }
            }
            if(getRequireOptionalTests() == 1) {
                temp += "Optional Test Errors:\n";
                if (finalResults[i][3] == 0) {
                    temp += "\tThe password must contain at least one lower case letter\n";
                }
                if (finalResults[i][4] == 0) {
                    temp += "\tThe password must contain at least one upper case letter\n";
                }
                if (finalResults[i][5] == 0) {
                    temp += "\tThe password must contain at least one number\n";
                }
                if (finalResults[i][6] == 0) {
                    temp += "\tThe password must contain at least one special character\n";
                }
                temp += "\n\n";
            }
            if(finalResults[i][8] == 1) {
                temp += "Passphrase? Yes";
            }
            if(finalResults[i][8] == 0) {
                temp += "Passphrase? No";
            }
            //Start here
            System.out.println(temp);
        }
    }

    public String toString(){
        String temp = "";
        int pass = 7;
        int fail = 0;

        for (int i = 0; i < finalResults.length; i++) {
            temp = "Potential Password: " + passwords.get(i) + "\n";

            if(finalResults[i][7] == 0 || finalResults[i][8] == 0 ) {
                if(finalResults[i][0] == 0 || finalResults[i][1] == 0 || finalResults[i][2] == 0) {
                    temp += " Required Test Errors:\n";
                    if (finalResults[i][0] == 0) {
                        temp += "\t-The password must be fewer than " + getMaxLength() + "\n";
                    }
                    if (finalResults[i][1] == 0) {
                        temp += "\t-The password must be at least " + getMinLength() + "characters long\n";
                    }
                    if (finalResults[i][2] == 0) {
                        temp += "\t-The password may not contain sequence of three or more repeated characters\n";
                    }
                    temp += "\n\n";
                }
            }
            if(getRequireOptionalTests() == 1) {
                temp += "Optional Test Errors:\n";
                if (finalResults[i][3] == 0) {
                    temp += "\tThe password must contain at least one lower case letter\n";
                }
                if (finalResults[i][4] == 0) {
                    temp += "\tThe password must contain at least one upper case letter\n";
                }
                if (finalResults[i][5] == 0) {
                    temp += "\tThe password must contain at least one number\n";
                }
                if (finalResults[i][6] == 0) {
                    temp += "\tThe password must contain at least one special character\n";
                }
                temp += "\n\n";
            }
        }

        return temp;
    }


}
