import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {


	private static final Double NO_COIN = (Double) 0.0;
	
	private VendingMachine vendingMachine;

	@Before
	public void setUp() {
		vendingMachine = new VendingMachine();
	}

	@Test
	public void shouldAcceptQuarter() {
		
		vendingMachine.insert(Coin.QUARTER);

		assertEquals(Coin.QUARTER.getValue(), vendingMachine.getCurrentAmount());
	}

	@Test
	public void shouldResetCurrentAmountEqualToZeroWhenReturnCoinsIsPressed() {
		
		vendingMachine.setCurrentAmount(Coin.QUARTER.getValue());

		vendingMachine.returnCoins();

		assertEquals(NO_COIN, vendingMachine.getCurrentAmount());
	}

	@Test
	public void pennyShouldGoToCoinReturn() {
		vendingMachine.insert(Coin.PENNY);

		assertEquals(Coin.PENNY.getValue(), vendingMachine.getCoinReturnAmount());
	}

	@Test
	public void shouldNotAddPennyToCurrentValue() {
		vendingMachine.insert(Coin.PENNY);
		
		assertEquals(NO_COIN, vendingMachine.getCurrentAmount());
	}
	
	@Test
	public void shouldAcceptNickel() {
		vendingMachine.insert(Coin.NICKEL);

		assertEquals(Coin.NICKEL.getValue(), vendingMachine.getCurrentAmount());
	}

	@Test
	public void shouldAcceptDime() {
		vendingMachine.insert(Coin.DIME);

		assertEquals(Coin.DIME.getValue(), vendingMachine.getCurrentAmount());
	}

	@Test
	public void pressingCoinReturnShouldReturnExactCoinsInserted() {
		insertFiftyCentsInQuarters();
		vendingMachine.insert(Coin.NICKEL);

		List<Coin> expectedCoinList = new ArrayList<Coin>();
		expectedCoinList.add(Coin.QUARTER);
		expectedCoinList.add(Coin.QUARTER);
		expectedCoinList.add(Coin.NICKEL);

		assertEquals(expectedCoinList, vendingMachine.returnCoins());
	}

	@Test
	public void returnCoinSlotShouldHoldAllCoinsReturned() {
		vendingMachine.insertPenny();
		insertFiftyCentsInQuarters();
		vendingMachine.insert(Coin.NICKEL);

		List<Coin> expectedCoinList = new ArrayList<Coin>();
		expectedCoinList.add(Coin.PENNY);
		expectedCoinList.add(Coin.QUARTER);
		expectedCoinList.add(Coin.QUARTER);
		expectedCoinList.add(Coin.NICKEL);

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

		assertEquals(Coin.QUARTER.getValue(), vendingMachine.getCoinReturnAmount());
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
