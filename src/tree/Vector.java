package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Paweł
 */
public class Vector extends Tree {

    public void add(int i, double v) {
        super.insert(i, v);
    }

    public void readFile(String name) throws FileNotFoundException {

        File myFile = new File(name);
        Scanner fileinput = new Scanner(myFile);

        while (fileinput.hasNext()) {
            String input1 = fileinput.next();
            int index = Integer.parseInt(input1);

            String input2 = fileinput.next();
            double value = Double.parseDouble(input2);

            add(index, value);
        }
    }
    
    
    public static void main(String[] args) {
        
        System.out.println("\nVector Test:");

        Vector vector = new Vector();

        try {
            vector.readFile("nazwapliku.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Podany plik nie istnieje.");
        }
        vector.printMe();

        double szukam = vector.get(1);
        System.out.println("\nPoszukiwany element o indeksie 1: " + szukam);
        szukam = vector.get(6);
        System.out.println("Poszukiwany element o indeksie 6: " + szukam);
    }

}
