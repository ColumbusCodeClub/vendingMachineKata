import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

	Double currentAmount = 0.0;
	Double coinReturnAmount = 0.0;
	ArrayList<Coin> coinsList = new ArrayList<Coin>();
	ArrayList<Coin> returnSlotCoins = new ArrayList<Coin>();
	String display = "";
	ArrayList<String> itemBinList = new ArrayList<String>();
	CurrencyHandler currencyHandler;

	public VendingMachine(CurrencyHandler currencyHandler) {
		this.currencyHandler = currencyHandler;
	}

	public ArrayList<Coin> returnCoins() {
		setCoinReturnAmount(getCurrentAmount());
		setCurrentAmount(0.00);

		returnSlotCoins.addAll(coinsList);
		return coinsList;
	}

	public void insertPenny() {
		setCoinReturnAmount(0.01);

		returnSlotCoins.add(Coin.PENNY);
	}

	public void sodaButton() {
		if (getCurrentAmount() >= 1.25) {
			updateChangeAmount();
			itemBinList.add("Soda");
		}
	}

	public void chipsButton() {
		if (getCurrentAmount() >= 0.75) {
			updateChangeAmount();
			itemBinList.add("Chips");
		}
	}

	public void candyButton() {
		if (getCurrentAmount() >= 0.50) {
			updateChangeAmount();
			itemBinList.add("Candy");
		}
	}

	// Helper Methods
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

	// Getters / Setters

	public Double getCoinReturnAmount() {
		return coinReturnAmount;
	}

	public void setCoinReturnAmount(Double coinReturnAmount) {
		this.coinReturnAmount = coinReturnAmount;
	}

	public Double getCurrentAmount() {
		return currencyHandler.getCurrentAmount();
	}

	public void setCurrentAmount(Double currentAmount) {
		currencyHandler.setCurrentAmount(currentAmount);
	}

	public ArrayList<Coin> getReturnSlotCoins() {
		return returnSlotCoins;
	}

	public void setReturnSlotCoins(ArrayList<Coin> returnSlotCoins) {
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

	public void insert(Coin coin) {
		this.currencyHandler.insert(coin);
		if (isValidCoin(coin)) {
			setCurrentAmount(getCurrentAmount() + coin.getValue());
			coinsList.add(coin);
			updateDisplay();
		} else {
			insertPenny();
			return;
		}

	}

	private boolean isValidCoin(Coin coin) {
		return !Coin.PENNY.equals(coin);
	}

}
