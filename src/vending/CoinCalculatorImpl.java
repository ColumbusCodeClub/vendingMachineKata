package vending;

public class CoinCalculatorImpl implements CoinCalculator {
	private double totalAmount;

	@Override
	public void insertCoin(double coinAmount) {
		totalAmount += coinAmount;
	}

	@Override
	public double calcTotalAmount() {
		return totalAmount;
	}
}
