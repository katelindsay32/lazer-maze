import java.io.File;
        import java.io.FileNotFoundException;
        import java.util.ArrayList;
        import java.util.Scanner;

public class FileReaderWrapper {

    public ArrayList<String>  ReadFile(String fileName) {

        ArrayList toReturn = new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                toReturn.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot file file specified");
            e.printStackTrace();
        }
        return toReturn;
    }

}
