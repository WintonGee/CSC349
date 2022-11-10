import java.util.ArrayList;

public class ValueHelper {

    public static char[][] getBoard(ArrayList<String> list) {
        char[][] array2d = new char [5][5];
        // First row
        array2d[0][0] = list.get(0).charAt(1);
        array2d[0][1] = list.get(0).charAt(2);
        array2d[0][2] = list.get(0).charAt(3);
        array2d[0][3] = list.get(0).charAt(4);
        array2d[0][4] = list.get(0).charAt(5);

        // Second row
        array2d[1][0] = list.get(1).charAt(1);
        array2d[1][1] = list.get(1).charAt(2);
        array2d[1][2] = list.get(1).charAt(3);
        array2d[1][3] = list.get(1).charAt(4);
        array2d[1][4] = list.get(1).charAt(5);

        // Third row
        array2d[2][0] = list.get(2).charAt(1);
        array2d[2][1] = list.get(2).charAt(2);
        array2d[2][2] = list.get(2).charAt(3);
        array2d[2][3] = list.get(2).charAt(4);
        array2d[2][4] = list.get(2).charAt(5);

        // Fourth row
        array2d[3][0] = list.get(3).charAt(1);
        array2d[3][1] = list.get(3).charAt(2);
        array2d[3][2] = list.get(3).charAt(3);
        array2d[3][3] = list.get(3).charAt(4);
        array2d[3][4] = list.get(3).charAt(5);

        // Fifth row
        array2d[4][0] = list.get(4).charAt(1);
        array2d[4][1] = list.get(4).charAt(2);
        array2d[4][2] = list.get(4).charAt(3);
        array2d[4][3] = list.get(4).charAt(4);
        array2d[4][4] = list.get(4).charAt(5);

        return array2d;
    }

    public static int getValue(char[][] board, char c1, char c2) {
        int row = getCharValue(c1), column = getCharValue(c2);
        return board[row][column] - '0';
    }

    // Used to determine the index of the board rows and columns
    private static int getCharValue(char c) {
        if (c == 'A') {
            return 0;
        }
        if (c == 'C') {
            return 1;
        }
        if (c == 'G') {
            return 2;
        }
        if (c == 'T') {
            return 3;
        }
        return 4;
    }

}
