public class Map {
    private final char[][] map_1 = {
        "####################".toCharArray(),
        "#...............E..#".toCharArray(),
        "#...G..............#".toCharArray(),
        "#.........G........#".toCharArray(),
        "#..................#".toCharArray(),
        "#..................#".toCharArray(),
        "#...G..............#".toCharArray(),
        "#.............G....#".toCharArray(),
        "####################".toCharArray()
    };

    private final char[][] map_2 = {
        "####################".toCharArray(),
        "#.........G........#".toCharArray(),
        "#..................#".toCharArray(),
        "#.........G........#".toCharArray(),
        "#.........E........#".toCharArray(),
        "#..................#".toCharArray(),
        "#..................#".toCharArray(),
        "#..G...............#".toCharArray(),
        "####################".toCharArray()
    };

    private final char[][] map_3 = {
        "####################".toCharArray(),
        "#..............GE..#".toCharArray(),
        "#...G..............#".toCharArray(),
        "#..................#".toCharArray(),
        "#..................#".toCharArray(),
        "#...............G..#".toCharArray(),
        "#..................#".toCharArray(),
        "#..................#".toCharArray(),
        "####################".toCharArray()
    };

    // Return the map
    public char[][] get_Map(int mapNumber) {
        if(mapNumber == 1){
            return map_1;
        }
        else if (mapNumber == 2){
            return map_2;
        }
        else if(mapNumber == 3){
            return map_3;
        }
        else{
            System.out.println("Invalid map number, map 1 will be chosen by default");
            return map_1;
        }
    }
}

