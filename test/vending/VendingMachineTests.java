import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	private static final Double DIME = (Double) 0.10;
	private static final Double NICKEL = (Double) 0.05;
	private static final Double PENNY = (Double) 0.01;
	private static final Double NO_COIN = (Double) 0.0;
	private static final Double QUARTER_VALUE = (Double) 0.25;
	
	private VendingMachine vendingMachine;

	@Before
	public void setUp() {
		vendingMachine = new VendingMachine();
	}

	@Test
	public void shouldAcceptQuarter() {
		
		vendingMachine.insert(Coin.QUARTER);

		assertEquals(QUARTER_VALUE, vendingMachine.getCurrentAmount());
	}

	@Test
	public void shouldResetCurrentAmountEqualToZeroWhenReturnCoinsIsPressed() {
		
		vendingMachine.setCurrentAmount(QUARTER_VALUE);

		vendingMachine.returnCoins();

		assertEquals(NO_COIN, vendingMachine.getCurrentAmount());
	}

	@Test
	public void shouldNotAcceptPenny() {
		vendingMachine.insert(Coin.PENNY);

		assertEquals(PENNY, vendingMachine.getCoinReturnAmount());
	}

	@Test
	public void shouldAcceptNickel() {
		vendingMachine.insert(Coin.NICKEL);

		assertEquals(NICKEL, vendingMachine.getCurrentAmount());
	}

	@Test
	public void shouldAcceptDime() {
		vendingMachine.insert(Coin.DIME);

		assertEquals(DIME, vendingMachine.getCurrentAmount());
	}

	@Test
	public void pressingCoinReturnShouldReturnExactCoinsInserted() {
		insertFiftyCentsInQuarters();
		vendingMachine.insert(Coin.NICKEL);

		ArrayList<Double> expectedCoinList = new ArrayList<Double>();
		expectedCoinList.add(QUARTER_VALUE);
		expectedCoinList.add(QUARTER_VALUE);
		expectedCoinList.add(NICKEL);

		assertEquals(expectedCoinList, vendingMachine.returnCoins());
	}

	@Test
	public void returnCoinSlotShouldHoldAllCoinsReturned() {
		vendingMachine.insertPenny();
		insertFiftyCentsInQuarters();
		vendingMachine.insert(Coin.NICKEL);

		ArrayList<Double> expectedCoinList = new ArrayList<Double>();
		expectedCoinList.add(PENNY);
		expectedCoinList.add(QUARTER_VALUE);
		expectedCoinList.add(QUARTER_VALUE);
		expectedCoinList.add(NICKEL);

		vendingMachine.returnCoins();

		assertEquals(expectedCoinList, vendingMachine.getReturnSlotCoins());
	}

	@Test
	public void currentAmountShouldContinueToSumAllChangePutInMachine() {
		vendingMachine.insertPenny();
		insertFiftyCentsInQuarters();
		vendingMachine.insert(Coin.NICKEL);

		assertEquals((Double) 0.55, vendingMachine.getCurrentAmount());
	}

	@Test
	public void displayShouldShowAmountOfCurrencyInsertedIntoMachine() {
		insertDollarInQuarters();
		vendingMachine.insert(Coin.DIME);
		vendingMachine.insert(Coin.DIME);
		vendingMachine.insert(Coin.NICKEL);

		assertEquals("$1.25", vendingMachine.getDisplay());
	}

	@Test
	public void itemBinShouldHoldSodaWhenSodaButtonIsPressed() {
		insertDollarInQuarters();
		insertFiftyCentsInQuarters();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Soda");

		vendingMachine.sodaButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendSodaIfChangeIsInsufficent() {
		insertDollarInQuarters();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();

		vendingMachine.sodaButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldChipsWhenChipsButtonIsPressed() {
		insertDollarInQuarters();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Chips");

		vendingMachine.chipsButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendChipsIfChangeIsInsufficent() {
		insertFiftyCentsInQuarters();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();

		vendingMachine.chipsButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldCandyWhenCandyButtonIsPressed() {
		insertFiftyCentsInQuarters();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Candy");

		vendingMachine.candyButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendCandyIfChangeIsInsufficent() {
		vendingMachine.insert(Coin.QUARTER);
		vendingMachine.insert(Coin.NICKEL);

		ArrayList<String> expectedItemBinList = new ArrayList<String>();

		vendingMachine.candyButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldCandySodaChipsIfAllArePressed() {
		insertDollarInQuarters();
		insertDollarInQuarters();
		insertDollarInQuarters();
		vendingMachine.insert(Coin.QUARTER);

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Candy");
		expectedItemBinList.add("Soda");
		expectedItemBinList.add("Chips");

		vendingMachine.candyButton();
		vendingMachine.sodaButton();
		vendingMachine.chipsButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void machineShouldMakeChangeIfTooMuchMoneyPaidForSoda() {
		insertDollarInQuarters();
		insertFiftyCentsInQuarters();

		vendingMachine.sodaButton();
		vendingMachine.returnCoins();

		assertEquals(QUARTER_VALUE, vendingMachine.getCoinReturnAmount());
	}

	private void insertDollarInQuarters() {
		insertFiftyCentsInQuarters();
		insertFiftyCentsInQuarters();
	}

	private void insertFiftyCentsInQuarters() {
		vendingMachine.insert(Coin.QUARTER);
		vendingMachine.insert(Coin.QUARTER);
	}
}
