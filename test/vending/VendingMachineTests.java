package vending;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTests {

	private static final double PENNY = 0.01;
	private static final double QUARTER = 0.25;
	private static final double NICKLE = 0.05;
	private static final double DIME = 0.10;
	private VendingMachine vendingMachine;

	@Before
	public void setUp() {
		vendingMachine = new VendingMachine();
	}

	@Test
	public void shouldAcceptQuarter() {
		insertQuarter();
		vendingMachine.calculateCurrentAmount();

		assertEquals((Double) 0.25, vendingMachine.getCurrentAmount());
	}

	@Test
	public void shouldResetCurrentAmountEqualToZeroWhenReturnCoinsIsPressed() {
		vendingMachine.setCurrentAmount(0.25);
		vendingMachine.calculateCurrentAmount();

		vendingMachine.returnCoins();

		assertEquals((Double) 0.0, vendingMachine.getCurrentAmount());
	}

	@Test
	public void shouldNotAcceptPenny() {
		insertQuarter();
		insertPenny();
		vendingMachine.calculateCurrentAmount();

		assertEquals((Double) 0.25, vendingMachine.getCurrentAmount());
		assertEquals((Double) 0.01, vendingMachine.getCoinReturnAmount());
	}

	@Test
	public void shouldAcceptNickel() {
		insertNickle();
		vendingMachine.calculateCurrentAmount();

		assertEquals((Double) 0.05, vendingMachine.getCurrentAmount());
	}



	@Test
	public void shouldAcceptDime() {
		insertDime();
		vendingMachine.calculateCurrentAmount();
		vendingMachine.updateDisplay();
		
		assertEquals((Double) 0.10, vendingMachine.getCurrentAmount());
	}



	@Test
	public void pressingCoinReturnShouldReturnExactCoinsInserted() {
		insertFiftyCentsInQuarters();
		insertNickle();

		ArrayList<String> expectedCoinList = new ArrayList<String>();
		expectedCoinList.add("Quarter");
		expectedCoinList.add("Quarter");
		expectedCoinList.add("Nickle");
		vendingMachine.calculateCurrentAmount();

		assertEquals(expectedCoinList, vendingMachine.returnCoins());
	}

	@Test
	public void returnCoinSlotShouldHoldAllCoinsReturned() {
		insertPenny();
		insertFiftyCentsInQuarters();
		insertNickle();

		ArrayList<String> expectedCoinList = new ArrayList<String>();
		expectedCoinList.add("Penny");
		expectedCoinList.add("Quarter");
		expectedCoinList.add("Quarter");
		expectedCoinList.add("Nickle");

		vendingMachine.returnCoins();

		assertEquals(expectedCoinList, vendingMachine.getReturnSlotCoins());
	}

	@Test
	public void currentAmountShouldContinueToSumAllChangePutInMachine() {
		insertPenny();
		insertQuarter();
		insertQuarter();
		insertNickle();
		vendingMachine.calculateCurrentAmount();

		assertEquals((Double) 0.55, vendingMachine.getCurrentAmount());
	}

	@Test
	public void displayShouldShowAmountOfCurrencyInsertedIntoMachine() {
		insertDollarInQuarters();
		
		insertQuarter();
		vendingMachine.calculateCurrentAmount();
		vendingMachine.updateDisplay();
		
		insertNickle();

		assertEquals("$1.25", vendingMachine.getDisplay());
	}

	@Test
	public void itemBinShouldHoldSodaWhenSodaButtonIsPressed() {
		insertDollarInQuarters();
		insertFiftyCentsInQuarters();
		vendingMachine.calculateCurrentAmount();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Soda");

		vendingMachine.sodaButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendSodaIfChangeIsInsufficent() {
		insertDollarInQuarters();
		vendingMachine.calculateCurrentAmount();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();

		vendingMachine.sodaButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldChipsWhenChipsButtonIsPressed() {
		insertDollarInQuarters();
		vendingMachine.calculateCurrentAmount();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Chips");

		vendingMachine.chipsButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendChipsIfChangeIsInsufficent() {
		insertFiftyCentsInQuarters();
		vendingMachine.calculateCurrentAmount();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();

		vendingMachine.chipsButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldCandyWhenCandyButtonIsPressed() {
		insertFiftyCentsInQuarters();
		vendingMachine.calculateCurrentAmount();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Candy");

		vendingMachine.candyButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendCandyIfChangeIsInsufficent() {
		VendingMachine vendingMachine = new VendingMachine();
		insertQuarter();
		insertNickle();
		vendingMachine.calculateCurrentAmount();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();

		vendingMachine.candyButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldCandySodaChipsIfAllArePressed() {
		insertDollarInQuarters();
		insertDollarInQuarters();
		insertDollarInQuarters();
		insertQuarter();
		vendingMachine.calculateCurrentAmount();

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
		vendingMachine.calculateCurrentAmount();

		vendingMachine.sodaButton();
		vendingMachine.returnCoins();

		assertEquals((Double) 0.25, vendingMachine.getCoinReturnAmount());
	}

	private void insertDollarInQuarters() {
		insertFiftyCentsInQuarters();
		insertFiftyCentsInQuarters();
	}

	private void insertFiftyCentsInQuarters() {
		insertQuarter();
		insertQuarter();
	}
	
	private void insertQuarter() {
		vendingMachine.insertCoin("Quarter", QUARTER);
	}
	
	private void insertDime() {
		vendingMachine.insertCoin("Dime", DIME);
	}
	
	private void insertNickle() {
		vendingMachine.insertCoin("Nickle", NICKLE);
	}
	private void insertPenny() {
		vendingMachine.insertCoin("Penny", PENNY);
	}
	
}
