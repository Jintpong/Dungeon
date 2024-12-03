public class Command {
    private char[][] map;
    // Create the amount of gold to win
    private int goldtowin;
    private int playergold;
    // The position of the player 
    private int playerX ;
    private int playerY;
    //The position of the bot
    private int botX;
    private int botY;
    //Will use this in the while loop to keep track that as long as the game is not over continue asking the player
    private boolean endgame;

    // Constructor
    public Command(char[][] map) {
        this.map = map;
        this.playerX = 3;
        this.playerY = 3;
        this.botX = 4;
        this.botY = 2;
        this.goldtowin = 3;
        this.playergold = 0;
        this.endgame = false;
    }
//Need to create a while loop to keep asking the user until the game is finnished
    // Creating a method to process user input
    public void UserInput() {
    while (!endgame) {
        System.out.println("Enter a command: ");
        
        // Use Scanner if System.console() is unavailable
        String command;
        if (System.console() != null) {
            command = System.console().readLine().toUpperCase();
        } else {
            System.out.println("Console is not available. Please run in a terminal.");
            break; // Exit the game loop
        }

        // Check the command
        if (command.equals("HELLO")) {
            System.out.println("Gold to win: " + goldtowin);
        } else if (command.equals("GOLD")) {
            System.out.println("Gold owned: " + playergold);
        } else if (command.equals("PICKUP")) {
            if (map[playerX][playerY] == 'G') { // Use .equals for string comparison
                playergold += 1;
                map[playerX][playerY] = '.'; // Replace gold with empty space
                System.out.println("Success. Gold owned: " + playergold);
            } else {
                System.out.println("Fail. Gold owned: " + playergold);
            }
        } else if (command.startsWith("MOVE")) {
            String direction = command.substring(5).toUpperCase().trim(); // Extract direction
            int new_x = playerX;
            int new_y = playerY;

            // Update position based on direction
            if (direction.equals("N")) {
                new_x -= 1;
            } else if (direction.equals("S")) {
                new_x += 1;
            } else if (direction.equals("E")) {
                new_y += 1;
            } else if (direction.equals("W")) {
                new_y -= 1;
            } else {
                System.out.println("Invalid Direction");
                continue;
            }

            // Check new position
            if (new_x >= 0 && new_x < map.length && new_y >= 0 && new_y < map[0].length) {
                if (map[new_x][new_y] != '#') { // Ensure it's not a wall
                    playerX = new_x;
                    playerY = new_y;
                    System.out.println("Success");
                } else {
                    System.out.println("Fail. Position is a wall.");
                }
            } else {
                System.out.println("Fail. Position is out of bounds.");
            }
        } else if (command.equals("LOOK")) {
            // Display 5x5 grid around the player
            for (int row = -2; row <= 2; row++) {
                for (int col = -2; col <= 2; col++) {
                    int x = playerX + row;
                    int y = playerY + col;

                    if (x == playerX && y == playerY) {
                        System.out.print("P");
                    } else if (x == botX && y == botY) {
                        System.out.print("B");
                    } else if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
                        System.out.print(map[x][y]);
                    } else {
                        System.out.print("#");
                    }
                }
                System.out.println();
            }
        } else if (command.equals("QUIT")) {
            // Check win condition
            if (map[playerX][playerY] == 'E' && playergold >= goldtowin) {
                System.out.println("WIN");
            } else {
                System.out.println("LOSE");
            }
            endgame = true; // Exit the game loop
        } else {
            System.out.println("Unknown command");
        }
    }
}









    /*
    public void UserInput() {
        while(!endgame){
            System.out.println("Enter a command: ");
            if (System.console() != null) {
                String command = System.console().readLine().toUpperCase();
            }

            // Check if the command is "HELLO"
            else if (command.equals("HELLO")) {
                System.out.println("Gold to win: " + goldtowin);
            } 
            else if (command.equals("GOLD")){
                System.out.println("Gold own: " + playergold);
            }
            else if (commnd.equals("PICKUP")){
                if(map[playerX][playerY].equals("G")){
                    playergold += 1;
                    map[playerX][playerY] = ".";
                    System.out.println("Success. Gold owned: " + playergold);
                }
                else
                {
                    System.out.println("Fail. Gold owned: " + playergold);
                }
            }



                // Identify the new direction accordind to the user's input
            else if (command.equals("MOVE")){
                //Remove the "MOVE " and find the direction by removing the whitespace
                String direction = command.substring(5).toUpperCase().trim();
                int new_x = player_X;
                int new_y = player_Y;
                int new_position;
                if (direction.equals("N")){
                    new_x -= 1;
                }
                else if (direction.equals("S")){
                    new_x += 1;
                }
                else if (direction.equals("E")){
                    new_y += 1;
                }
                else if (direction.equals("W")){
                    new_y -= 1;
                }
                else{
                    System.out.println("Invalid Direction");
                    continue;
                }

                // Need to check that the position is not a '#'
                //Checking that the position is within the map
                if (new_x >= 0 && new_x < map.length && new_y >= 0 && new_y < map[0].length){
                    //Checking if its not a wall
                    if (map[new_x][new_y] != "#"){
                        player_X = new_x;
                        player_Y = new_y;
                        System.out.println("Success");
                    }
                    else{
                        System.out.println("Fail");
                    }
                }
                else{
                    System.out.println("Fail the position is out of bound");
                }


            }

            else if (command.equals("LOOK")){
                int row;
                int column; 
                // Loops checking that its covering the 5x5 area
                for (row = -2; row <= 2; row++){
                    for (column = -2; column <= 2; column++){
                        int x = player_X + row;
                        int y = player_Y + column;
                    }
                    if (x == player_X && y == player_Y){
                        System.out.print("P");
                    }
                    else if(x == bot_X && y == bot_Y){
                        System.out.print("B");
                    }
                    else if (x >= 0 && x < map.length && y >= 0 && y < map[0].length){
                        System.out.print(map[x][y]);
                    }
                    else{
                        System.out.print("#");
                    }
                }
                System.out.println();
            }
            else if(command.equals("QUIT")){
                // check that if the player have enough gold to win the game
                if (map[player_X][player_Y.equals("E") && playergold >= goldtowin]){
                    System.out.println("WIN");
                }
                else{
                    System.out.println("Lose");
                }
                //exit the loop
                endgame = true;
            }
        
            else {
                System.out.println("Unknown command");
            }
         
        else {
            System.out.println("Console is not available. Please run in a terminal.");
            }
        
        }
    }
    */
}
