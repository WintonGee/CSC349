import java.io.*;

public class DivideAndConquer {

    // This program assumes that there must be a singleton element.
    // It is also assumed that the text files are consistent with the formatting used in in1.txt, in2.txt, in3.txt.
    public static void main(String[] args) throws IOException {
        if (args.length == 0)
            return;
        // Reading the input and file
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String fileName = reader.readLine();
        File file = new File(args[0]);

        // Setting the data into an array
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String[] data = fileReader.readLine().split(", ");
        int[] arr = new int[data.length];
        for (int i = 0; i < data.length; i++)
            arr[i] = Integer.parseInt(data[i]);

        // Do nothing if the file is empty
        if (arr.length == 0)
            return;

        // Algo / Divide and Conquer to search for Singleton element
        int singletonElement = getSingletonElement(arr, 0, arr.length - 1);
        System.out.println(singletonElement);
    }

    // The main goal is to check whether the number on the left and right of an element are equal
    private static int getSingletonElement(int[] arr, int left, int right) {
        if (left == right)
            return arr[left];

        // This is similar to (right + left) / 2, except this prevents overflow errors.
        int mid = left + (right - left) / 2;

        // Case: Mid pointer is an even number
        if (mid % 2 == 0) {
            // Shift 2 positions because mid and mid + 1 is checked.
            if (arr[mid] == arr[mid + 1])
                return getSingletonElement(arr, mid + 2, right);

            // Elements at position mid and mid + 1 are not equal
            return getSingletonElement(arr, left, mid);
        }

        // Case: Mid pointer is an odd number
        if (arr[mid] == arr[mid - 1])
            return getSingletonElement(arr, mid + 1, right);

        // Elements at position mid and mid - 1 are not equal
        return getSingletonElement(arr, left, mid - 1);
    }

}
