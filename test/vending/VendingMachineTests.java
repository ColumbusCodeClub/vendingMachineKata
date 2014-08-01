import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {


	private static final Double NO_COIN = (Double) 0.0;
	
	private VendingMachine underTest;

	private CurrencyHandlerStub currencyHandlerStub;
	@Before
	public void setUp() {
		 currencyHandlerStub = new CurrencyHandlerStub();
		underTest = new VendingMachine(currencyHandlerStub);
	}

	@Test
	public void shouldAcceptQuarter() {
		
		underTest.insert(Coin.QUARTER);

		assertEquals(Coin.QUARTER.getValue(), underTest.getCurrentAmount());
	}

	@Test
	public void shouldResetCurrentAmountEqualToZeroWhenReturnCoinsIsPressed() {
		
		underTest.setCurrentAmount(Coin.QUARTER.getValue());

		underTest.returnCoins();

		assertEquals(NO_COIN, underTest.getCurrentAmount());
	}

	@Test
	public void pennyShouldGoToCoinReturn() {
		underTest.insert(Coin.PENNY);

		assertEquals(Coin.PENNY.getValue(), underTest.getCoinReturnAmount());
	}

	@Test
	public void shouldNotAddPennyToCurrentValue() {
		underTest.insert(Coin.PENNY);
		
		assertEquals(NO_COIN, underTest.getCurrentAmount());
	}
	
	@Test
	public void shouldAcceptNickel() {
		underTest.insert(Coin.NICKEL);

		assertEquals(Coin.NICKEL.getValue(), underTest.getCurrentAmount());
	}

	@Test
	public void shouldAcceptDime() {
		underTest.insert(Coin.DIME);

		assertEquals(Coin.DIME.getValue(), underTest.getCurrentAmount());
	}

	@Test
	public void pressingCoinReturnShouldReturnExactCoinsInserted() {
		insertFiftyCentsInQuarters();
		underTest.insert(Coin.NICKEL);

		List<Coin> expectedCoinList = new ArrayList<Coin>();
		expectedCoinList.add(Coin.QUARTER);
		expectedCoinList.add(Coin.QUARTER);
		expectedCoinList.add(Coin.NICKEL);

		assertEquals(expectedCoinList, underTest.returnCoins());
	}

	@Test
	public void returnCoinSlotShouldHoldAllCoinsReturned() {
		underTest.insertPenny();
		insertFiftyCentsInQuarters();
		underTest.insert(Coin.NICKEL);

		List<Coin> expectedCoinList = new ArrayList<Coin>();
		expectedCoinList.add(Coin.PENNY);
		expectedCoinList.add(Coin.QUARTER);
		expectedCoinList.add(Coin.QUARTER);
		expectedCoinList.add(Coin.NICKEL);

		underTest.returnCoins();

		assertEquals(expectedCoinList, underTest.getReturnSlotCoins());
	}

	@Test
	public void currentAmountShouldContinueToSumAllChangePutInMachine() {
		underTest.insertPenny();
		insertFiftyCentsInQuarters();
		underTest.insert(Coin.NICKEL);

		assertEquals((Double) 0.55, underTest.getCurrentAmount());
	}

	@Test
	public void displayShouldShowAmountOfCurrencyInsertedIntoMachine() {
		insertDollarInQuarters();
		underTest.insert(Coin.DIME);
		underTest.insert(Coin.DIME);
		underTest.insert(Coin.NICKEL);

		assertEquals("$1.25", underTest.getDisplay());
	}

	@Test
	public void itemBinShouldHoldSodaWhenSodaButtonIsPressed() {
		insertDollarInQuarters();
		insertFiftyCentsInQuarters();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Soda");

		underTest.sodaButton();

		assertEquals(expectedItemBinList, underTest.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendSodaIfChangeIsInsufficent() {
		insertDollarInQuarters();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();

		underTest.sodaButton();

		assertEquals(expectedItemBinList, underTest.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldChipsWhenChipsButtonIsPressed() {
		insertDollarInQuarters();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Chips");

		underTest.chipsButton();

		assertEquals(expectedItemBinList, underTest.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendChipsIfChangeIsInsufficent() {
		insertFiftyCentsInQuarters();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();

		underTest.chipsButton();

		assertEquals(expectedItemBinList, underTest.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldCandyWhenCandyButtonIsPressed() {
		insertFiftyCentsInQuarters();

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Candy");

		underTest.candyButton();

		assertEquals(expectedItemBinList, underTest.getItemBinList());
	}

	@Test
	public void vendingMachineShouldNotVendCandyIfChangeIsInsufficent() {
		underTest.insert(Coin.QUARTER);
		underTest.insert(Coin.NICKEL);

		ArrayList<String> expectedItemBinList = new ArrayList<String>();

		underTest.candyButton();

		assertEquals(expectedItemBinList, underTest.getItemBinList());
	}

	@Test
	public void itemBinShouldHoldCandySodaChipsIfAllArePressed() {
		insertDollarInQuarters();
		insertDollarInQuarters();
		insertDollarInQuarters();
		underTest.insert(Coin.QUARTER);

		ArrayList<String> expectedItemBinList = new ArrayList<String>();
		expectedItemBinList.add("Candy");
		expectedItemBinList.add("Soda");
		expectedItemBinList.add("Chips");

		underTest.candyButton();
		underTest.sodaButton();
		underTest.chipsButton();

		assertEquals(expectedItemBinList, underTest.getItemBinList());
	}

	@Test
	public void machineShouldMakeChangeIfTooMuchMoneyPaidForSoda() {
		insertDollarInQuarters();
		insertFiftyCentsInQuarters();

		underTest.sodaButton();
		underTest.returnCoins();

		assertEquals(Coin.QUARTER.getValue(), underTest.getCoinReturnAmount());
	}

	private void insertDollarInQuarters() {
		insertFiftyCentsInQuarters();
		insertFiftyCentsInQuarters();
	}

	private void insertFiftyCentsInQuarters() {
		underTest.insert(Coin.QUARTER);
		underTest.insert(Coin.QUARTER);
	}
	
	@Test
	public void shouldCallCurrencyHandlerWhenMoneyIsInserted(){
		underTest.insert(Coin.QUARTER);
		assertEquals(Coin.QUARTER, currencyHandlerStub.getCoin());	
	}
	
	public class CurrencyHandlerStub implements CurrencyHandler{
		Coin coin;
		Double currentAmount = 0.0;
		public Coin getCoin() {
			return coin;
		}
		@Override
		public void insert(Coin coin){
			this.coin=coin;
			this.currentAmount = getCurrentAmount() + coin.getValue();
		}
		@Override
		public Double getCurrentAmount() {
			return this.currentAmount;
		}
		@Override
		public void setCurrentAmount(Double currentAmount) {
			this.currentAmount = currentAmount;
		}
		
	}
}
