# playtech
Repo for test project for Playtech

## Original requirements
Write a program and associated unit tests that can price a basket of goods considering
some special offers.
The goods that can be purchased, together with their normal prices are:
* Soup – 65p per tin
* Bread – 80p per loaf
* Milk – £1.30 per bottle
* Apples – £1.00 per bag

Current special offers:
* Apples have a 10% discount off their normal price this week
* Buy 2 tins of soup and get a loaf of bread for half price


The program should accept a list of items in the basket and output the subtotal, the special
offer discounts and the final price.

Input should be via the command line in the form `PriceBasket item1 item2 item3 …`

For example:
`PriceBasket Apple Milk Bread`

Output should be to the console, for example:

`Subtotal: £3.10`

`Apples 10% off: -10p`

`Total: £3.00 `

If no special offers are applicable the code should output:

`Subtotal: £1.30`

`(No offers available)`

`Total price: £1.30`

The code and design should meet these requirements, but be sufficiently flexible to allow
future changes to the product list and/or discounts applied.

The code should be well structured, commented, have error handling and be tested.


## Solution
[UPDATED]

Introduced wrapper class for the shop containing product list and method for updating  the product  price
Passing reference to the store object when creating the basket and applying discounts
----
~~Ideally I would introduce another class to be wrapper for actual items in cart and separate
  it logically from Product class. Product should be able to have his price updated.
  I would also add Shop class which would define the products and price list 
  for the shop and initialize the discounts, init new baskets and pass available discounts to them~~
  
User gets basket assigned to him with a reference to the product list.
Products are added based on name from the user input.
Total sum of item prices is calculated and then all the discounts are applied **if** 
they are applicable to the basket. 
_Basket_ allows only inserts of items, calculating the price, adding possible discounts
and getting subtotal, discount amount and total amount.

Used BigDecimal because precision issues with float and double in Java
If new Discounts are needed they can be added by creating new class that implements Discount interface
Alternatively Discount counld be replaced with FunctionalInterface and then create 
specific discounts as lambda functions.

The application resides in `com.luka.playtech` package:


**Main module:**

_Basket_ class that represents shopping cart, 

_Discount_ Interface used to define behaviour for discounts

_Product_ - wrapper class for product item containing product name and price

_ShopRunner_ - class used to run the app as a user, it accepts user input on the command line
in the form of 

`PriceBasket item1 item2 item3`

_discounts/Apple10PercentOff_ - Class implementing discount on apples

_discounts/HalfBreadTwoTinSoups_ - Class implementing discount based on soups


**Test module:**

_TestShop_ - tests for the Basket class and interfaces 
