public class Command {
    // Create the amount of gold to win
    private int goldtowin = 3;

    // Constructor
    public Command(int goldtowin) {
        this.goldtowin = goldtowin;
    }

    // Creating a method to process user input
    public void UserInput() {
        System.out.println("Enter a command: ");
        if (System.console() != null) {
            String command = System.console().readLine().toUpperCase();

            // Check if the command is "HELLO"
            if (command.equals("HELLO")) {
                System.out.println("Gold to win: " + goldtowin);
            } else {
                System.out.println("Unknown command");
            }
        } else {
            System.out.println("Console is not available. Please run in a terminal.");
        }
    }
}
