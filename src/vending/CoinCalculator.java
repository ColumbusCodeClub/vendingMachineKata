package vending;

public class CoinCalculator implements VendingCalc {
	private double totalAmount;

	@Override
	public void insertCoin(double coinAmount) {
		if (coinAmount != 0.01)
			totalAmount += coinAmount;
	}

	@Override
	public double calcTotalAmount() {
		return totalAmount;
	}
}
