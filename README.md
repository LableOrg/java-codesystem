Codesystem Utilities
====================

This is a mini-project that is home to a small collection of Java utilities that are used when dealing with coded 
values defined in standards and such.

## CodeReference

The `CodeReference` class is an immutable value class that is used to reference well-defined codes. In addition to 
holding the coded value itself (as a string), a reference to the code system it is defined in is stored as well. This 
is useful when you want to store not just the abstract code, but also the standard it belongs to.

The field names used in this class are taken from [IETF/RFC 3881](https://tools.ietf.org/html/rfc3881).

To give an example; say you have a project where you receive coded sets of data from a vendor — perhaps you've hacked 
the networked office coffeemaker to send its beverage statistics to a backend of you own, and you want to display 
live statistics on the beverage intake of your office on a screen.

The coffeemaker sends out its data in hourly blobs of XML containing abstract codes for each beverage in its selection. 
Let's say it uses `1` for *black coffee*.

You could store just this `1` in your database, along with the number of cups of black coffee dispensed, and that is 
fine as long as your frontend retrieving the data knows that it should interpret `1` as *black coffee*.

This approach is fine if the situation doesn't change, but what if you want to add the statistics from another device 
with a different set of codes? (say, `CfBk` for *black coffee*.) You could try to link the two sets of codes (store 
`CfBk` as `1` in the database so all your coffee stats make sense), but what if you add another brand of coffeemaker 
to the mix? And what if the coffeemaker you used the original set of codes from is retired? Now you are converting 
the existing codes to the old codes that isn't even in use anymore. And what if you decide that `CfBk` from one 
machine and `AMERICANO` from another are not the same beverage after all statistically speaking?

For long term statistics, instead of storing only the code, it makes sense to store the complete reference:

Field name       | Example value
-----------------|-----------------------------------
CodeSystem       | JoeInc.7443
CodeSystemName   | Joe Inc. MrCoffee (model nr. 7443)
Code             | CfBk
DisplayName      | Black Coffee

`CodeReference` holds these four fields.