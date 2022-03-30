import java.util.ArrayList;
import java.util.Scanner;

public class Lab3 {
    public static void main(String[] args) throws Exception {
        zad1();
        // zad2();
        // zad3();
        // zad4();
        // zad5();
    }

    private static void zad1() {
        NumberInWords number;
        for(int i = -100; i <= 100; i++) {
            number = new NumberInWords(i);
            System.out.printf("%4d: %s\n", i, number);
        }
    }

    private static void zad2() {
        TicTacToe ttt = new TicTacToe();
        ttt.start();
    }

    private static void zad3() {
        var scanner = new Scanner(System.in);
        
        System.out.print("Podaj liczbę wierszy macierzy: ");
        int m = scanner.nextInt();
        
        System.out.print("Podaj liczbę kolumn macierzy: ");
        int n = scanner.nextInt();
        
        double[][] mat = new double[m][n];

        for(int mi = 0; mi < m; mi++)
            for(int ni = 0; ni < n; ni++) {
                System.out.printf("Podaj wartość macierzy dla m = %d, = %d: ", mi, ni);
                mat[mi][ni] = scanner.nextDouble();
            }

        System.out.println();

        Matrix A = new Matrix(mat);
        Matrix B = new Matrix(new double[][] {{1}, {2}});

        Matrix tA = A.transpose();
        Matrix pA = A.product(2);
        Matrix pA2 = A.product(B);

        System.out.println("Oryginalna macierz:");
        System.out.println(A);
        System.out.println();
        
        System.out.println("Transpozycja:");
        System.out.println(tA);
        System.out.println();
        
        System.out.println("Mnożenie przez 2:");
        System.out.println(pA);
        System.out.println();
        
        System.out.println("Mnożenie przez wektor [1, 2]':");
        System.out.println(pA2);
        System.out.println();
    }

    private static void zad4() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question(
            "Pytanie 1. Dla tablicy\nint tablica[][] = new int[2][3];\nJaką długość zwróci instrukcja tablica.length?",
            new String[] {
                "6",
                "2",
                "5",
                "3"
            },
            1
        ));
        questions.add(new Question(
            "Pytanie 2. Jaka jest poprawna konstrukcja pętli for each w Javie?",
            new String[] {
                "for(typ zmienna in kolekcja)",
                "foreach(typ zmienna : kolekcja)",
                "foreach(typ zmienna in kolekcja)",
                "for(typ zmienna : kolekcja)"
            },
            3
        ));
        questions.add(new Question(
            "Pytanie 3. Który kod poprawnie utworzy obiekt?",
            new String[] {
                "Object obj();",
                "Object obj = create Object();",
                "Object obj = new Object();",
                "Object obj = Object();"
            },
            2
        ));

        int score = 0;

        Scanner scanner = new Scanner(System.in);
        for(Question q : questions) {
            q.ask();

            while(true) {
                System.out.print("Odpowiedź: ");
                String answer = scanner.nextLine().toUpperCase();

                if(!answer.matches("[A-Z]"))
                    continue;

                System.out.println();
                
                if(q.answer(answer))
                    score++;

                break;
            }

        }
        System.out.println();
        System.out.println("Wynik: " + score);
    }

    private static void zad5() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Wysokość planszy: ");
        int x = scanner.nextInt();
        System.out.print("Szerokość planszy: ");
        int y = scanner.nextInt();
        System.out.print("Ilość znaków do wygranej: ");
        int n = scanner.nextInt();

        ExtendedTicTacToe ttt = new ExtendedTicTacToe(x, y, n);
        ttt.start();
    }
}
