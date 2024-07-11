package com.pluralsight.ui;

import com.pluralsight.models.Customer;
import com.pluralsight.ui.enumerations.*;
import java.util.Scanner;

public class UserInterface {
    private Scanner userInput = new Scanner(System.in);

    private void err() {
        System.out.println("Invalid Choice. Try again.");
    }

    public OrderChoice getMainMenu() {
        while(true) {
            System.out.print(
                    "=".repeat(50) + "\n" +
                    " ".repeat(14) + "Welcome to DELI-cious!" + "\n" +
                    "=".repeat(50) + "\n" +
                    """
                    Please make a selection:
                    \t[1] - New Order
                    \t[0] - EXIT
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                OrderChoice orderChoice = switch (choice) {
                    case 1 -> OrderChoice.NewOrder;
                    case 0 -> OrderChoice.Exit;
                    default -> null;
                };
                if (orderChoice != null) return orderChoice;
                else System.out.println("Invalid Choice: Try Again.");
            } catch (Exception _) {
                System.out.println("Invalid Choice: Try again.");
            }
        }
    }

    public OrderChoice getOrderScreen() {
        while(true) {
            System.out.print(
                    "=".repeat(50) + "\n" +
                    " ".repeat(20) + "Order Menu" + "\n" +
                    "=".repeat(50) + "\n" +
                    """
                    Please make a selection:
                    \t[1] - Add Sandwich
                    \t[2] - Add Signature Sandwich
                    \t[3] - Add Drink
                    \t[4] - Add Chips
                    \t[5] - Checkout
                    \t[0] - Cancel Order
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                OrderChoice orderChoice = switch (choice) {
                    case 1 -> OrderChoice.AddSandwich;
                    case 2 -> OrderChoice.AddSignature;
                    case 3 -> OrderChoice.AddDrink;
                    case 4 -> OrderChoice.AddChips;
                    case 5 -> OrderChoice.Checkout;
                    case 0 -> OrderChoice.CancelOrder;
                    default -> null;
                };
                if (orderChoice != null) return orderChoice;
                else System.out.println("Invalid Choice: Try Again.");
            } catch (Exception _) {
                err();
            }
        }
    }
    public SignatureChoice getSignatureScreen() {
        while(true) {
            System.out.println("=".repeat(50));
            System.out.println(" ".repeat(9) + "DELI-cious Signature Sandwiches:");
            System.out.println("=".repeat(50));
            System.out.print("""
                    Please make a selection:
                    \t[1] - BLT
                    \t[2] - Philly Cheese Steak
                    \t[3] - Hot Chicken
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                SignatureChoice sizeChoice = switch (choice) {
                    case 1 -> SignatureChoice.BLT;
                    case 2 -> SignatureChoice.Philly;
                    case 3 -> SignatureChoice.HotChicken;
                    default -> null;
                };
                if (sizeChoice != null) return sizeChoice;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }
    public SandwichChoice getSandwichScreen() {
        while(true) {
            System.out.print(
                    """
                    Please make a BREAD selection:
                    \t[1] - White
                    \t[2] - Wheat
                    \t[3] - Rye
                    \t[4] - Wrap
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                SandwichChoice sandwichChoice = switch (choice) {
                    case 1 -> SandwichChoice.White;
                    case 2 -> SandwichChoice.Wheat;
                    case 3 -> SandwichChoice.Rye;
                    case 4 -> SandwichChoice.Wrap;
                    default -> null;
                };
                if (sandwichChoice != null) return sandwichChoice;
                else System.out.println("Invalid Choice: Try Again.");
            } catch (Exception _) {
                err();
            }
        }
    }
    public SizeChoice getSandwichSize() {
        while(true) {
            System.out.print(
                    "=".repeat(50) + "\n" +
                    " ".repeat(15) + "Build Your Sandwich!" + "\n" +
                    "=".repeat(50) + "\n" +
                    """
                    Please make a SIZE selection:
                    \t[1] - Small
                    \t[2] - Medium
                    \t[3] - Large
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                SizeChoice sizeChoice = switch (choice) {
                    case 1 -> SizeChoice.Small;
                    case 2 -> SizeChoice.Medium;
                    case 3 -> SizeChoice.Large;
                    default -> null;
                };
                if (sizeChoice != null) return sizeChoice;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }
    public boolean getIsToasted() {
        while (true){
            System.out.print(
                    """
                            Would you like your Sandwich Toasted:
                            \t[1] - Yes
                            \t[2] - No
                            """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else System.out.println("Invalid Choice: Try Again.");
            } catch (Exception _) {
                System.out.println("Invalid Choice: Try again.");
            }
        }
    }

    public ToppingChoice getRegularTopping() {
        while(true) {
            System.out.print(
                    "-".repeat(50) + "\n" +
                    " ".repeat(10) + "Build Your Sandwich: Toppings!" + "\n" +
                    "-".repeat(50) + "\n" +
                    """
                    Please make a TOPPING selection:
                    \t[1]  - Lettuce
                    \t[2]  - Peppers
                    \t[3]  - Onions
                    \t[4]  - Tomatoes
                    \t[5]  - Jalapenos
                    \t[6]  - Cucumbers
                    \t[7]  - Pickles
                    \t[8]  - Guacamole
                    \t[9]  - Mushrooms
                    \t[10] - None
                    \t[11] - All
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                ToppingChoice toppingChoice = switch (choice) {
                    case 1 -> ToppingChoice.Lettuce;
                    case 2 -> ToppingChoice.Peppers;
                    case 3 -> ToppingChoice.Onions;
                    case 4 -> ToppingChoice.Tomatoes;
                    case 5 -> ToppingChoice.Jalapenos;
                    case 6 -> ToppingChoice.Cucumbers;
                    case 7 -> ToppingChoice.Pickles;
                    case 8 -> ToppingChoice.Guacamole;
                    case 9 -> ToppingChoice.Mushrooms;
                    case 10 -> ToppingChoice.None;
                    case 11 -> ToppingChoice.All;
                    default -> null;
                };
                if (toppingChoice != null) return toppingChoice;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }
    public MeatChoice getMeat() {
        while(true) {
            System.out.print(
                    "-".repeat(50) + "\n" +
                    " ".repeat(12) + "Build Your Sandwich: Meat!" + "\n" +
                    "-".repeat(50) + "\n" +
                    """
                    Please make a MEAT selection:
                    \t[1] - Steak
                    \t[2] - Ham
                    \t[3] - Salami
                    \t[4] - Roast Beef
                    \t[5] - Chicken
                    \t[6] - Bacon
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                MeatChoice meatChoice = switch (choice) {
                    case 1 -> MeatChoice.Steak;
                    case 2 -> MeatChoice.Ham;
                    case 3 -> MeatChoice.Salami;
                    case 4 -> MeatChoice.RoastBeef;
                    case 5 -> MeatChoice.Chicken;
                    case 6 -> MeatChoice.Bacon;
                    default -> null;
                };
                if (meatChoice != null) return meatChoice;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }
    public CheeseChoice getCheese() {
        while(true) {
            System.out.print(
                    "-".repeat(50) + "\n" +
                    " ".repeat(11) + "Build Your Sandwich: Cheese!" + "\n" +
                    "-".repeat(50) + "\n" +
                    """
                    Please make a CHEESE selection:
                    \t[1] - American
                    \t[2] - Provolone
                    \t[3] - Cheddar
                    \t[4] - Swiss
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                CheeseChoice cheeseChoice = switch (choice) {
                    case 1 -> CheeseChoice.American;
                    case 2 -> CheeseChoice.Provolone;
                    case 3 -> CheeseChoice.Cheddar;
                    case 4 -> CheeseChoice.Swiss;
                    default -> null;
                };
                if (cheeseChoice != null) return cheeseChoice;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }
    public SauceChoice getSauces() {
        while(true) {
            System.out.print(
                    "-".repeat(50) + "\n" +
                    " ".repeat(11) + "Build Your Sandwich: Sauces!" + "\n" +
                    "-".repeat(50) + "\n" +
                    """
                    Please make a SAUCE selection:
                    \t[1] - Mayo
                    \t[2] - Mustard
                    \t[3] - Ketchup
                    \t[4] - Ranch
                    \t[5] - Thousand Islands
                    \t[6] - Vinaigrette
                    \t[7] - None
                    \t[8] - All
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                SauceChoice sauceChoice = switch (choice) {
                    case 1 -> SauceChoice.Mayo;
                    case 2 -> SauceChoice.Mustard;
                    case 3 -> SauceChoice.Ketchup;
                    case 4 -> SauceChoice.Ranch;
                    case 5 -> SauceChoice.ThousandIslands;
                    case 6 -> SauceChoice.Vinaigrette;
                    case 7 -> SauceChoice.None;
                    case 8 -> SauceChoice.All;
                    default -> null;
                };
                if (sauceChoice != null) return sauceChoice;
                else System.out.println("Invalid Choice: Try Again.");
            } catch (Exception _) {
                System.out.println("Invalid Choice: Try again.");
            }
        }
    }
    public SideChoice getSides() {
        while(true) {
            System.out.print(
                    "-".repeat(50) + "\n" +
                    " ".repeat(13) + "Build Your Sandwich: Side!" + "\n" +
                    "-".repeat(50) + "\n" +
                    """
                    Please make a selection of what you want on the side:
                    \t[1] - Au Jus
                    \t[2] - Sauces
                    \t[3] - None
                    \t[4] - All
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                SideChoice sideChoice = switch (choice) {
                    case 1 -> SideChoice.AuJus;
                    case 2 -> SideChoice.Sauces;
                    case 3 -> SideChoice.None;
                    case 4 -> SideChoice.All;
                    default -> null;
                };
                if (sideChoice != null) return sideChoice;
                else System.out.println("Invalid Choice: Try Again.");
            } catch (Exception _) {
                System.out.println("Invalid Choice: Try again.");
            }
        }
    }

    public boolean getIsExtra() {
        while(true) {
            System.out.print(
                    """
                    Would you like extra:
                    \t[1] - Yes
                    \t[2] - No
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else System.out.println("Invalid Choice: Try Again.");
            } catch (Exception _) {
                System.out.println("Invalid Choice: Try again.");
            }
        }
    }
    public boolean addMore() {
        while(true) {
            System.out.print(
                    """
                    Would you like to add more toppings:
                    \t[1] - Yes
                    \t[2] - No
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else System.out.println("Invalid Choice: Try Again.");
            } catch (Exception _) {
                System.out.println("Invalid Choice: Try again.");
            }
        }
    }

    public boolean getDrink() {
        while(true) {
            System.out.print(
                    "-".repeat(50) + "\n" +
                    " ".repeat(15) + "Add Drinks!"+ "\n" +
                    "-".repeat(50) + "\n" +
                    """
                    Would you like a Drink?
                    \t[1] - Yes
                    \t[2] - No
                    """
            );
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }
    public SizeChoice getDrinkSize() {
        while (true) {
            System.out.print(
                    """
                    \nWhat size?
                    \t[1] - Small
                    \t[2] - Medium
                    \t[3] - Large
                    """
            );
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                SizeChoice sizeChoice = switch (choice) {
                    case 1 -> SizeChoice.Small;
                    case 2 -> SizeChoice.Medium;
                    case 3 -> SizeChoice.Large;
                    default -> null;
                };
                if (sizeChoice != null) return sizeChoice;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }
    public DrinkChoice getDrinkFlavor() {
        while (true) {
            System.out.print(
                    """
                    \nWhat Drink?
                    \t[1] - Cola
                    \t[2] - Root Beer
                    \t[3] - Orange
                    \t[4] - Lemon Lime
                    \t[5] - Ginger Ale
                    """
            );
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                DrinkChoice drinkChoice = switch (choice) {
                    case 1 -> DrinkChoice.Cola;
                    case 2 -> DrinkChoice.RootBeer;
                    case 3 -> DrinkChoice.Orange;
                    case 4 -> DrinkChoice.LemonLime;
                    case 5 -> DrinkChoice.GingerAle;
                    default -> null;
                }; if (drinkChoice != null) return drinkChoice;
            } catch (Exception _) {
                err();
            }
        }
    }
    public boolean getChips() {
        while(true) {
            System.out.print(
                    "-".repeat(50) + "\n" +
                    " ".repeat(20) + "Add Chips!" + "\n" +
                    "-".repeat(50) + "\n" +
                    """
                    Would you like Chips:
                    \t[1] - Yes
                    \t[2] - No
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }
    public ChipChoice getChipBrand() {
        while (true) {
            System.out.print(
                    """
                    \nWhat Chips?
                    \t[1] - Lays
                    \t[2] - Doritos
                    \t[3] - SunChips
                    \t[4] - KettleChips
                    """
            );
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                ChipChoice chipChoice = switch (choice) {
                    case 1 -> ChipChoice.Lays;
                    case 2 -> ChipChoice.Doritos;
                    case 3 -> ChipChoice.SunChips;
                    case 4 -> ChipChoice.KettleChips;
                    default -> null;
                };
                if (chipChoice != null) return chipChoice;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }

    public OrderChoice getCheckout(Customer customer) {
        while(true) {
            System.out.print(
                    "=".repeat(50) + "\n" +
                    " ".repeat(21) + "Checkout" + "\n" +
                    "=".repeat(50) + "\n" +
                    customer.toString() + "\n" +
                    """
                    Complete or Cancel Order:
                    \t[1] - Confirm
                    \t[2] - Cancel
                    """);
            try {
                System.out.print("Your Selection: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());
                OrderChoice orderChoice = switch (choice) {
                    case 1 -> OrderChoice.Receipt;
                    case 2 -> OrderChoice.CancelOrder;
                    default -> null;
                };
                if (orderChoice != null) return orderChoice;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }

    public boolean continueChoice() {
        while (true) {
            System.out.println("Continue?");
            System.out.println("\t[1] - Yes");
            System.out.println("\t[2] - No");
            System.out.print("Your Selection: ");
            try {
                int choice = Integer.parseInt(userInput.nextLine().strip());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }
    public boolean continueToCheckout() {
        while (true) {
            System.out.println("Continue to Checkout or Exit program?");
            System.out.println("\t[1] - Checkout");
            System.out.println("\t[0] - Exit Program");
            System.out.print("Your Selection: ");
            try {
                int choice = Integer.parseInt(userInput.nextLine().strip());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else err();
            } catch (Exception _) {
                err();
            }
        }
    }
}
