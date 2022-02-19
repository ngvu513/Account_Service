import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split("");
        Integer first = Arrays.stream(strings).limit(3).mapToInt(Integer::parseInt).sum();
        Integer second = Arrays.stream(strings).skip(3).mapToInt(Integer::parseInt).sum();
        System.out.println(first.equals(second) ? "Lucky" : "Regular");
    }
}