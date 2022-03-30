import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    
    private final String[] MARKS = { "   ", " o ", " x " };
    private final String VSEPARATOR = "|";
    private final String HSEPARATOR = "---";
    private final String VHSEPARATOR = "+";

    protected int[][] board;
    private boolean nextMark;
    protected int n = 3;

    private Scanner scanner;

    public TicTacToe() {
        board = new int[3][3];
        nextMark = false;
        scanner = new Scanner(System.in);
    }

    public void start() {
        drawBoard();
        do {
            System.out.println("Następny ruch - " + (nextMark ? "krzyżyk" : "kółko"));
            System.out.print("[<x> <y> - współrzędne, RR - od nowa, QQ - zakończ]: ");
            String reply = scanner.nextLine().toUpperCase();
            String[] coords = reply.split(" ");
            
            if(coords[0].equals("QQ"))
                break;

            if(coords[0].equals("RR")) {
                restart();
                continue;
            }

            if(coords.length < 2)
                continue;

            if(!coords[0].matches("[A-Z]") || !coords[1].matches("\\d+"))
                continue;

            int x = Integer.parseInt(coords[1]) - 1;
            int y = (int)(coords[0].charAt(0) - 'A');

            if(board[x][y] != 0)
                continue;

            board[x][y] = (nextMark ? 2 : 1);
            nextMark = !nextMark;

            drawBoard();

            if(checkWinner()) {
                System.out.print("Naciśnij ENTER, aby kontynuować...");
                scanner.nextLine();
                restart();
            }

        } while(true);
    }

    private void restart() {
        for(int row[] : board)
            Arrays.fill(row, 0);
        nextMark = false;
        drawBoard();
    }

    private boolean checkWinner() {
        int x = 0;
        int y = 0;
        
        Integer[] cnt = new Integer[4];

        int winningMatchIdx = -1;
        int winningMatchCoords[][] = new int[2][2];

        for(x = 0; x < board.length; x++) {
            for(y = 0; y < board[x].length; y++) {

                if(board[x][y] == 0)
                    continue;

                if(board[x].length - y < n
                    && board.length - x < n
                    && x + 1 < n)
                    continue;

                Arrays.fill(cnt, 0);

                for(int i = 1; i < n; i++) {
                    if(board[x].length - y >= n) {
                        if(board[x][y + i] == board[x][y])
                            cnt[0]++;
                    }

                    if(board.length - x >= n) {
                        if(board[x + i][y] == board[x][y])
                            cnt[1]++;
                    }

                    if(board[x].length - y >= n && board.length - x >= n) {
                        if(board[x + i][y + i] == board[x][y])
                            cnt[2]++;
                    }

                    if(board[x].length - y >= n && x + 1 >= n) {
                        if(board[x - i][y + i] == board[x][y])
                            cnt[3]++;
                    }
                }

                winningMatchIdx = Arrays.asList(cnt).indexOf(n - 1);
                if(winningMatchIdx != -1) {
                    winningMatchCoords[0][0] = x;
                    winningMatchCoords[0][1] = y;
                    winningMatchCoords[1][0] = x;
                    winningMatchCoords[1][1] = y;

                    switch(winningMatchIdx) {
                        case 0:
                            winningMatchCoords[1][1] += cnt[0];
                            break;
                        case 1:
                            winningMatchCoords[1][0] += cnt[1];
                            break;
                        case 2:
                            winningMatchCoords[1][0] += cnt[2];
                            winningMatchCoords[1][1] += cnt[2];
                            break;
                        case 3:
                            winningMatchCoords[1][0] -= cnt[3];
                            winningMatchCoords[1][1] += cnt[3];
                            break;
                    }

                    break;
                }
            }

            if(winningMatchIdx != -1)
                break;

        }

        if(winningMatchIdx != -1){
            System.out.println();
            System.out.println("Wygrywa " + (board[x][y] == 1 ? "kółko" : "krzyżyk") + "!");
            System.out.println();
            return true;
        }

        return false;
    }

    private void drawBoard() {
        ArrayList<String> rows = new ArrayList<>();

        ArrayList<String> row = new ArrayList<>();
        row.add("   ");
        for(int y = 0; y < board[0].length; y++)
            row.add(" " + (char)(y + 'A') + " ");
        rows.add(String.join(VSEPARATOR, row));

        for(int x = 0; x < board.length; x++) {
            row.clear();
            row.add(" " + (x + 1) + " ");
            for(int y = 0; y < board[x].length; y++)
                row.add(MARKS[board[x][y]]);
            rows.add(String.join(VSEPARATOR, row));            
        }

        List<String> rowSeparatorList = Collections.nCopies(board[0].length + 1, HSEPARATOR);
        String rowSeparator = "\n" + String.join(VHSEPARATOR, rowSeparatorList) + "\n";

        System.out.println();
        System.out.println(String.join(rowSeparator, rows));
        System.out.println();
    }
    
}
