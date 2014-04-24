import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

	Double currentAmount = 0.0;
	Double coinReturnAmount = 0.0;
	ArrayList<Double> coinsList = new ArrayList<Double>();
	ArrayList<Double> returnSlotCoins = new ArrayList<Double>();
	String display = "";
	ArrayList<String> itemBinList = new ArrayList<String>();
	

	public ArrayList<Double> returnCoins() {
		setCoinReturnAmount(getCurrentAmount());	
		setCurrentAmount(0.00);
		
		returnSlotCoins.addAll(coinsList);		
		return coinsList;
	}

	public void insertPenny() {
		setCoinReturnAmount(0.01);
		
		returnSlotCoins.add((Double)0.01);
	}

	public void insertNickel() {
		setCurrentAmount(getCurrentAmount() + 0.05);
		updateDisplay();
        
		coinsList.add((Double)0.05);		
	}

	public void insertDime() {
		setCurrentAmount(getCurrentAmount() + 0.10);
		updateDisplay();		
		
		coinsList.add((Double)0.10);		
	}	

	public void insertQuarter() {
		setCurrentAmount(getCurrentAmount() + 0.25);
		updateDisplay();
		
		coinsList.add((Double)0.25);
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

	public ArrayList<Double> getReturnSlotCoins() {
		return returnSlotCoins;
	}

	public void setReturnSlotCoins(ArrayList<Double> returnSlotCoins) {
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
