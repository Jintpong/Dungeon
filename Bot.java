import java.util.Random;

public class Bot {
    private int botX;
    private int botY;

    public Bot(int start_X, int start_Y) {
        this.botX = start_X;
        this.botY = start_Y;
    }

    public int getBotX() {
        return botX;
    }

    public int getBotY() {
        return botY;
    }

    public String getBotCommand(char[][] map, int playerX, int playerY) {
        // Directions and their effects on bot position
        String[] moves = {"Move N", "Move S", "Move W", "Move E"};
        int[] change_in_x = {-1, 1, 0, 0};
        int[] change_in_y = {0, 0, -1, 1};

        // Check if the bot is in the same position as the player
        if (botX == playerX && botY == playerY) {
            return "Bot reached the player!";
        }

        // Try random moves until a valid one is found
        Random random = new Random();
        while (true) {
            int randomIndex = random.nextInt(moves.length);
            int newBotX = botX + change_in_x[randomIndex];
            int newBotY = botY + change_in_y[randomIndex];

            // Check if the move is within bounds and not blocked
            if (newBotX >= 0 && newBotX < map.length &&
                newBotY >= 0 && newBotY < map[0].length &&
                map[newBotX][newBotY] != '#') {

                // Update the bot's position
                map[botX][botY] = '.'; // Clear old position
                botX = newBotX;
                botY = newBotY;
                map[botX][botY] = 'B'; // Mark new position
                return moves[randomIndex];
            }
        }
    }
}
