package matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tree.Tree;

/**
 *
 * @author Paweł
 */
public class Matrix {

    private ArrayList<Tree> wiersze = new ArrayList<>();
    private int width;
    private int height;

    public Matrix add(Matrix second) throws WrongSizeException {

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
            throw new WrongSizeException();
        }
    }

    public Matrix multiply(Matrix second) throws WrongSizeException {

        Matrix result = new Matrix();

        if (this.height == second.width) {

            result.height = this.height;
            result.width = second.width;

            for (int row = 0; row < result.height; row++) {

            }

            return result;

        } else {
            throw new WrongSizeException();
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

    public class WrongSizeException extends Exception {

        @Override
        public String toString() {
            return "Podane macierze mają niewłaściwy rozmiar dla tej operacji.";
        }
    }

    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Podano za mało argumentów.");
            System.out.println("Podaj dwie nazwy plików zawierających macierze i żądane działanie między nimi.");
            System.out.println("Dozwolone operacje:");
            System.out.println("Dodawanie: +");
            System.out.println("Mnożenie: x");
        } else {

            String m1name = args[0];
            String operation = args[1];
            String m2name = args[2];

            Matrix matrix1 = new Matrix();
            Matrix matrix2 = new Matrix();

            try {
                matrix1.readMatrix(m1name);
            } catch (FileNotFoundException ex) {
                System.out.println("Podany plik nie istnieje: " + m1name);
            }

            try {
                matrix2.readMatrix(m2name);
            } catch (FileNotFoundException ex) {
                System.out.println("Podany plik nie istnieje: " + m2name);
            }

            System.out.println("Wprowadzone dane:\n");

            matrix1.printMe();
            matrix2.printMe();

            Matrix result;

            switch (operation) {
                case "+":
                    System.out.println("Żądana operacja: dodawanie macierzy");

                    try {
                        result = matrix1.add(matrix2);
                        System.out.println("Wynik:\n");
                        result.printMe();
                    } catch (WrongSizeException ex) {
                        System.err.println(ex);
                    }

                    break;
                case "x":
                    System.out.println("Żądana operacja: mnożenie macierzy");

                    try {
                        result = matrix1.multiply(matrix2);
                        System.out.println("Wynik:\n");
                        result.printMe();
                    } catch (WrongSizeException ex) {
                        System.err.println(ex);
                    }

                    break;
                default:
                    System.out.println("Nie rozpoznano operacji!");
                    System.out.println("Dozwolone operacje:");
                    System.out.println("- Dodawanie: +");
                    System.out.println("- Mnożenie:  x");
                    break;
            }

        }

    }

}
