package com.pluralsight.models.addedExtras;

public class Drink extends AddOn {
    public Drink(int size, String name) {
        super(
                size,
                switch(size) {
                    case 1 -> 2.00;
                    case 2 -> 2.50;
                    case 3 -> 3.00;
                    default -> throw new IllegalStateException("Unexpected value: " + size);
                },
                name
        );
    }
    @Override
    public String toString() {
        return String.format("%-33s %-9s $%4.2f",
                getName(),
                switch(getSize()) {
                    case 1 -> "Small";
                    case 2 -> "Medium";
                    case 3 -> "Large";
                    default -> throw new IllegalStateException("Unexpected value: " + super.getSize());
                },
                getPrice());
    }
}
