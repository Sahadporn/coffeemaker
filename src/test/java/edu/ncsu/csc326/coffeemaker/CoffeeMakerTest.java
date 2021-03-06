/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import io.cucumber.junit.Cucumber;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import io.cucumber.java.en.*;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Sarah Heckman
 */
@RunWith(MockitoJUnitRunner.class)
public class CoffeeMakerTest {

//	@Mock
//	List<String> list;
	@Mock
	RecipeBook recipeBook;

	private Inventory coffeeInventory;

	private CoffeeMaker cfWithConstruct;

	private int moneyPaid;

	private static final double TOL = 1.0E-4;

	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {

		//MockitoAnnotations.initMocks(this);

		coffeeMaker = new CoffeeMaker();

		recipeBook = Mockito.mock(RecipeBook.class);
		coffeeInventory = new Inventory();
		cfWithConstruct = new CoffeeMaker(recipeBook, coffeeInventory);

		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
	}

	/**
	 * Initialize new recipe, recipe book, and inventory to test with cucumber.
	 * @throws RecipeException if there was an error parsing the ingredient
	 * amount when setting up the recipe.
	 */
	@io.cucumber.java.Before
	public void beforeSecnario() throws RecipeException {
		Recipe newRecipe = new Recipe();
		newRecipe.setName("Beverage magic");
		newRecipe.setAmtChocolate("10");
		newRecipe.setAmtCoffee("10");
		newRecipe.setAmtMilk("10");
		newRecipe.setAmtSugar("10");
		newRecipe.setPrice("50");
		moneyPaid = 0;
		recipeBook = new RecipeBook();
		coffeeInventory = new Inventory();
		recipeBook.addRecipe(newRecipe);
	}

	/**
	 * Use cucumber to test initialization of new coffeemaker
	 */
	@Given("I purchase a beverage")
	public void iPurchaseABeverage() throws RecipeException {
//		this.coffeeMaker = new CoffeeMaker();

		coffeeMaker = new CoffeeMaker(recipeBook, coffeeInventory);
//		this.coffeeMaker.addRecipe(newRecipe);
	}

	/**
	 * Use cucumber to set the money customer paid each time.
	 * @param moneyPaid money that customer pays for beverage.
	 */
	@When("I pay {int}")
	public void iPay(Integer moneyPaid) {
		this.moneyPaid = moneyPaid;
	}

	/**
	 * Use cucumber to set everything in the inventory to 0.
	 */
	@When("There are not enough ingredients")
	public void thereAreNotEnoughIngredients() {
		coffeeInventory.setChocolate(0);
		coffeeInventory.setCoffee(0);
		coffeeInventory.setMilk(0);
		coffeeInventory.setSugar(0);
	}

	/**
	 * Use cucumber to test change given back to customer with each purchase.
	 * @param moneyChange change given back to customer.
	 */
	@Then("I get {int} change")
	public void iGetChange(Integer moneyChange) {
		assertEquals(moneyChange, coffeeMaker.makeCoffee(0, this.moneyPaid), TOL);
	}


//	@Test
//	public void testFakeGet(){
//		assertNotNull(list);
//		System.out.println(list.get(0));
//		when(list.get(0)).thenReturn("zero");
//		System.out.println(list);
//		System.out.println(list.get(0));
//		assertEquals("zero", list.get(0));
////		verify(list).get(0);
//	}


	/**
	 * Use mockito to test a successful purchase
	 * from make coffee
	 *
	 * @ID FP1
	 */
	@Test
	public void testFakePurchase() {
		assertNotNull(recipeBook);
//		Recipe re = Mockito.mock(Recipe.class);
//		when(recipeBook.getRecipes()).thenReturn(new Recipe[]{re});
//		assertEquals(52, cfWithConstruct.makeCoffee(0, 52));
//		verify(re, times(2)).getAmtMilk();
		when(recipeBook.getRecipes()).thenReturn(new Recipe[]{recipe1});
		assertEquals(2, cfWithConstruct.makeCoffee(0, 52));
	}

	/**
	 * Use mockito to test not enough money purchase
	 * from makecoffee
	 *
	 * @ID FP2
	 */
	@Test
	public void testMockInvalidCashPurchase() {
		when(recipeBook.getRecipes()).thenReturn(new Recipe[]{recipe1});
		assertEquals(22, cfWithConstruct.makeCoffee(0, 22));
		assertEquals(0, cfWithConstruct.makeCoffee(0, 50));
	}

	/**
	 * Check inventory with mockito
	 * after a purchase.
	 *
	 * @ID FP3
	 */
	@Test
	public void testMockInventoryCheck() {
//		Inventory iv = new Inventory()
//		CoffeeMaker cfWithConstruct = new CoffeeMaker(recipeBook, iv);
		when(recipeBook.getRecipes()).thenReturn(new Recipe[]{recipe1});
		cfWithConstruct.makeCoffee(0, 80);
		assertEquals("Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n", cfWithConstruct.checkInventory());
	}

	/**
	 * Use mockito to check for return money
	 * when there is not enough ingredients.
	 *
	 * @ID FP4
	 */
	@Test
	public void testMockNotEnoughIngredients() {
		when(recipeBook.getRecipes()).thenReturn(new Recipe[]{recipe2});
		assertEquals(100, cfWithConstruct.makeCoffee(0,100));
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 *
	 * @ID: AI1
	 * @throws InventoryException  if there was an error parsing the quantity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * @ID: AI2CO1
	 * @throws InventoryException  if there was an error parsing the quantity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddNonIntCoffeeException() throws InventoryException {
		coffeeMaker.addInventory("rt", "rbh", "asdf", "ebr");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @ID: AI2CO2
	 * @throws InventoryException  if there was an error parsing the quantity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddNonPositiveCoffeeException() throws InventoryException {
		coffeeMaker.addInventory("-2", "rbh", "asdf", "ebr");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @ID: AI2M1
	 * @throws InventoryException  if there was an error parsing the quantity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddNonIntMilkException() throws InventoryException {
		coffeeMaker.addInventory("1","e","-23","-4");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @ID: AI2M2
	 * @throws InventoryException  if there was an error parsing the quantity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddNonPositiveMilkException() throws InventoryException {
		coffeeMaker.addInventory("1","-1","-23","-4");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @ID: AI2S1
	 * @throws InventoryException  if there was an error parsing the quantity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddNonIntSugarException() throws InventoryException {
		coffeeMaker.addInventory("1","3","vr","-4");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @ID: AI2S2
	 * @throws InventoryException  if there was an error parsing the quantity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddNonPositiveSugarException() throws InventoryException {
		coffeeMaker.addInventory("1","3","-43","-4");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @ID: AI2C1
	 * @throws InventoryException  if there was an error parsing the quantity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddNonIntChocoException() throws InventoryException {
		//In addSugar there is a bug that accept negative and throw error at positive int.
		//So to prevent error from sugar bug, sugar input will be negative.
		coffeeMaker.addInventory("1","5","-3","eg");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @ID: AI2C2
	 * @throws InventoryException  if there was an error parsing the quantity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddNonPositiveChocoException() throws InventoryException {
		coffeeMaker.addInventory("1","5","3","-1");
	}

	/**
	 * When add inventory ingredients should increase
	 * according to the amount add.
	 *
	 * @ID: AI3
	 */
	@Test
	public void testAddInventoryCorrectness() throws InventoryException {
		coffeeMaker.addInventory("1","2","1","1");
	}

	/**
	 * When add a new recipe to the recipe book successfully then
	 * it should return true
	 *
	 * @ID: AR1
	 */
	@Test
	public void testAddRecipe(){
		assertTrue(coffeeMaker.addRecipe(new Recipe()));
	}

	/**
	 * When the recipe book is full,
	 * delete a recipe to add a new one
	 * should be possible.
	 *
	 * @ID: AR2
	 */
	@Test
	public void testReAddRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.addRecipe(recipe4);
		coffeeMaker.deleteRecipe(3);
		assertTrue(coffeeMaker.addRecipe(recipe4));
	}

	/**
	 * When add a non positive integer to the recipe
	 * the code will not accept.
	 *
	 * @ID: AR3
	 * @throws RecipeException
	 */
	@Test(expected = RecipeException.class)
	public void testRecipeException() throws RecipeException {
		Recipe w = new Recipe();
		w.setAmtSugar("-3");
		w.setAmtMilk("www");
		coffeeMaker.addRecipe(w);
	}

	/**
	 * When a name of the recipe already exist
	 * return false
	 *
	 * @ID: AR4
	 */
	@Test
	public void testDuplicateName() {
		coffeeMaker.addRecipe(recipe3);
		Recipe ww = new Recipe();
		ww.setName("Latte");
		assertFalse(coffeeMaker.addRecipe(ww));
	}

	/**
	 * When deleting recipe it should return the name
	 * of the successfully deleted recipe
	 * and null if recipe cannot be deleted.
	 *
	 * @ID: DR1
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testDeleteRecipe() {
		Recipe r = new Recipe();
		r.setName("poison");

		coffeeMaker.addRecipe(r);
		coffeeMaker.deleteRecipe(5);
		assertEquals("poison", coffeeMaker.deleteRecipe(0));
	}

	/**
	 * When deleting a non existing recipe
	 * should return null
	 *
	 * @ID: DR2
	 */
	@Test
	public void testNonExistingDelete() {
		assertNull(coffeeMaker.deleteRecipe(0));
		coffeeMaker.addRecipe(new Recipe());
		coffeeMaker.deleteRecipe(0);
		assertNull(coffeeMaker.deleteRecipe(0));
	}

	/**
	 * When successfully edited a recipe
	 * it should return name of said recipe and null
	 * if it cannot be edit.
	 *
	 * @ID: ER1
	 */
	@Test
	public void testEditRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals("Coffee", coffeeMaker.getRecipes()[0].getName());

		coffeeMaker.editRecipe(0, recipe2);
		assertEquals("Mocha", coffeeMaker.getRecipes()[0].getName());
	}

	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 *
	 * @ID: MC1
	 */
	@Test
	public void testMakeCoffee() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}

	/**
	 * When customer pay less than the total price
	 * return the money back to the customer.
	 *
	 * @ID: MC2
	 */
	@Test
	public void testNotEnoughMoney() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(5, coffeeMaker.makeCoffee(0, 5));
	}

	/**
	 * When amount paid is negative.
	 * Coffee should not be made.
	 *
	 * @ID: MC3
	 */
	@Test
	public void testNegativeMoney() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.makeCoffee(0, -11);
	}

	/**
	 * When customer paid equal to the beverage price.
	 * There should be no change.
	 *
	 * @ID: MC4
	 */
	@Test
	public void testNoChange() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(0, coffeeMaker.makeCoffee(0, 50));
	}

	/**
	 * When making coffee more than the available ingredients
	 * it should return the money back to the customer and
	 * ingredients should decrease.
	 *
	 * @ID: MC5
	 * @throws RecipeException
	 */
	@Test
	public void testMakeCoffeeInventory() throws RecipeException {
		Recipe r = new Recipe();
		r.setName("www");
		r.setAmtChocolate("20");
		r.setAmtCoffee("20");
		r.setAmtMilk("70");
		r.setAmtSugar("70");
		r.setPrice("12");

		coffeeMaker.addRecipe(r);
//		coffeeMaker.makeCoffee(0,12);
//		coffeeMaker.makeCoffee(0,12);
		assertEquals(12, coffeeMaker.makeCoffee(0, 12));
		StringBuffer buf = new StringBuffer();
		buf.append("Coffee: ");
		buf.append("1");
		buf.append("\n");
		buf.append("Milk: ");
		buf.append("1");
		buf.append("\n");
		buf.append("Sugar: ");
		buf.append("1");
		buf.append("\n");
		buf.append("Chocolate: ");
		buf.append("1");
		buf.append("\n");
		assertEquals(buf.toString(), coffeeMaker.checkInventory());

	}

	/**
	 * When chosen recipe is null
	 * return the money to the customer.
	 *
	 * @ID: MC6
	 */
	@Test
	public void testNullRecipe() {
		assertEquals(12, coffeeMaker.makeCoffee(1,12));
	}

}
