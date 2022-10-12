import java.util.Objects;
import java.util.Scanner;

public class MineSweeper extends Paramaters {

    public MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;

    }

    public MineSweeper() {
        this.gameField = new String[row][col];

    }

    public void createMinelesGameField() {

        minelessGameField = new String[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                minelessGameField[i][j] = "-";
            }
        }
    }

    // TO SHOW THE CREATED GAME FİELD WITHOUT MINES
    public void showMinelessGameField() {
        System.out.println("=======================================");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(minelessGameField[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void createGameField() {
        createMinelesGameField();
        mineNumber = (row * col) / 4;
        gameField = new String[this.col][this.row];
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                gameField[i][j] = minelessGameField[i][j];
            }
        }

        while (mineNumber >= 1) {
            int x = (int) (Math.random() * row);
            int y = (int) (Math.random() * col);
            if (!Objects.equals(gameField[x][y], "*")) {
                gameField[x][y] = "*";     //matrisin o indisinde mayın yoksa mayın yerleştir dedim
                mineNumber--;
            }
        }


        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                if (!Objects.equals(this.gameField[i][j], "*")) {
                    this.gameField[i][j] = "-";   //mayın olmayan indislere tire işareti ekledim
                }
            }
        }

    }

    //TO SHOW THE GAME FIELD İF THE USER LOOSE THE GAME
    public void showGameField() {
        System.out.println("=======================================");
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                this.gameField[i][j] = gameField[i][j];
                System.out.print(this.gameField[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int calcNearbyMines(int rowCoord, int columnCoord) {
        int count = 0;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j] == "*" && Math.abs(rowCoord - i) <= 1 && Math.abs(columnCoord - j) <= 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void run() {
        System.out.println("*******WELCOME MINESWEEPERGAME******");
        Scanner input = new Scanner(System.in);
        System.out.print("Insert raw number: ");
        row = input.nextInt();
        System.out.print("Insert coloumn number: ");
        col = input.nextInt();
        createMinelesGameField();
        showMinelessGameField();
        createGameField();
        int selectedRow = 0;
        int selectedCol = 0;

        boolean isContunie = true;


        do {
            System.out.print("Select a row: ");
            selectedRow = input.nextInt();
            System.out.print("Select a coloumn: ");
            selectedCol = input.nextInt();
            if (selectedRow > row || selectedCol > col || selectedCol <= 0 || selectedRow <= 0) {
                System.out.println("Selection of coordinates can  not be lower than zero or higher than game field's maximum coordinate numbers");
            }else {
                isContunie = !Objects.equals(this.gameField[selectedRow][selectedCol], "*");
                if (isContunie) {
                    gameField[selectedRow][selectedCol] = String.valueOf(calcNearbyMines(selectedRow, selectedCol));
                }
            }

        } while (isContunie);
        input.close();
        System.out.println("GAME OVER!!!");
        showGameField();


    }
}
