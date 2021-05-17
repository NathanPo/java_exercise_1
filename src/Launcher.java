import java.util.Scanner;

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
            }
            if ("fibo".equals(command)) {
                fibo();
            } else {
                System.out.println("Unknown command");
            }
        } while (!shouldQuit);
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
