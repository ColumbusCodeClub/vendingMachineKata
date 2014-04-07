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
		vendingMachine.update();

		assertEquals("$0.25", vendingMachine.display());
	}

	@Test
	public void shouldNotAcceptPenny() {
		insertQuarter();
		insertPenny();
		vendingMachine.update();

		assertEquals("$0.25", vendingMachine.display());
		assertEquals((Double) PENNY, vendingMachine.getCoinReturnAmount());
	}

	@Test
	public void shouldAcceptNickel() {
		insertNickle();
		vendingMachine.update();

		assertEquals("$0.05", vendingMachine.display());
	}

	@Test
	public void shouldAcceptDime() {
		insertDime();
		vendingMachine.update();
		
		assertEquals("$0.10", vendingMachine.display());
	}

	@Test
	public void shouldResetCurrentAmountEqualToZeroWhenReturnCoinsIsPressed() {
		insertQuarter();
		vendingMachine.update();

		vendingMachine.returnCoins();

		assertEquals("$0.00", vendingMachine.display());
	}	
	
	@Test
	public void pressingCoinReturnShouldReturnExactCoinsInserted() {
		insertFiftyCentsInQuarters();
		insertNickle();

		ArrayList<String> expectedCoinList = new ArrayList<String>();
		expectedCoinList.add("Quarter");
		expectedCoinList.add("Quarter");
		expectedCoinList.add("Nickle");
		vendingMachine.update();

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
		vendingMachine.update();

		assertEquals("$0.55", vendingMachine.display());
	}

	@Test
	public void displayShouldShowAmountOfCurrencyInsertedIntoMachine() {
		insertDollarInQuarters();
		insertQuarter();
		vendingMachine.update();
		

		assertEquals("$1.25", vendingMachine.display());
	}

	@Test
	public void itemBinShouldHoldSodaWhenSodaButtonIsPressed() {
		insertDollarInQuarters();
		insertFiftyCentsInQuarters();
		vendingMachine.update();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Soda");

		vendingMachine.sodaButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendSodaIfChangeIsInsufficent() {
		insertDollarInQuarters();
		vendingMachine.update();

		vendingMachine.sodaButton();

		assertEquals(new ArrayList<String>(), vendingMachine.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldChipsWhenChipsButtonIsPressed() {
		insertDollarInQuarters();
		vendingMachine.update();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Chips");

		vendingMachine.chipsButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendChipsIfChangeIsInsufficent() {
		insertFiftyCentsInQuarters();
		vendingMachine.update();

		vendingMachine.chipsButton();

		assertEquals(new ArrayList<String>(), vendingMachine.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldCandyWhenCandyButtonIsPressed() {
		insertFiftyCentsInQuarters();
		vendingMachine.update();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Candy");

		vendingMachine.candyButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendCandyIfChangeIsInsufficent() {
		insertQuarter();
		insertNickle();
		vendingMachine.update();

		vendingMachine.candyButton();

		assertEquals(new ArrayList<String>(), vendingMachine.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldCandySodaChipsIfAllArePressed() {
		insertDollarInQuarters();
		insertDollarInQuarters();
		insertDollarInQuarters();
		insertQuarter();
		vendingMachine.update();

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
		vendingMachine.update();

		vendingMachine.sodaButton();
		vendingMachine.returnCoins();

		assertEquals((Double) QUARTER, vendingMachine.getCoinReturnAmount());
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
