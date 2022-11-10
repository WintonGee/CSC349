import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DynamicProgramming {

    public static void main(String[] args) throws IOException {
        if (args.length == 0)
            return;
        // Reading the input and file
        File file = new File(args[0]);

        // Setting the data into a list
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        ArrayList<String> list = new ArrayList<>();
        String line;
        while ((line = fileReader.readLine()) != null) {
            list.add(line.replaceAll(" ", ""));
        }
        String firstString = list.get(0), secondString = list.get(1);
        list.remove(0);
        list.remove(0);
        list.remove(0);

        char[][] board = ValueHelper.getBoard(list);
        calculate(board, firstString, secondString);

    }

    public static int max(int... numbers) {
        return Arrays.stream(numbers)
                .max().orElse(0);
    }

    public static void calculate(char[][] board, String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];
        DataObject[][] stringArr = new DataObject[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                stringArr[i][j] = new DataObject();
                if (i == 0) {
                    if (j > 0) {
                        stringArr[i][j].first = "" + y.charAt(j - 1);
                        stringArr[i][j].second = "-";
                    }

                    dp[i][j] = 0;
                    continue;
                }
                if (j == 0) {
                    stringArr[i][0].first = "-";
                    stringArr[i][0].second = ""  + x.charAt(i - 1);
                    dp[i][j] = 0;
                    continue;
                }

                char nextIChar = x.charAt(i - 1), nextJChar = y.charAt(j - 1);

                int upLeftVal = dp[i - 1][j - 1];
                int leftVal = dp[i - 1][j];
                int upVal = dp[i][j - 1];
                int newVal = ValueHelper.getValue(board, nextIChar, nextJChar);
                int val = upLeftVal + newVal;

                int max = max(val, leftVal, upVal);

                if (upVal == max) { // Align bj w/ -
                    stringArr[i][j].first = stringArr[i][j-1].first + nextJChar;
                    stringArr[i][j].second = stringArr[i][j-1].second + "-";
                    dp[i][j] = upVal;
                } else if (leftVal == max) { // Align ai w/ -
                    stringArr[i][j].first = stringArr[i-1][j].first + "-";
                    stringArr[i][j].second = stringArr[i-1][j].second + nextIChar;
                    dp[i][j] = leftVal;
                } else if (val == max) { // Align ai & bj
                    stringArr[i][j].first = stringArr[i-1][j-1].first + nextJChar;
                    stringArr[i][j].second = stringArr[i-1][j-1].second + nextIChar;
                    dp[i][j] = val;
                }
            }
        }

//        System.out.println("Testing: Values");
//        for (int[] i : dp) {
//            for (int j : i) {
//                System.out.print(j + " ");
//            }
//            System.out.println();
//        }

//        System.out.println("Testing: Data Objects");
//        for (DataObject[] i : stringArr) {
//            for (DataObject j : i) {
//                System.out.print("(" + j.second + "," + j.first + ")");
//            }
//            System.out.println();
//        }

        // Insert the first couple characters from the longest string
        String first = stringArr[x.length()][y.length()].first;
        String second = stringArr[x.length()][y.length()].second;
        int missingFirstChars = y.length() - first.replaceAll("-", "").length();
        int missingSecondChars = x.length() - second.replaceAll("-", "").length();
//        System.out.println("Used first Chars: " + missingFirstChars + " second: " + missingSecondChars);
        String valToInsertFirst = "", valToInsertSecond = "";
//        System.out.println(x + ", " + first.replaceAll("-", ""));
//        System.out.println(y + ", " + second.replaceAll("-", ""));
        for (int i = 0; i < missingFirstChars; i++) {
            valToInsertFirst = valToInsertFirst + y.charAt(i);
            valToInsertSecond = valToInsertSecond + "-";
        }
        for (int i = 0; i < missingSecondChars; i++) {
            valToInsertFirst = valToInsertFirst + "-";;
            valToInsertSecond = valToInsertSecond + x.charAt(i);
        }
        stringArr[x.length()][y.length()].first = valToInsertFirst + stringArr[x.length()][y.length()].first;
        stringArr[x.length()][y.length()].second = valToInsertSecond + stringArr[x.length()][y.length()].second;

//        System.out.println("Strings: " + x + " " + y);
        stringArr[x.length()][y.length()].print();
//        System.out.println("Score: " + dp[x.length()][y.length()]);

        //TODO temp
        String firstString = stringArr[x.length()][y.length()].first;
        String secondString = stringArr[x.length()][y.length()].second;
        int val = 0;
        for (int i = 0; i < firstString.length(); i++) {
            int newVal = ValueHelper.getValue(board, firstString.charAt(i), secondString.charAt(i));
            val += newVal;
        }
        System.out.println("Score: " + val);
    }

}
