package machine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);

        while (true) {
            coffeeMachine.printMessage();
            Scanner scanner = new Scanner(System.in);
            String action = scanner.next();
            boolean continueLoop = coffeeMachine.processAction(action);
            if (!continueLoop) {
                break;
            }
        }
    }


}
