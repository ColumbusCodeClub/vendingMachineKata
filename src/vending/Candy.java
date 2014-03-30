package vending;

import java.util.ArrayList;

public class Candy implements VendItem {

	@Override
	public ArrayList<String> vend(ArrayList<String> itemBinList, double currentAmount) {
		
		if (currentAmount >= 0.50) {
			itemBinList.add("Candy");
		}
		
		return itemBinList;
	}

}
