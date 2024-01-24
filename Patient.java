import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Patient {

    private String fullname;
    private String drugs;
    private String ID;
    private String Doctor;
    private String dateOf;
    public Patient(String fullname, String drugs, String ID, String Doctor, String dateOf) {
        this.ID = ID;
        this.drugs = drugs;
        this.fullname = fullname;
        this.Doctor = Doctor;
        this.dateOf = dateOf;
    }
    public static void addPatient () {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter patient ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient's Doctor: ");
        String doctor = scanner.nextLine();
        System.out.print("Enter patient addmission date: ");
        String date = scanner.nextLine();
        System.out.print("Enter patient drugs: ");
        String drugs = scanner.nextLine();
        String newPatient = id + "-" + name + "-" + doctor + "-" + date + "-" + drugs;
        try {
        FileWriter fileWriter = new FileWriter("Patientfile.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append(newPatient);
        bufferedWriter.newLine();
        bufferedWriter.close();
        fileWriter.close();
        } catch (IOException e) { System.out.println(e); }
    }
    public static void deleteP(){
        try{
        Scanner in = new Scanner(System.in);
        String file = "Patientfile.txt";
        System.out.print("Please Enter The Patient ID : ");
        String idp = in.nextLine();
        int lineNumber = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(new File(file));
        int x = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] a2 = line.split("-");
            lineNumber++;
            lines.add(line);
            if(idp.equals(a2[0]) )  { x = lineNumber - 1;}  
        }
        scanner.close();
        lines.remove(x);
        PrintWriter writer = new PrintWriter(file);
        for (int i = 0; i < lines.size(); i++) {
            writer.println(lines.get(i));
        }
        writer.close();
    
        }catch(IOException e) {System.out.println(e);}
    }
    public static void rewriting() {
            try{
            Scanner in = new Scanner(System.in);
            System.out.print("Please Enter the Patient ID : ");
            String idP = in.nextLine();
            int lineNumber = 0;           
            BufferedReader reader = new BufferedReader(new FileReader("Patientfile.txt"));
            String line ;
            String[] b = new String[5];
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("-");
                lineNumber++;
                if(idP.equals(a[0])) {  
                    for (int i = 0; i < a.length; i++) b[i] = a[i];
                    break;
                    //System.out.println("*");
                }
            }
            System.out.print("Please Enter Patient's Doctor : ");
            String doctor = in.nextLine();
            System.out.print("Please Enter the new drugs : ");
            String drugs = in.nextLine();
            String newLine = b[0]+ "-" + b[1] + "-" + doctor + "-" + b[3] + "-" + drugs;
            List<String> lines = Files.readAllLines(Path.of("Patientfile.txt"), StandardCharsets.UTF_8);
            lines.set(lineNumber - 1, newLine);
            Files.write(Path.of("Patientfile.txt"), lines, StandardCharsets.UTF_8);
            System.out.println("Rewriting is succesfully.");
            }catch(IOException e){System.out.println(e);}
        }
    
    // public static void prar(String[] a) {
    //     //just for checking
    //     for (int i = 0; i < a.length; i++) {
    //         System.out.println(a[i]);
    //     }
    // }
    public static void search(){
        try{
        Scanner in = new Scanner(System.in);
        System.out.print("Please Enter the Patient's ID : ");
        String idP = in.nextLine();
        boolean found = false;
        String line;
        BufferedReader reader = new BufferedReader(new FileReader("Patientfile.txt"));
        while ((line = reader.readLine()) != null) {
            String[] a = line.split("-");
            if(a[0].equals(idP)) {
                System.out.println(line);
                found = true;
                break;
            }     
        }
        if(!found)  System.out.println("Sorry, The patient id not found. ");
        }catch(IOException e){System.out.println(e);}
    }
    public static void displyAll() {
        try{
        BufferedReader reader = new BufferedReader(new FileReader("Patientfile.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        }catch(IOException e){System.out.println(e);}

    }
    public static void main(String[] args) throws IOException {
        //just for checking
        Patient.deleteP();
        
    }
}