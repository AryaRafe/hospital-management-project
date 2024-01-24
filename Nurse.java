import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
public class Nurse {
    private String ID;
    private String Passcode;
    Scanner in = new Scanner(System.in);
    public Nurse(String ID, String Passcode) {
        this.ID = ID;
        this.Passcode = Passcode;
    }
    public static boolean Login(String ID, String Passcode){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Nursefile.txt"));
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                // each line in the file should have the format: Nursefile-password
                String[] tokens = line.split("-");
                if (tokens.length == 2 && tokens[0].equals(ID) && tokens[1].equals(Passcode)) {
                    found = true;
                    break;
                }
            }
            reader.close();
            return found;
        } catch(IOException e){
            System.out.println(e);
            return false;
        }
    }
    public static void changePasscode(){
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Please Enter Your ID : ");
            String id = in.nextLine();
            System.out.print("Please Enter Your Passcode : ");
            String oldPasscode = in.nextLine();
            if(Nurse.Login(id, oldPasscode)){
                System.out.print("Enter a new Passcode : ");
                String newPasscode = in.nextLine();
                int lineNumber = 1;          
                BufferedReader reader = new BufferedReader(new FileReader("Nursefile.txt"));
                String line ;
                while ((line = reader.readLine()) != null) {
                    String[] a = line.split("-");
                    if(id.equals(a[0]))    break;
                    lineNumber++;
                    //System.out.println("*");
                }
                String newLine = id + "-" + newPasscode;
                List<String> lines = Files.readAllLines(Path.of("Nursefile.txt"), StandardCharsets.UTF_8);
                lines.set(lineNumber - 1, newLine); 
                Files.write(Path.of("Nursefile.txt"), lines, StandardCharsets.UTF_8);
                System.out.println("Passcode changed succesfully.");
            }else   System.out.println("Passcode Is Wrong.");
        } catch(IOException e){
            System.out.println(e);
        }
    }
       public static void main(String[] args) {
        // main for test and found error...
        Nurse.changePasscode(); 
    }
}