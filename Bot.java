import java.lang.Math;

public class Bot {
    private int bot_position_X;
    private int bot_position_Y;
    private int botGold;

    public Bot(int X_value, int Y_value){
        this.bot_position_X = X_value;
        this.bot_position_Y = Y_value;
        this.botGold = 0;
    }
    

    public String ProcessBotCommand(char[][] map, int playerX, int playerY, boolean[][] positionGold) {
        String[] moves = {"MOVE N", "MOVE S", "MOVE W", "MOVE E"};
        int[] change_in_x = {-1, 1, 0, 0};
        int[] change_in_y = {0, 0, -1, 1};
        double min_distance = Double.MAX_VALUE;
        String bestMove = "";

        // Determine the best move
        for (int i = 0; i < moves.length; i++) {
            int newbotX = bot_position_X + change_in_x[i];
            int newbotY = bot_position_Y + change_in_y[i];

            if (newbotX >= 0 && newbotX < map.length && newbotY >= 0 && newbotY < map[0].length 
                    && map[newbotX][newbotY] != '#') {
                double distance_to_player = Math.sqrt(
                    Math.pow(playerX - newbotX, 2) + Math.pow(playerY - newbotY, 2));

                if (distance_to_player < min_distance) {
                    min_distance = distance_to_player;
                    bestMove = moves[i];
                }
            }
        }

        // Execute the best move
        if (!bestMove.isEmpty()) {
            int newBotX = bot_position_X;
            int newBotY = bot_position_Y;

            if (bestMove.equals("MOVE N")) {
                newBotX -= 1;
            } else if (bestMove.equals("MOVE S")) {
                newBotX += 1;
            } else if (bestMove.equals("MOVE W")) {
                newBotY -= 1;
            } else if (bestMove.equals("MOVE E")) {
                newBotY += 1;
            }

            if (newBotX >= 0 && newBotX < map.length && newBotY >= 0 && newBotY < map[0].length 
                    && map[newBotX][newBotY] != '#' && map[newBotX][newBotY] != 'P') {
                map[bot_position_X][bot_position_Y] = '.'; // Clear old position
                bot_position_X = newBotX;
                bot_position_Y = newBotY;
                map[bot_position_X][bot_position_Y] = 'B'; // Mark new position
                System.out.println("Bot moved to (" + bot_position_X + ", " + bot_position_Y + ").");
            } else {
                System.out.println("Bot move failed.");
            }
        }

    // Check for gold pickup
    if (positionGold[bot_position_X][bot_position_Y]) {
        botGold += 1;
        positionGold[bot_position_X][bot_position_Y] = false; // Remove gold
        System.out.println("Bot picked up gold. Total Gold: " + botGold);
    }

    return bestMove.isEmpty() ? "MOVE N" : bestMove; // Return the best move or default
}


    public int getGold(){
        return botGold;
    }

    public void setGold(int gold){
        this.botGold = gold;
    }

    /*public void ProcessBotCommand(char[][] map, int playerX, int playerY, boolean[][] positionGold) {
    String[] moves = {"MOVE N", "MOVE S", "MOVE W", "MOVE E"};
    int[] change_in_x = {-1, 1, 0, 0};
    int[] change_in_y = {0, 0, -1, 1};
    double min_distance = Double.MAX_VALUE;
    String bestMove = "";

    // Determine the best move
    for (int i = 0; i < moves.length; i++) {
        int newbotX = bot_position_X + change_in_x[i];
        int newbotY = bot_position_Y + change_in_y[i];

        // Validate the move
        if (newbotX >= 0 && newbotX < map.length && newbotY >= 0 && newbotY < map[0].length 
                && map[newbotX][newbotY] != '#') {
            double distance_to_player = Math.sqrt(
                Math.pow(playerX - newbotX, 2) + Math.pow(playerY - newbotY, 2));

            // Update the best move if closer to the player
            if (distance_to_player < min_distance) {
                min_distance = distance_to_player;
                bestMove = moves[i];
            }
        }
    }

    // Execute the best move
    if (!bestMove.isEmpty()) {
        int newBotX = bot_position_X;
        int newBotY = bot_position_Y;

        if (bestMove.equals("MOVE N")) {
            newBotX -= 1;
        } else if (bestMove.equals("MOVE S")) {
            newBotX += 1;
        } else if (bestMove.equals("MOVE W")) {
            newBotY -= 1;
        } else if (bestMove.equals("MOVE E")) {
            newBotY += 1;
        }

        // Validate and update the move
        if (newBotX >= 0 && newBotX < map.length && newBotY >= 0 && newBotY < map[0].length 
                && map[newBotX][newBotY] != '#' && map[newBotX][newBotY] != 'P') {
            map[bot_position_X][bot_position_Y] = '.'; // Clear old position
            bot_position_X = newBotX;
            bot_position_Y = newBotY;
            map[bot_position_X][bot_position_Y] = 'B'; // Mark new position
            System.out.println("Bot moved to (" + bot_position_X + ", " + bot_position_Y + ").");
        } else {
            System.out.println("Bot failed to move.");
        }
    }

    // Check for gold pickup
    if (positionGold[bot_position_X][bot_position_Y]) {
        botGold += 1;
        positionGold[bot_position_X][bot_position_Y] = false; // Remove gold from map
        System.out.println("Bot picked up gold. Total Gold: " + botGold);
    }
}
*/



}