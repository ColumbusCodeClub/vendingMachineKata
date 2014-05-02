
public enum Coin {
	QUARTER(0.25), DIME(0.1), NICKEL(0.05), PENNY(0.01);
	private double value;
	Coin(double value) {
		this.value = value;
	}
	public Double getValue() {
		return value;
	}
}
