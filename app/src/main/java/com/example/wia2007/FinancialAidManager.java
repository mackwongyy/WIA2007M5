package com.example.wia2007;

import java.util.ArrayList;

public class FinancialAidManager {
    private static ArrayList<FinancialAid> financialAidList = new ArrayList<>();

    // Initialize the list with default values if it's empty
    public static void initializeList() {
        if (financialAidList.isEmpty()) {
            financialAidList.add(new FinancialAid(1, "Mani Group Financial Aid", 500, 2, "02/12/2024", R.drawable.workspace1));
            financialAidList.add(new FinancialAid(2, "Wesley Foundation Aid", 10000, 1, "15/12/2024", R.drawable.workspace2));
            financialAidList.add(new FinancialAid(3, "Hua Chai Aid", 15000, 2, "13/12/2024", R.drawable.workspace3));
            financialAidList.add(new FinancialAid(4, "Ahmad and Co Fund", 20000, 2, "06/12/2024", R.drawable.workspace4));
            financialAidList.add(new FinancialAid(5, "Rodrigo Corporation Fund", 1500, 1, "01/01/2025", R.drawable.workspace5));
        }
    }

    // Get the list
    public static ArrayList<FinancialAid> getFinancialAidList() {
        return financialAidList;
    }

    // Update the list (e.g., when an entry is modified or removed)
    public static void updateFinancialAidList(ArrayList<FinancialAid> updatedList) {
        financialAidList = updatedList;
    }

    // Remove an entry by ID
    public static void removeFinancialAid(int aidID) {
        financialAidList.removeIf(aid -> aid.getAidID() == aidID);
    }
}