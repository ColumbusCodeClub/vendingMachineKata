package vending;

import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

	private Double currentAmount = 0.0;
	private Double coinReturnAmount = 0.0;
	private ArrayList<String> returnSlotCoins = new ArrayList<String>();
	private String display = "";
	private ArrayList<String> itemBinList = new ArrayList<String>();
	private ArrayList<String> coinList = new ArrayList<String>();
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

	public void update() {
		setCurrentAmount(coinCalc.calcTotalAmount());
		updateDisplay();
	}

	public ArrayList<String> returnCoins() {
		setCoinReturnAmount(currentAmount);
		returnSlotCoins.addAll(coinList);
		coinList.removeAll(coinList);
		setCurrentAmount(0.00);
		updateDisplay();

		return returnSlotCoins;
	}

	public void insertCoin(String coin, double coinAmount) {
		if (coinIsPenny(coin)) {
			pennyFallsToReturnSlot(coin, coinAmount);
		} else {
			coinAdded(coin, coinAmount);
		}
	}

	public void sodaButton() {
		itemBinList = soda.vend(itemBinList, currentAmount);
		updateChangeAmount();
	}

	public void chipsButton() {
		itemBinList = chips.vend(itemBinList, currentAmount);
		updateChangeAmount();
	}

	public void candyButton() {
		itemBinList = candy.vend(itemBinList, currentAmount);
		updateChangeAmount();
	}

	public Double getCoinReturnAmount() {
		return coinReturnAmount;
	}

	public ArrayList<String> getReturnSlotCoins() {
		return returnSlotCoins;
	}

	public String display() {
		return display;
	}

	public ArrayList<String> getItemBinList() {
		return itemBinList;
	}

	private void setDisplay(String display) {
		this.display = display;
	}

	private void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

	private void updateDisplay() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);
		String stringConversion = nf.format(currentAmount);
		setDisplay("$" + stringConversion);
	}

	private void updateChangeAmount() {
		if (currentAmount >= 1.25) {
			setCurrentAmount(currentAmount - 1.25);
		}
	}

	private void setCoinReturnAmount(Double coinReturnAmount) {
		this.coinReturnAmount = coinReturnAmount;
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

}
