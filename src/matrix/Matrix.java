package matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import tree.Tree;

/**
 *
 * @author Paweł
 */
public class Matrix {

    private ArrayList<Tree> wiersze = new ArrayList<>();
    private int width;
    private int height;

    public void readMatrix(String name) throws FileNotFoundException {

        File myFile = new File(name);
        Scanner fileinput = new Scanner(myFile);

        String input;

        if (fileinput.hasNext()) {
            input = fileinput.next();
            this.width = Integer.parseInt(input);
            input = fileinput.next();
            this.height = Integer.parseInt(input);
        }

        while (fileinput.hasNext()) {
            input = fileinput.next();
            int row = Integer.parseInt(input);
            input = fileinput.next();
            int column = Integer.parseInt(input);
            input = fileinput.next();
            double value = Double.parseDouble(input);

            Tree currentRow;

            try {
                currentRow = wiersze.get(row);
            } catch (Exception e) {
                currentRow = new Tree();
                wiersze.add(row, currentRow);
            }

            currentRow.insert(column, value);
        }
    }

    public void printMe() {

        for (int row = 0; row < this.height; row++) {
            
            Tree currentRow = wiersze.get(row);

            for (int column = 0; column < this.width; column++) {

                double number = currentRow.get(column);
                System.out.print(number + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {

        Matrix macierz1 = new Matrix();

        try {
            macierz1.readMatrix("macierz1.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Podany plik nie istnieje.");
        }
        
        macierz1.printMe();

    }

}
