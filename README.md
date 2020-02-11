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
The application resides in `com.luka.playtech` package:

**Basket** class that represents shopping cart, 

**Discount** Inetrface used to define behaviour for discounts

**Product** - wrapper class for product item containing product name and price

**ShopRunner** - class used to run the app as a user, it accepts user input on the command line
in the form of 

`PriceBasket item1 item2 item3`

**discounts/Apple10PercentOff** - Class implementing discount on apples

**discounts/HalfBreadTwoTinSoups** - Class implementing discount based on soups


test modules consists of one test class
**TestShop** - tests for the Basket class and interfaces 
