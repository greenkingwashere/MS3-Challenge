

import database.Database;
import csvloader.CSVLoader;
import java.util.Scanner;


public class MS3ChallengeApplication {
    public static void main(String[] args){
        if (!Database.assertSqliteIsLoaded()){
            System.out.println("ERROR: sqlite-jdbc jar file not known. This can be found at https://github.com/xerial/sqlite-jdbc.\nCommand line example: java -classpath \".;sqlite-jdbc-(VERSION).jar\" Sample");
            System.exit(1);
        }
        printLogo();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of file to parse. \nThis file should be in the 'data' directory.");
        String fileName = scanner.nextLine();
        if (fileName.endsWith(".csv")){
            fileName = fileName.substring(0, fileName.length() - 4);
        }
        CSVLoader.parseCSV(fileName);
    }

    private static void printLogo(){
        System.out.println(" _  _  ____  ____     __   ____  ____ \n" +
                "( \\/ )/ ___)( __ \\   / _\\ (  _ \\(  _ \\\n" +
                "/ \\/ \\\\___ \\ (__ (  /    \\ ) __/ ) __/\n" +
                "\\_)(_/(____/(____/  \\_/\\_/(__)  (__)  ");
    }
}
