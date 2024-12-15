import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an instance of the Map class
        Scanner scanner = new Scanner(System.in);
        Map gameMap = new Map();

        // Ask the user to select the map
        System.out.println("Please select a map to play: ");
        System.out.println("Choose 1 for Map 1 ");
        System.out.println("Choose 2 for Map 2 ");
        System.out.println("Choose 3 for Map 3 ");
        System.out.println("Enter your choic:  ");

        // check the user input 
        int choice = scanner.nextInt();

        //Get the map base on the chosen choice
        char[][] chosenMap = gameMap.get_Map(choice);
        
        
        //display map
        for (char[] row : chosenMap){
            System.out.println(new String(row));
        }

        Command command = new Command(chosenMap);
        command.UserInput();

    }
}


