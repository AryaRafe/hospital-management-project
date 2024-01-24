import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Main {

    public static void main(String[] args) {
        Nurse nurses[] = new Nurse[10];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Nursefile.txt"));
            String line;
            int j = 0;
            while ((line = reader.readLine()) != null) {
                // each line in the file : Nursefile-password-ID
                String[] tokens = line.split("-");
                for (int i = 0; i < nurses.length; i++) 
                        nurses[i] = new Nurse(tokens[j], tokens[j+1]);
            }
        }  catch(IOException e){
             System.out.println(e);}
        System.out.println("Hello\nWellcome to my application!");
        Scanner in = new Scanner(System.in);
        int flag = 0;
        Boolean nurlog = false;
        boolean exit = true;
        while (exit) {
            System.out.println("Menu :\n1.Nurse\n2.Patient reception\nand 0 to exit\n(Please enter the desired menu number)");
            int fSwitch = Integer.parseInt(in.nextLine());
            switch (fSwitch) {
                case 1:
                    while (flag <= 3) {
                        System.out.print("ID : ");
                        String ID = in.nextLine();
                        System.out.print("Passcode : ");
                        String passcode = in.nextLine();
                        nurlog = Nurse.Login(ID, passcode);
                        if (nurlog)  break;
                        flag++;
                        if(flag < 3)    System.out.println("Nursefile or Password is wrong please try again...");
                        if (flag == 3) {
                        System.out.println("You do not have permission to access the application");
                        return;
                        }   
                    }
                if(nurlog){
                    System.out.println("Menu :\n1.Update the Patients file\n2.Change the Passcode\nand 0 to exit\n(Please enter the desired menu number)");
                    int secSwitch = Integer.parseInt(in.nextLine());
                    switch (secSwitch) {
                        case 1:

                            Patient.rewriting();
                            break;
                        case 2:
                        
                            Nurse.changePasscode();
                            break;
                        case 0: exit = false;
                    }
                }
                    break;
            case 2:
                if (flag == 3)  break; 
                System.out.println("1.New Patient\n2.Delete a Patient\n3.Search Patient\n4.Disply All Patient\nand 0 to exit");
                int thswitch = Integer.parseInt(in.nextLine());
                switch (thswitch) {
                    case 1:
                       Patient.addPatient();
                        break;

                    case 2:
                        Patient.deleteP();
                        break;

                    case 3:
                        Patient.search();
                        break;

                    case 4:
                        Patient.displyAll();
                        break;
                    case 0: 
                        exit = false;
                        break;
                }
                break;
            case 0:
                exit = false;
                break;       
            }
        }
    }
}