public class Main {
    public static void main(String[] args) {
        // Create an instance of the Map class
        Map game_map = new Map();

        // Get the map
        char[][] map = game_map.get_Map();


        // Find the center position to place the player "P"
        int Y = map.length / 2;
        int X = map[0].length / 2;
        map[Y][X] = 'P';

        //Print out the map
        for (char[] i : map) {
            System.out.println(new String(i));
        }

        Command command = new Command(3);
        command.UserInput();


    }
}


