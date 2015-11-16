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

    public Matrix add(Matrix second) {

        Matrix result = new Matrix();

        if (this.height == second.height && this.width == second.width) {

            result.height = this.height;
            result.width = this.width;

            for (int row = 0; row < result.height; row++) {

                Tree currentRowM1;

                try {
                    currentRowM1 = this.wiersze.get(row);
                } catch (Exception e) {
                    currentRowM1 = new Tree();
                }

                Tree currentRowM2;

                try {
                    currentRowM2 = second.wiersze.get(row);
                } catch (Exception e) {
                    currentRowM2 = new Tree();
                }

                Tree currentRowResult;

                try {
                    currentRowResult = result.wiersze.get(row);
                } catch (Exception e) {
                    currentRowResult = new Tree();
                    result.wiersze.add(row, currentRowResult);
                }

                for (int column = 0; column < result.width; column++) {

                    double value = currentRowM1.get(column) + currentRowM2.get(column);

                    currentRowResult.insert(column, value);

                }
                
            }

            return result;

        } else {
            return null;
        }
    }

    public void readMatrix(String name) throws FileNotFoundException {

        File myFile = new File(name);
        Scanner fileinput = new Scanner(myFile);

        String input;

        if (fileinput.hasNext()) {
            input = fileinput.next();
            this.height = Integer.parseInt(input);
            input = fileinput.next();
            this.width = Integer.parseInt(input);
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

            Tree currentRow;

            try {
                currentRow = wiersze.get(row);
            } catch (Exception e) {
                currentRow = new Tree();
            }

            for (int column = 0; column < this.width; column++) {

                double number = currentRow.get(column);
                System.out.print(number + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) {

        Matrix macierz1 = new Matrix();

        try {
            macierz1.readMatrix("macierz2.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Podany plik nie istnieje.");
        }

        macierz1.printMe();
        
        System.out.println("Dodawanie:");
        Matrix test1 = new Matrix();
        Matrix test2 = new Matrix();

        try {
            test1.readMatrix("m1add.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Podany plik nie istnieje.");
        }
        test1.printMe();

        try {
            test2.readMatrix("m2add.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Podany plik nie istnieje.");
        }
        test2.printMe();

        Matrix additionResult;

        additionResult = test1.add(test2);
        System.out.println("Wynik dodawania:");
        additionResult.printMe();
        
        System.out.println("Mnożenie:");
        Matrix mult1 = new Matrix();
        Matrix mult2 = new Matrix();

        try {
            mult1.readMatrix("m1mult.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Podany plik nie istnieje.");
        }
        mult1.printMe();

        try {
            mult2.readMatrix("m2mult.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Podany plik nie istnieje.");
        }
        mult2.printMe();

    }

}
