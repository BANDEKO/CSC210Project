/**Group Project
 * CIS 210-0900
 * Osman Goni Rifat & Roger Siambe
 * Due 12/6/2023, Wednesday
 */

package com.example.fx1120demo;

public class  ClothingStore {

    private String[][] loginInfo = {{"Baskin", "100 Chambers"}};
    private String[] itemNames = {"Jeans", "T-shirt", "Sweater", "Socks"};
    private double[] unitCosts = {29.99, 5.99, 15.99, 1.99};
    private double taxRate = 0.08;

    public String getUsername() {
        return loginInfo[0][0];
    }

    public String getPassword() {
        return loginInfo[0][1];
    }

    public double getPrice(String itemName) {
        for (int i = 0; i < itemNames.length; i++) {
            if (itemNames[i].equalsIgnoreCase(itemName)) {
                return unitCosts[i];
            }
        }
        return -1; // Item not found
    }

    public String[] getItemNames() {
        return itemNames;
    }

    public double calculateCost(String itemName, int quantity) {
        double unitCost = getPrice(itemName);
        if (unitCost != -1) {
            return unitCost * quantity;
        }
        return -1; // Item not found
    }

    public double calculateTaxes(double preCost) {
        return preCost * taxRate;
    }

    public double calculateTotalCost(double preCost, double taxes) {
        return preCost + taxes;
    }
}
