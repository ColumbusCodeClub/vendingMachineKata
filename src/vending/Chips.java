package vending;

import java.util.ArrayList;

public class Chips implements VendItem {

	@Override
	public ArrayList<String> vend(ArrayList<String> itemBinList, double currentAmount) {
		
		if (currentAmount >= 0.75) {
			itemBinList.add("Chips");
		}
		
		return itemBinList;
	}

}
