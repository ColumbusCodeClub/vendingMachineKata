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
	private VendingCalc coinCalc;
	private VendItem soda;
	private VendItem chips;
	private VendItem candy;

	public VendingMachine() {
		coinCalc = new CoinCalculator();
		soda = new Soda();
		chips = new Chips();
		candy = new Candy();
	}

	public ArrayList<String> returnCoins() {
		setCoinReturnAmount(getCurrentAmount());
		setCurrentAmount(0.00);

		returnSlotCoins.addAll(coinList);
		return coinList;
	}

	public void insertCoin(String coin, double coinAmount) {
		if (coinIsPenny(coin)) {
			pennyFallsToReturnSlot(coin, coinAmount);
		} else {
			coinAdded(coin, coinAmount);
		}
	}

	private void coinAdded(String coin, double coinAmount) {
		coinList.add(coin);
		coinCalc.insertCoin(coinAmount);
	}

	private void pennyFallsToReturnSlot(String coin, double coinAmount) {
		returnSlotCoins.add(coin);
		setCoinReturnAmount(coinAmount);
	}

	private boolean coinIsPenny(String coin) {
		return coin.equals("Penny");
	}

	public void update() {
		setCurrentAmount(coinCalc.calcTotalAmount());
		updateDisplay();
	}

	public void sodaButton() {
		itemBinList = soda.vend(itemBinList, getCurrentAmount());
		updateChangeAmount();
	}

	public void chipsButton() {
		itemBinList = chips.vend(itemBinList, getCurrentAmount());
		updateChangeAmount();
	}

	public void candyButton() {
		itemBinList = candy.vend(itemBinList, getCurrentAmount());
		updateChangeAmount();
	}

	private void updateDisplay() {
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

	public Double getCoinReturnAmount() {
		return coinReturnAmount;
	}

	public void setCoinReturnAmount(Double coinReturnAmount) {
		this.coinReturnAmount = coinReturnAmount;
	}
	
	public ArrayList<String> getReturnSlotCoins() {
		return returnSlotCoins;
	}

	public void setReturnSlotCoins(ArrayList<String> returnSlotCoins) {
		this.returnSlotCoins = returnSlotCoins;
	}

	public String display() {
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
	
	private void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

	private Double getCurrentAmount() {
		return currentAmount;
	}	

}
