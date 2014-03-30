package vending;

import java.util.ArrayList;

public class Soda implements VendItem {

	@Override
	public ArrayList<String> vend(ArrayList<String> itemBinList, double currentAmount) {
		
		if (currentAmount >= 1.25) {
			itemBinList.add("Soda");
		}
		
		return itemBinList;
	}

}
