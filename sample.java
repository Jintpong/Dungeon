import java.util.Random;
import java.util.Scanner;

// Main game class
public class DungeonOfDoom {
    public static void main(String[] args) {
        Game game = new Game(10, 10, 5); // Example: 10x10 board with 5 gold required to win
        game.start();
    }
}

// Game class managing the board and players
class Game {
    private Board board;
    private HumanPlayer human;
    private BotPlayer bot;
    private boolean gameOver;
    private int goldRequired;

    public Game(int rows, int cols, int goldRequired) {
        this.board = new Board(rows, cols);
        this.human = new HumanPlayer();
        this.bot = new BotPlayer();
        this.goldRequired = goldRequired;
        this.gameOver = false;
        board.placePlayer(human, 'P');
        board.placePlayer(bot, 'B');
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Dungeon of Doom!");
        while (!gameOver) {
            board.printBoard();
            System.out.println("Gold Collected: " + human.getGoldCollected());
            System.out.println("Enter command (MOVE <UP/DOWN/LEFT/RIGHT>, PICKUP, EXIT):");
            String command = scanner.nextLine();
            processCommand(command.trim());
            botMove();
            checkGameState();
        }
        scanner.close();
    }

    private void processCommand(String command) {
        String[] parts = command.split(" ");
        String action = parts[0].toUpperCase();
        switch (action) {
            case "MOVE":
                if (parts.length > 1) {
                    human.move(parts[1].toUpperCase(), board);
                } else {
                    System.out.println("Invalid MOVE command!");
                }
                break;
            case "PICKUP":
                human.pickUpGold(board);
                break;
            case "EXIT":
                if (human.canExit(board, goldRequired)) {
                    System.out.println("You have won the game!");
                    gameOver = true;
                } else {
                    System.out.println("You cannot exit yet!");
                }
                break;
            default:
                System.out.println("Invalid command!");
        }
    }

    private void botMove() {
        bot.move(board);
    }

    private void checkGameState() {
        if (bot.getX() == human.getX() && bot.getY() == human.getY()) {
            System.out.println("The bot caught you! Game Over!");
            gameOver = true;
        }
    }
}

// Board class representing the game board
class Board {
    private char[][] grid;
    private int rows;
    private int cols;
    private HumanPlayer humanPlayer;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new char[rows][cols];
        initializeBoard();
    }

    private void initializeBoard() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '.';
            }
        }
        for (int i = 0; i < rows * cols / 10; i++) {
            grid[random.nextInt(rows)][random.nextInt(cols)] = 'G';
        }
        grid[random.nextInt(rows)][random.nextInt(cols)] = 'E';
        for (int i = 0; i < rows * cols / 5; i++) {
            grid[random.nextInt(rows)][random.nextInt(cols)] = '#';
        }
    }

    public void placePlayer(Player player, char symbol) {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(rows);
            y = random.nextInt(cols);
        } while (grid[x][y] != '.');
        grid[x][y] = symbol;
        player.setPosition(x, y);
        if (symbol == 'P') {
            this.humanPlayer = (HumanPlayer) player;
        }
    }

    public HumanPlayer getHumanPlayer() {
        return humanPlayer;
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] != '#';
    }

    public char getTile(int x, int y) {
        return grid[x][y];
    }

    public void updateTile(int x, int y, char symbol) {
        grid[x][y] = symbol;
    }

    public void printBoard() {
        for (char[] row : grid) {
            for (char tile : row) {
                System.out.print(tile + " ");
            }
            System.out.println();
        }
    }
}

// Abstract Player class
abstract class Player {
    protected int x, y;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void move(Board board);
}

// HumanPlayer class
class HumanPlayer extends Player {
    private int goldCollected = 0;

    @Override
    public void move(Board board) {
        // Human movement is controlled by commands in the Game class
    }

    public void move(String direction, Board board) {
        int newX = x, newY = y;
        switch (direction) {
            case "UP": newX--; break;
            case "DOWN": newX++; break;
            case "LEFT": newY--; break;
            case "RIGHT": newY++; break;
            default:
                System.out.println("Invalid direction!");
                return;
        }
        if (board.isValidMove(newX, newY)) {
            board.updateTile(x, y, '.');
            x = newX;
            y = newY;
            board.updateTile(x, y, 'P');
        } else {
            System.out.println("Cannot move there!");
        }
    }

    public void pickUpGold(Board board) {
        if (board.getTile(x, y) == 'G') {
            goldCollected++;
            board.updateTile(x, y, '.');
            System.out.println("Gold picked up! Total Gold: " + goldCollected);
        } else {
            System.out.println("No gold here!");
        }
    }

    public int getGoldCollected() {
        return goldCollected;
    }

    public boolean canExit(Board board, int goldRequired) {
        return goldCollected >= goldRequired && board.getTile(x, y) == 'E';
    }
}

// BotPlayer class
class BotPlayer extends Player {
    @Override
    public void move(Board board) {
        HumanPlayer human = board.getHumanPlayer();
        int dx = Integer.compare(human.getX(), x);
        int dy = Integer.compare(human.getY(), y);
        int newX = x + dx, newY = y + dy;

        if (board.isValidMove(newX, newY)) {
            board.updateTile(x, y, '.');
            x = newX;
            y = newY;
            board.updateTile(x, y, 'B');
        }
    }
}
