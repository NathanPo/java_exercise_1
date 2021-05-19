import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("coucou");
        Scanner sc = new Scanner(System.in);

        boolean shouldQuit = false;
        do {
            System.out.println("Commande dispo : fibo, quit");
            String command = sc.nextLine();
            if ("quit".equals(command)) {
                shouldQuit = true;
                break;
            } else if ("fibo".equals(command)) {
                fibo();
            } else if ("freq".equals(command)) {
                System.out.println("Donne moi le chemin du fichier que je dois lire: ");
                String path = sc.nextLine();
                frequence(path);

            } else {
                System.out.println("Unknown command");
            }
        } while (!shouldQuit);
    }

    private static void frequence(String p) {
        try {
            Path path = Paths.get(p);
            String content = Files.readString(path);
            String[] words = content.split(" ");
            Stream<String> wordStream = Arrays.stream(words);
            Map<String, Long> countsByWord = wordStream
                    .filter(s -> !s.isBlank())
                    .map(s -> s.toLowerCase())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            Comparator<Map.Entry<String, Long>> countReversed =
                    Comparator.<Map.Entry<String, Long>, Long>comparing(e -> e.getValue())
                            .reversed();
            String threeMostOccurringWords = countsByWord.entrySet().stream()
                    .sorted(countReversed)
                    .limit(3)
                    .map(e -> e.getKey())
                    .collect(Collectors.joining(" "));
            System.out.println(threeMostOccurringWords);
        } catch (IOException e) {
            System.out.println("Unreadable file: " + e);
        }
    }

    private static void fibo() {
        System.out.println("Entre un nombre : ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(fiboc(n));
    }
    private static int fiboc(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return fiboc(n - 1) + fiboc(n - 2);
        }
    }
}
