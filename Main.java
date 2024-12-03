public class Main {
    public static void main(String[] args) {
        // Create an instance of the Map class
        Map game_map = new Map();

        // Get the map
        char[][] map = game_map.get_Map();


        // Need to change so that player spawn randomly
        /*int playerX = map.length / 2;
        int playerY = map[0].length / 2;
        map[playerX][playerY] = 'P';

        int botX = playerX + 1;
        int botY = playerY - 1;
        map[botX][botY] = 'B';
        */


        Command command = new Command(map);

        for (char[] i : map) {
            System.out.println(new String(i));
        }
        
        command.UserInput();

    }
}


