import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    static final String FILE_Name = "countries.txt";
    public static void main(String[] args) {


        HashMap<String, ArrayList<Country>> countryAbv  = new HashMap();
        String countryContent = readFile("countries.txt");
        String [] lines = countryContent.split("\n");//split the file of countries into individual lines
        ArrayList<Country> cont = new ArrayList();

        for (String line : lines) {//loop for temp variable line
            String[] columns = line.split("\\|");//splitting line into multiple columns,\\ means to split
            String name = columns[1] ;//temporary variables
            String abbreviation = columns[0];
            Country country = new Country(name ,abbreviation);//creating country object
            cont.add(country);//
        }

        for (Country country : cont) {
            String firstLetter = String.valueOf(country.name.charAt(0));
            ArrayList<Country> list = countryAbv.get(firstLetter);//getting array list for first letter
            if( list == null){
                list = new ArrayList();//creating array list and adding country object to it
                list.add(country);
                countryAbv.put(firstLetter, list);

            }else {
                list.add(country);
            }

        }
        System.out.println("");

        System.out.println("Please enter a letter");
        Scanner scanner = new Scanner(System.in);
        String countryLetter = scanner.nextLine();
        String newCountryFile = String.format("%s_countries.txt", countryLetter);

        if(countryAbv.containsKey(countryLetter)) {
            String newLine = "";
            for (Country newCont : countryAbv.get(countryLetter)) {
                newLine = newLine + String.format("%s %s \n", newCont.abbreviation,newCont.name );
            }
            writeFile(newCountryFile, newLine);
        }

    }
    static String readFile(String fileName){
        File f = new File(fileName);
        try{
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String (fileContent);

        } catch (Exception e){
            return null;

        }
    }

    static void writeFile(String fileName, String fileContent){
        File f = new File(fileName);
        try{
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        }catch (Exception e){

        }
    }
}
