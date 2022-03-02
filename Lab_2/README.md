# Advanced Unit Testing

- Download **StrangeGame.java** from Github.
- Write tests for  StrangeGame class which satisfy the following case:
	- If a **notorious** player **enter the game** on **0:00 - 11:59**, verify that prison doesn’t do any imprisonment.
	- If a **notorious** player **enter the game** on **12:00 - 23:59**, assert the output correct.
	- Suppose 3 players go to the prison. Verify **prisonerLog** in **prison** will record prisoner’s **playerid** with **spy** method. **Don’t stub getLog function**.
	- Use **stub** method to test **getScore** function (PlayerID = your StudentID) to avoid connection to outer database.
	- Implement **paypalService** interface as a **fake** object to test donate function.
- Name your test function test_a to test_e which belong to each case.
- Upload StrangeGameTest.java to E3