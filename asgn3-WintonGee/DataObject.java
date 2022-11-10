public class DataObject {

    public String first = "", second = "";

    // This class is used to keep track of the alignments and -
    public DataObject() {

    }

    public void print() {
        System.out.println("x: " + second.replace("", " ").trim());
        System.out.println("y: " + first.replace("", " ").trim());
    }

}
