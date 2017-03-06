/**
 * Created by Wenjian on 2017/3/6, 0006.
 */

import java.util.Scanner;

/**
 * start from (0, 0)
 * Given a series of coordinate, calculate the final position
 * give up the illegal coordinate
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String commands = sc.nextLine();
        Position finalPosition = new Position();
        calcFinalPosition(commands, new Position(0, 0), finalPosition);
        System.out.println(finalPosition.x + "," + finalPosition.y);

        sc.close();
    }

    /**
     *
     * @param commands
     * @param startPosition
     * @param finalPosition
     * @return
     */
    private static void calcFinalPosition(String commands, Position startPosition, Position finalPosition) {
        String[] commandArray = commands.split(";");

        finalPosition.x = startPosition.x;
        finalPosition.y = startPosition.y;
        for(int i = 0; i < commandArray.length; i++) {
            Command thisCommand = new Command();
            if(commandLegal(commandArray[i], thisCommand)) {
                if((thisCommand.direction == 'A') || (thisCommand.direction == 'a')) {
                    finalPosition.x -= thisCommand.length;
                }
                else if((thisCommand.direction == 'D') || (thisCommand.direction == 'd')) {
                    finalPosition.x += thisCommand.length;
                }
                else if((thisCommand.direction == 'W') || (thisCommand.direction == 'w')) {
                    finalPosition.y += thisCommand.length;
                }
                else if((thisCommand.direction == 'S') || (thisCommand.direction == 's')) {
                    finalPosition.y -= thisCommand.length;
                }
            }
        }
    }

    /**
     *
     * @param commandString
     * @param command
     * @return
     */
    private static boolean commandLegal(String commandString, Command command) {

        commandString = commandString.trim();
        if(commandString.length() <= 0) return false;
        command.direction = commandString.charAt(0);
        try{
            command.length = Integer.valueOf(commandString.substring(1));
        }
        catch (Exception ex) {
            return false;
        }

        if((command.length < 0) || (command.length > 99)) {
            return false;
        }
        return true;
    }

    /**
     *
     */
    static class Position {
        int x;
        int y;
        Position(){}
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 
     */
    static class Command {
        char direction;
        int length;
    }
}
