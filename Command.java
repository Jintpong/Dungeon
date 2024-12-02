public class Command {
    // The amount of gold to win
    private int goldtowin = 3;

    public Command(int goldtowin)
    {
        this.goldtowin = goldtowin;
        
    }
    
    //Creating a mehod in order to process user input
    public void UserInput()
    {
        System.out.println("Enter a command: ");
        String command = System.console().readLine().toUpperCase();

        if (command.equals("HELLO")){
            System.out.println("Gold to win: " + goldtowin);
        }
    }
}