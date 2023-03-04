import java.io.*;
import static pkg.ReadingFromFile.*;
import static pkg.PasswordTests.*;

//import static pkg.ReadingFromFile.ImportData;


public class Main {
    //file path C:\Users\right\Desktop\Password Packages
    public static void main(String[] args) {

        System.out.println(PasswordRequirements(ImportData(new File("src\\Password Pkg\\Proposed_passwords_1.txt"))));
        System.out.println(PasswordTesting(getPasswords()));

        System.out.println(PasswordRequirements(ImportData(new File("src\\Password Pkg\\Proposed_passwords_2.txt"))));
        System.out.println(PasswordTesting(getPasswords()));

        System.out.println(PasswordRequirements(ImportData(new File("src\\Password Pkg\\Proposed_passwords_3.txt"))));
        System.out.println(PasswordTesting(getPasswords()));
    }




}
