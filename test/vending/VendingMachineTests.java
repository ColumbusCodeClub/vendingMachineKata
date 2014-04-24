import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class VendingMachineTests {

	private VendingMachine vendingMachine;

	@Before
	public void setUp() {
		vendingMachine = new VendingMachine();
	}

	@Test
	public void shouldAcceptQuarter() {
		vendingMachine.insertQuarter();

		assertEquals((Double) 0.25, vendingMachine.getCurrentAmount());
	}

	@Test
	public void shouldResetCurrentAmountEqualToZeroWhenReturnCoinsIsPressed() {
		vendingMachine.setCurrentAmount(0.25);

		vendingMachine.returnCoins();

		assertEquals((Double) 0.0, vendingMachine.getCurrentAmount());
	}

	@Test
	public void shouldNotAcceptPenny() {
		vendingMachine.insertQuarter();

		vendingMachine.insertPenny();

		assertEquals((Double) 0.25, vendingMachine.getCurrentAmount());
		assertEquals((Double) 0.01, vendingMachine.getCoinReturnAmount());
	}

	@Test
	public void shouldAcceptNickel() {
		vendingMachine.insertNickel();

		assertEquals((Double) 0.05, vendingMachine.getCurrentAmount());
	}

	@Test
	public void shouldAcceptDime() {
		vendingMachine.insertDime();

		assertEquals((Double) 0.10, vendingMachine.getCurrentAmount());
	}

	@Test
	public void pressingCoinReturnShouldReturnExactCoinsInserted() {
		insertFiftyCentsInQuarters();
		vendingMachine.insertNickel();

		ArrayList<Double> expectedCoinList = new ArrayList<Double>();
		expectedCoinList.add((Double) 0.25);
		expectedCoinList.add((Double) 0.25);
		expectedCoinList.add((Double) 0.05);

		assertEquals(expectedCoinList, vendingMachine.returnCoins());
	}

	@Test
	public void returnCoinSlotShouldHoldAllCoinsReturned() {
		vendingMachine.insertPenny();
		insertFiftyCentsInQuarters();
		vendingMachine.insertNickel();

		ArrayList<Double> expectedCoinList = new ArrayList<Double>();
		expectedCoinList.add((Double) 0.01);
		expectedCoinList.add((Double) 0.25);
		expectedCoinList.add((Double) 0.25);
		expectedCoinList.add((Double) 0.05);

		vendingMachine.returnCoins();

		assertEquals(expectedCoinList, vendingMachine.getReturnSlotCoins());
	}

	@Test
	public void currentAmountShouldContinueToSumAllChangePutInMachine() {
		vendingMachine.insertPenny();
		insertFiftyCentsInQuarters();
		vendingMachine.insertNickel();

		assertEquals((Double) 0.55, vendingMachine.getCurrentAmount());
	}

	@Test
	public void displayShouldShowAmountOfCurrencyInsertedIntoMachine() {
		insertDollarInQuarters();
		vendingMachine.insertDime();
		vendingMachine.insertDime();
		vendingMachine.insertNickel();

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
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.insertQuarter();
		vendingMachine.insertNickel();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();

		vendingMachine.candyButton();

		assertEquals(expectedItemBinList, vendingMachine.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldCandySodaChipsIfAllArePressed() {
		insertDollarInQuarters();
		insertDollarInQuarters();
		insertDollarInQuarters();
		vendingMachine.insertQuarter();

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

		assertEquals((Double) 0.25, vendingMachine.getCoinReturnAmount());
	}

	private void insertDollarInQuarters() {
		insertFiftyCentsInQuarters();
		insertFiftyCentsInQuarters();
	}

	private void insertFiftyCentsInQuarters() {
		vendingMachine.insertQuarter();
		vendingMachine.insertQuarter();
	}
}
