package com.pluralsight.application;

import com.pluralsight.models.*;
import com.pluralsight.models.addedExtras.*;
import com.pluralsight.models.sandwiches.BLT;
import com.pluralsight.models.sandwiches.HotChicken;
import com.pluralsight.models.sandwiches.Philly;
import com.pluralsight.models.sandwiches.Sandwich;
import com.pluralsight.models.toppings.*;
import com.pluralsight.services.Receiptify;
import com.pluralsight.ui.enumerations.CheeseChoice;
import com.pluralsight.ui.UserInterface;
import com.pluralsight.ui.enumerations.*;

import java.util.HashSet;

public class DeliApp {
    private Customer customer = new Customer();
    private UserInterface ui = new UserInterface();

    private void err() {
        System.out.println("Invalid input please enter correct response. ");
    }

    public void run() {
        OrderChoice choice;
        while (true) {
            choice = ui.getMainMenu();
            switch (choice) {
                case NewOrder -> handleNewOrder();
                case Exit -> System.exit(0);
            };
        }
    }

    private void handleNewOrder() {
        while (true){
            OrderChoice choice = ui.getOrderScreen();
            switch (choice) {
                case AddSandwich -> customer.addToOrder(handleNewSandwich());
                case AddSignature -> customer.addToOrder(handleSignatureSandwich());
                case AddDrink -> customer.addToOrder(handleNewDrink());
                case AddChips -> customer.addToOrder(handleNewChips());
                case Checkout -> {
                    handleCheckout();
                    return;
                }
                case CancelOrder -> {
                    handleCancelOrder();
                    return;
                }
            }
            if (!ui.continueChoice()) {
                handleContintueToCheckout();
            }
        }

    }

    private Sandwich handleNewSandwich() {
        int size = handleSize(ui.getSandwichSize());
        String bread = handleBread();
        boolean isToasted = handleIsToasted();
        PremiumTopping meat = handleMeatChoice();
        PremiumTopping cheese = handleCheeseChoice();
        HashSet<Topping> regularToppings = handleRegularToppings();
        HashSet<Topping> sauces = handleSauces();
        HashSet<Topping> sides = handleSides();

        HashSet<Topping> allToppings = new HashSet<>();
        allToppings.add(meat);
        allToppings.add(cheese);
        allToppings.addAll(regularToppings);
        allToppings.addAll(sauces);
        allToppings.addAll(sides);

        return new Sandwich(size, bread, isToasted, allToppings);
    }
    private Sandwich handleSignatureSandwich() {
        SignatureChoice choice = ui.getSignatureScreen();
        return switch (choice) {
            case BLT -> new BLT();
            case Philly -> new Philly();
            case HotChicken -> new HotChicken();
        };
    }
    private int handleSize(SizeChoice choice) {
        return switch (choice) {
            case Small -> 1;
            case Medium -> 2;
            case Large -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }

    private String handleBread () {
        SandwichChoice sandwichChoice = ui.getSandwichScreen();
        return switch (sandwichChoice) {
            case White -> "White";
            case Wheat -> "Wheat";
            case Rye -> "Rye";
            case Wrap -> "Wrap";
        };
    }
    private boolean handleIsToasted() {
        return ui.getIsToasted();
    }
    private PremiumTopping handleCheeseChoice() {
        CheeseChoice choice = ui.getCheese();
        String cheese = switch (choice) {
            case CheeseChoice.American -> "American";
            case CheeseChoice.Provolone -> "Provolone";
            case CheeseChoice.Cheddar -> "Cheddar";
            case CheeseChoice.Swiss -> "Swiss";
        };
        // getIsExtra
        boolean isExtraCheese = ui.getIsExtra();

        return new PremiumTopping("Cheese", cheese, isExtraCheese);
    }
    private PremiumTopping handleMeatChoice() {
        MeatChoice choice = ui.getMeat();
        String meat = switch (choice) {
            case Steak -> "Steak";
            case Ham -> "Ham";
            case Salami -> "Salami";
            case RoastBeef -> "Roast Beef";
            case Chicken -> "Chicken";
            case Bacon -> "Bacon";
        };
        // getIsExtra
        boolean isExtraMeat = ui.getIsExtra();
        return new PremiumTopping("Meat", meat, isExtraMeat);
    }

    private HashSet<Topping> handleRegularToppings() {
        HashSet<Topping> toppingSet = new HashSet<>();
        ToppingChoice choice;
        String regTopping = "";
        boolean addmore = false;
        do {
            choice = ui.getRegularTopping();
            switch (choice) {
                case None :
                    toppingSet.clear();
                    break;
                case All :
                    toppingSet.clear();
                    toppingSet.addAll(handleAllToppings());
                    break;
                default :
                    regTopping = switch (choice) {
                        case Lettuce -> "Lettuce";
                        case Peppers -> "Peppers";
                        case Onions -> "Onions";
                        case Tomatoes -> "Tomatoes";
                        case Jalapenos -> "Jalapenos";
                        case Cucumbers -> "Cucumbers";
                        case Pickles -> "Pickles";
                        case Guacamole -> "Guacamole";
                        case Mushrooms -> "Mushrooms";
                        default -> "";
                    };
                    toppingSet.add(new RegularTopping(regTopping));
                    addmore = ui.addMore();
            }
            if (choice != ToppingChoice.None) toppingSet.add(new RegularTopping(regTopping));
        } while (addmore);
        return toppingSet;
    }
    private HashSet<RegularTopping> handleAllToppings() {
        HashSet<RegularTopping> regularToppings = new HashSet<>();
        regularToppings.add(new RegularTopping("Lettuce"));
        regularToppings.add(new RegularTopping("Peppers"));
        regularToppings.add(new RegularTopping("Onions"));
        regularToppings.add(new RegularTopping("Tomatoes"));
        regularToppings.add(new RegularTopping("Jalapenos"));
        regularToppings.add(new RegularTopping("Cucumbers"));
        regularToppings.add(new RegularTopping("Pickles"));
        regularToppings.add(new RegularTopping("Guacamole"));
        regularToppings.add(new RegularTopping("Mushrooms"));
        return regularToppings;
    }

    private HashSet<Topping> handleSauces() {
        HashSet<Topping> sauces = new HashSet<>();
        SauceChoice choice;
        String sauce = "";
        boolean addmore = false;
        do {
            choice = ui.getSauces();
            switch (choice) {
                case None :
                    sauces.clear();
                    break;
                case All :
                    sauces.clear();
                    sauces.addAll(handleAllSauces());
                    break;
                default:
                    sauce = switch (choice) {
                        case SauceChoice.Mayo ->  "Mayo";
                        case SauceChoice.Mustard -> "Mustard";
                        case SauceChoice.Ketchup -> "Ketchup";
                        case SauceChoice.Ranch -> "Ranch";
                        case SauceChoice.ThousandIslands -> "ThousandIslands";
                        case SauceChoice.Vinaigrette -> "Vinaigrette";
                        default -> "";
                    };
                    sauces.add(new Sauces(sauceId, sauce));
                    addmore = ui.addMore();
            }
        } while (addmore);
        return sauces;
    }
    private HashSet<Sauces> handleAllSauces() {
        HashSet<Sauces> sauces = new HashSet<>();
        sauces.add(new Sauces(sauceId, "Mayo"));
        sauces.add(new Sauces(sauceId, "Mustard"));
        sauces.add(new Sauces(sauceId, "Ketchup"));
        sauces.add(new Sauces(sauceId, "Ranch"));
        sauces.add(new Sauces(sauceId, "Thousand Islands"));
        sauces.add(new Sauces(sauceId, "Vinaigrette"));
        return sauces;
    }

    private HashSet<Topping> handleSides() {
        HashSet<Topping> sides = new HashSet<>();
        SideChoice choice;
        String side = "";
        boolean addmore = false;
        // getSides
        do {
            choice = ui.getSides();
            switch (choice) {
                case None:
                    sides.clear();
                    break;
                case All :
                    sides.clear();
                    sides.add(new Side("Au Jus"));
                    sides.add(new Side("Sauces"));
                    break;
                default:
                    side = switch (choice) {
                        case SideChoice.AuJus -> "Au Jus";
                        case SideChoice.Sauces -> "Sauces";
                        default -> "";
                    };
                    sides.add(new Side(side));
                    addmore = ui.addMore();
            }
        } while (addmore);
        return sides;
    }

    private Drink handleNewDrink() {
        return ui.getDrink() ? new Drink(handleSize(ui.getDrinkSize()),
                switch(ui.getDrinkFlavor()) {
                    case Cola -> "Cola";
                    case RootBeer -> "Root Beer";
                    case Orange -> "Orange";
                    case LemonLime -> "Lemon Lime";
                    case GingerAle -> "Ginger Ale";
                })
                : null;
    }

    private Chips handleNewChips() {
        return ui.getChips() ? new Chips(
                switch(ui.getChipBrand()) {
                    case Lays -> "Lays";
                    case Doritos -> "Doritos";
                    case SunChips -> "SunChips";
                    case KettleChips -> "KettleChips";
                }) : null;
    }

    private void handleCheckout() {
        OrderChoice choice = ui.getCheckout(customer);
        switch (choice) {
            case Receipt:
                Receiptify receipt = new Receiptify();
                receipt.writeToReceipt(customer);
                break;
            case CancelOrder:
                break;
        }
    }

    private void handleCancelOrder() {
        customer.clearOrder();
    }
    private void handleContintueToCheckout() {
        boolean choice = ui.continueToCheckout();
        if (choice) {
            handleCheckout();
        } else {
            handleCancelOrder();
            System.exit(0);
        }
    }
}
