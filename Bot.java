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
    //The idea behind the bot finding the position of the player is to use formula for the Eucilidean Distance 
    public String BotCommand(char[][] map, int playerX, int playerY){
        String[] moves = {"MOVE N", "MOVE S", "MOVE W", "MOVE E"};
        // Direction of change e.g. (1,0) = S or (0,1) = E
        int[] change_in_x = {-1, 1, 0, 0};
        int[] change_in_y = {0 ,0, 1, -1};
        //distance taks the maximum value a double can represent
        double min_distance = Double.MAX_VALUE;
        String bestmove = "";

        // Checking for each moves
        for (int i = 0; i < moves.length; i++){
            int newbotX = bot_position_X + change_in_x[i];
            int newbotY = bot_position_Y + change_in_y[i];
        
        //Checking that its not a wall
        if (newbotX >= 0 && newbotX < map.length && newbotY >= 0 && newbotY < map[0].length) {
            // Calculate the distance to the player
            double distance_to_player = Math.sqrt(Math.pow(playerX - newbotX, 2) + Math.pow(playerY - newbotY, 2));

            //Updating the shortest move to the player
            if (distance_to_player < min_distance){
                min_distance = distance_to_player;
                bestmove = moves[i];
                }

            }
        }

    }
}