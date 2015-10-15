import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Countries {
    static final String FILE_Name = "countries.txt";
    public static void main(String[] args) {


        HashMap<String, ArrayList<Country>> countryAbv  = new HashMap();
        String countryContent = readFile("countries.txt");
        String [] lines = countryContent.split("\n");
        ArrayList<Country> cont = new ArrayList();

        for (String line : lines) {
            String[] columns = line.split("\\|");
            String name = columns[1] ;
            String abbreviation = columns[0];
            Country country = new Country(name ,abbreviation);
            cont.add(country);
        }

        for (Country country : cont) {
            String firstLetter = String.valueOf(country.name.charAt(0));
            ArrayList<Country> list = countryAbv.get(firstLetter);
            if( list == null){
                list = new ArrayList();
                list.add(country);
                countryAbv.put(firstLetter, list);

            }else {
                list.add(country);
            }

        }
        System.out.println("");

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
