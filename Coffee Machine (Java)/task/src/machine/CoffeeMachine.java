package machine;
import java.util.Scanner;

public class CoffeeMachine {

    public int water;
    public int milk;
    public int coffeeBeans;
    public int disposableCups;
    public int money;

    private CoffeeMachineState state;

    private static Coffee espresso = new Coffee(250, 0, 16, 1, 4);
    private static Coffee latte = new Coffee(350, 75, 20, 1, 7);
    private static Coffee cappuccino = new Coffee(200, 100, 12, 1, 6);
    public CoffeeMachine(int water, int milk, int coffeeBeans, int disposableCups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.disposableCups = disposableCups;
        this.money = money;
        this.state = CoffeeMachineState.DEFAULT;
    }
    public void displayMenu() {
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " ml of water");
        System.out.println(this.milk + " ml of milk");
        System.out.println(this.coffeeBeans + " g of coffee beans");
        System.out.println(this.disposableCups + " disposable cups");
        System.out.println("$" + this.money+ " of money");
    }

    public void takeMoney() {
        System.out.println("I gave you $" + this.money);
        this.money = 0;
    }
    public void fillCoffeeMachine(int addWater, int addMilk, int addCoffeeBeans, int addDisposableCups) {
        this.water += addWater;
        this.milk += addMilk;
        this.coffeeBeans += addCoffeeBeans;
        this.disposableCups += addDisposableCups;
    }

    public CoffeeValidationResult makeCoffee(Coffee c) {
        CoffeeValidationResult validationResult = this.validateCoffeeRequirements(c);
        if (!validationResult.isMade) {
            return validationResult;
        }
        this.water -= c.water;
        this.milk -= c.milk;
        this.coffeeBeans -= c.coffeeBeans;
        this.money += c.money;
        this.disposableCups -= 1;
        return validationResult;
    }

    public CoffeeValidationResult validateCoffeeRequirements(Coffee c) {
        if (this.water < c.water) {
            return new CoffeeValidationResult(false, "water");
        }
        if (this.milk < c.milk) {
            return new CoffeeValidationResult(false, "milk");
        }
        if (this.coffeeBeans < c.coffeeBeans) {
            return new CoffeeValidationResult(false, "coffee beans");
        }
        if (this.disposableCups < c.disposableCups) {
            return new CoffeeValidationResult(false, "disposable cups");
        }
        return new CoffeeValidationResult(true, "");
    }


    public void printMessage() {
        switch (this.state) {
            case DEFAULT:
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                break;
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
        }
    }

    public boolean processAction(String action) {
        if (this.state == CoffeeMachineState.BUY) {
            this.processBuyOptions(action);
            return true;
        }
        switch (action) {
            case "buy":
                System.out.println("");
                this.state = CoffeeMachineState.BUY;
                return true;
            case "fill":
                System.out.println("");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Write how many ml of water you want to add:");
                int addWater = scanner.nextInt();
                System.out.println("Write how many ml of milk you want to add:");
                int addMilk = scanner.nextInt();
                System.out.println("Write how many grams of coffee beans you want to add:");
                int addCoffeeBeans = scanner.nextInt();
                System.out.println("Write how many disposable cups you want to add:");
                int addDisposableCups = scanner.nextInt();
                this.fillCoffeeMachine(addWater, addMilk, addCoffeeBeans, addDisposableCups);
                this.state = CoffeeMachineState.DEFAULT;
                return true;
            case "take":
                System.out.println("");
                this.takeMoney();
                return true;
            case "remaining":
                System.out.println("");
                this.displayMenu();
                return true;
            case "exit":
                return false;
        }
        return true;
    }

    public void processBuyOptions(String num) {
        CoffeeValidationResult coffeePreparationResult;
        switch (num) {
            case "1":
                coffeePreparationResult = this.makeCoffee(espresso);
                if (coffeePreparationResult.isMade) {
                    System.out.println("I have enough resources, making you a coffee!");
                    break;
                }
                System.out.println("Sorry, not enough " + coffeePreparationResult.missingIngredient + "!");
                break;
            case "2":
                coffeePreparationResult = this.makeCoffee(latte);
                if (coffeePreparationResult.isMade) {
                    System.out.println("I have enough resources, making you a coffee!");
                    break;
                }
                System.out.println("Sorry, not enough " + coffeePreparationResult.missingIngredient + "!");
                break;
            case "3":
                coffeePreparationResult = this.makeCoffee(cappuccino);
                if (coffeePreparationResult.isMade) {
                    System.out.println("I have enough resources, making you a coffee!");
                    break;
                }
                System.out.println("Sorry, not enough " + coffeePreparationResult.missingIngredient + "!");
                break;
        }
        this.state = CoffeeMachineState.DEFAULT;
    }
}


