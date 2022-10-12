import java.util.Scanner;

public class Paramaters {
    String [][] gameField;
    String [][] minelessGameField;
    int row, col, mineNumber;

    public void createParameters() {
        Scanner input = new Scanner(System.in);
        System.out.print("Satır sayısını giriniz: ");
        row = input.nextInt();
        System.out.print("Sütun sayısını giriniz: ");
        col = input.nextInt();
        mineNumber = (row * col) / 4;
    }

}

