import pkg.ReadingFromFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import static pkg.ReadingFromFile.*;
import static pkg.PasswordTests.*;

//import static pkg.ReadingFromFile.ImportData;


public class Main {
    //file path C:\Users\right\Desktop\Password Packages
    public static void main(String[] args) {

        System.out.println(PasswordRequirements(ImportData(new File("src\\Password Pkg\\Proposed_passwords_1.txt"))));
        TestingResults(PasswordTesting(getPasswords()));

        System.out.println(PasswordRequirements(ImportData(new File("src\\Password Pkg\\Proposed_passwords_2.txt"))));
        TestingResults(PasswordTesting(getPasswords()));

        System.out.println(PasswordRequirements(ImportData(new File("src\\Password Pkg\\Proposed_passwords_3.txt"))));
        TestingResults(PasswordTesting(getPasswords()));



//        System.out.println(getCount() + " " + getMaxLength() + " " + getMinLength() + " " + getAllowPassPhrase() + " " + getRequireOptionalTests());


    }




}
