package bigWork;

public class Test {
    public static void main(String[] args) {

        System.out.println("aaaa".matches("(?!\\d*+$)[a-zA-Z]{6,18}"));

        System.out.println("A51".matches("[A-Za-z](?![a-zA-Z]*+$)[\\d]{2,7}"));


    }
}
