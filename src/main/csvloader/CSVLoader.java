package csvloader;

import java.io.*;
import java.util.Scanner;
import database.Database;

public class CSVLoader {
    private static final int NUMHEADERS = 10;

    public static boolean parseCSV(String fileName){
        String badLines = "";
        int goodCount = 0;
        int badCount = 0;
        try{
            File sourceFile = new File(System.getProperty("user.dir") + "/src/data/"+fileName+".csv");
            Scanner fileReader = new Scanner(sourceFile);
            fileReader.nextLine(); //skip headers
            Database database = new Database(fileName);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (values.length != NUMHEADERS){
                    badLines = badLines + line + "\n";
                    badCount++;
                } else {
                    if (!database.pushRow(prepareCell(values))){
                        return false;
                    }
                    goodCount++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(fileName + ": FILE NOT FOUND");
            return false;
        }
        writeGoodLines(fileName, goodCount, badCount);
        writeBadLines(fileName, badLines);
        return true;
    }

    public static String prepareCell(String cell){
        return cell.replace("'", "''");
    }

    public static String[] prepareCell(String[] cells){
        String[] result = new String[cells.length];
        for (int i = 0; i < cells.length; i++){
            result[i] = prepareCell(cells[i]);
        }
        return result;
    }

    private static boolean writeBadLines(String fileName, String badLines) {
        try{
            FileWriter badFile = new FileWriter(System.getProperty("user.dir") + "/src/data/"+fileName+"-bad.csv");
            badFile.write(badLines);
            badFile.close();
            return true;
        } catch (IOException e){
            return false;
        }

    }

    private static boolean writeGoodLines(String fileName, int goodCount, int badCount){
        try{
            FileWriter logFile = new FileWriter(System.getProperty("user.dir") + "/src/data/"+fileName+".log");
            logFile.write("Records Received: " + (goodCount+badCount) + "\nSuccessful Records: " + goodCount + "\nFailed Records: " + badCount);
            logFile.close();
            return true;
        } catch (IOException e){
            return false;
        }
    }



}
