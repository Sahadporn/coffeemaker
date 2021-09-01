<!--## Test Case ID-->
<!--| Id      | Test | pass | remark | -->
<!--| ----------- | ----------- | --- | --- |-->
<!--| AI1      | testAddInventory       | y |-->
<!--| AI2   | testAddInventoryException        | y |-->
<!--| AI3 | testAddInventoryCorrectness | n | sugar cannot add positive int |-->
<!--| AR1 | testAddRecipe | y |-->
<!--| AR2 | testReAddRecipe | n | cannot add new recipe after the old one was deleted |-->
<!--| AR3 | testRecipeException | y |-->
<!--| AR4 | testDuplicateName | y |-->
<!--| DR1 | testDeleteRecipe | y |-->
<!--| DR2 | testNonExistingDelete | n | it did not return null but empty name "" |-->
<!--| ER1 | testEditRecipe | n | return null instead of new name |-->
<!--| MC1 | testMakeCoffee | y |-->
<!--| MC2 | testNotEnoughMoney | y |-->
<!--| MC3 | testNegativeMoney | y |-->
<!--| MC4 | testNoChange | y |-->
<!--| MC5 | testMakeCoffeeInventory | n | coffee added to inventory instead of remove |--> 

## Requirement Traceability Matrix
| Requirement ID | Requirement Description | Testcase ID | Status |
| --- | --- | --- | --- |
| UC2 | Add Recipe | AR1<Br>AR2<br>AR3<br>AR4 | Pass<br>Fail<br>Pass<br>Pass |
| UC3 | Delete Recipe | DR1<br>DR2 | Pass<br>Fail |
| UC4 | Edit Recipe | ER1 | Fail |
| UC5 | Add Inventory | AI1<br>AI2CO1<br>AI2CO2<br>AI2M1<br>AI2M2<br>AI2S1<br>AI2S2<br>AI2C1<br>AI2C2<br>AI3 | Pass<br>Pass<br>Pass<br>Pass<br>Pass<br>Pass<br>Pass<br>Pass<br>Pass<br>Fail |
| UC7 | Purchase Beverage | MC1<br>MC2<br>MC3<br>MC4<br>MC5<br>MC6 | Pass<br>Pass<br>Pass<br>Pass<br>Fail<br>Pass |


