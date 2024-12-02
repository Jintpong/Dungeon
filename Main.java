
public class Main {
    public static void main(String[] args){
        // An instance for the Map class
        Map game_map = new Map();

        char[][] map = game_map.get_Map();
        for (char[] i : map){
            System.out.println(new String(i));
        }
    }
}

