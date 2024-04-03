import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    // Интерфейс, определяющий метод для получения символа клетки
    interface Basic {
        char getChar();
    }

    // Класс, представляющий символ X
    class XXX implements Basic {
        public char getChar() {
            return 'X';
        }
    }

    // Класс, представляющий символ O
    class OOO implements Basic {
        public char getChar() {
            return 'O';
        }
    }

    // Класс, представляющий пустую клетку
    class Empty implements Basic {
        public char getChar() {
            return '*';
        }
    }

    // Константы, представляющие символы X, O и пустую клетку
    final Basic SIGN_X = new XXX();
    final Basic SIGN_O = new OOO();
    final Basic SIGN_EMPTY = new Empty();

    // Игровое поле
    Basic[][] table;

    // Объекты для работы с рандомом и вводом с клавиатуры
    Random random;
    Scanner scanner;

    // Точка входа в программу
    public static void main(String[] args) {
        new TicTacToe().game();
    }

    // Конструктор
    TicTacToe() {
        random = new Random();
        scanner = new Scanner(System.in);
        table = new Basic[3][3];
    }

    // Основной метод игры
    void game() {
        initTable(); // Инициализация игрового поля
        while (true) {
            turnHuman(); // Ход игрока
            if (checkWin(SIGN_O)) { // Проверка на победу игрока
                System.out.println("AI LOSE!");
                break;
            }
            if (isTableFull()) { // Проверка на ничью
                System.out.println("Sorry, DRAW!");
                break;
            }
            turnAI(); // Ход компьютера
            printTable(); // Вывод игрового поля
            if (checkWin(SIGN_X)) { // Проверка на победу компьютера
                System.out.println("AI WIN!");
                break;
            }
            if (isTableFull()) { // Проверка на ничью
                System.out.println("Sorry, DRAW!");
                break;
            }
        }
        System.out.println("GAME OVER");
        printTable(); // Вывод окончательного состояния игрового поля
    }

    // Инициализация игрового поля
    void initTable() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                table[row][col] = SIGN_EMPTY;
    }

    // Вывод игрового поля на экран
    void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
                System.out.print(table[row][col].getChar() + " ");
            System.out.println();
        }
    }

    // Ход игрока
    void turnHuman() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1..3):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_O;
    }

    // Проверка допустимости хода
    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3 || y >= 3) {
            System.out.println("Error!");
            return false;
        }
        return table[y][x] == SIGN_EMPTY;
    }

    // Ход компьютера
    void turnAI() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_X;
    }

    // Проверка на победу
    boolean checkWin(Basic dot) {
        for (int i = 0; i < 3; i++)
            if ((table[i][0].equals(dot) && table[i][1].equals(dot) &&
                    table[i][2].equals(dot)) ||
                    (table[0][i].equals(dot) && table[1][i].equals(dot) &&
                            table[2][i].equals(dot)))
                return true;
        return (table[0][0].equals(dot) && table[1][1].equals(dot) &&
                table[2][2].equals(dot)) ||
                (table[2][0].equals(dot) && table[1][1].equals(dot) &&
                        table[0][2].equals(dot));
    }

    // Проверка на ничью
    boolean isTableFull() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (table[row][col] == SIGN_EMPTY)
                    return false;
        return true;
    }
}