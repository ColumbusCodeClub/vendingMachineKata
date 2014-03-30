package vending;

import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

	Double currentAmount = 0.0;
	Double coinReturnAmount = 0.0;
	ArrayList<String> returnSlotCoins = new ArrayList<String>();
	String display = "";
	ArrayList<String> itemBinList = new ArrayList<String>();
	ArrayList<String> coinList = new ArrayList<String>();
	private CoinCalculator coinCalc;

	public VendingMachine() {
		coinCalc = new CoinCalculatorImpl();
	}
	
	public ArrayList<String> returnCoins() {
		setCoinReturnAmount(getCurrentAmount());	
		setCurrentAmount(0.00);
		
		returnSlotCoins.addAll(coinList);		
		return coinList;
	}

	public void insertCoin(String coin, double coinAmount) {
		coinList.add(coin);
		coinCalc.insertCoin(coinAmount);
	}
	
	public void insertPenny() {
		setCoinReturnAmount(0.01);
		
		coinList.add("Penny");		
	}

	public void insertNickel() {
		calculateCurrentAmount();
		updateDisplay();
        
		coinList.add("Nickle");		
	}

	public void calculateCurrentAmount() {
		setCurrentAmount(coinCalc.calcTotalAmount());
	}

	public void insertQuarter() {
		setCurrentAmount(getCurrentAmount() + 0.25);
		updateDisplay();
		
		coinList.add("Quarter");
	}
	
	public void sodaButton() {
		if (getCurrentAmount() >= 1.25){
			updateChangeAmount();
			itemBinList.add("Soda");
		}
	}

	public void chipsButton() {
		if (getCurrentAmount() >= 0.75){
			updateChangeAmount();			
			itemBinList.add("Chips");
		}
	}
	
	public void candyButton() {
		if (getCurrentAmount() >= 0.50){
			updateChangeAmount();			
			itemBinList.add("Candy");
		}
	}
	
	// Helper Methods
	public void updateDisplay() {
		NumberFormat nf = NumberFormat.getInstance();  
		nf.setMinimumFractionDigits(2);  
		String stringConversion = nf.format(getCurrentAmount()); 		
		setDisplay("$" + stringConversion);
	}
	private void updateChangeAmount() {
		if (getCurrentAmount() >= 1.25) {
			setCurrentAmount(getCurrentAmount() - 1.25);
		}
	}
	
	//Getters / Setters
	
	public Double getCoinReturnAmount() {
		return coinReturnAmount;
	}
	
	public void setCoinReturnAmount(Double coinReturnAmount) {
		this.coinReturnAmount = coinReturnAmount;
	}
	
	public Double getCurrentAmount() {
		return currentAmount;
	}
	
	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public ArrayList<String> getReturnSlotCoins() {
		return returnSlotCoins;
	}

	public void setReturnSlotCoins(ArrayList<String> returnSlotCoins) {
		this.returnSlotCoins = returnSlotCoins;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public ArrayList<String> getItemBinList() {
		return itemBinList;
	}

	public void setItemBinList(ArrayList<String> itemBinList) {
		this.itemBinList = itemBinList;
	}



}
