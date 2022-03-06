package tw.edu.nycu.cs.softwaretesting.spring2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StrangeGameTest {
    private static String playerId = "310551017";
    @Mock
    Hour hour; // 目的為改變輸出的變數
    @Mock
    GAMEDb gameDb;
    @Spy
    Prison prison; // 目的為干擾內部的行為，否則會真的 sleep 7 天

    // 由於本次 Lab 不可使用 @InjectMocks ，所以說會在 @BeforeEach setUp 去做更改物件的行為。
    private StrangeGame strangeGame;

    public static Collection inputBEnterGame() {
        return Arrays.asList(new Object[][]{
                {new Player(playerId, 1), 24, "Have a nice day!"},
                {new Player(), 24, "After a long period of punishment, the player can leave! :)"}
        });
    }

    public static Collection inputCToPrison() {
        return Arrays.asList(
                new Player(playerId + "-1", -1),
                new Player(playerId + "-2", -1),
                new Player(playerId + "-3", -1)
        );
    }

    public static Collection inputEDonate() {
        return Arrays.asList(new Object[][]{
                {true, "Thank you"},
                {false, "Some errors occurred"}
        });
    }

    @BeforeEach
    void setUp() {
        strangeGame = new StrangeGame();
        strangeGame.hour = hour;
        strangeGame.prison = prison;
        strangeGame.db = gameDb;
    }

    @Test
    void testAEnterGame() throws InterruptedException {
        when(hour.getHour()).thenReturn(0);
        assertEquals("The game is not yet open!", strangeGame.enterGame(new Player()));
    }

    @ParameterizedTest
    @MethodSource("inputBEnterGame")
    void testBEnterGame(Player iPlayer, int iHour, String eReturn) throws InterruptedException {
        when(hour.getHour()).thenReturn(iHour);
        lenient().doNothing().when(prison).imprisonment(iPlayer); // For testB when getReputation < 0
        assertEquals(eReturn, strangeGame.enterGame(iPlayer));
    }

    @ParameterizedTest
    @MethodSource("inputCToPrison")
    void testCToPrison(Player iPlayer) throws InterruptedException {
        doNothing().when(prison).imprisonment(iPlayer);
        prison.crime(iPlayer);
        assertEquals(iPlayer.getPlayerId(), prison.getLog().remove(0));
        verify(prison, times(1)).crime(iPlayer);
        verify(prison, times(1)).getLog();
    }

    @Test
    void testDGetScore() {
        when(gameDb.getScore(playerId)).thenReturn(999);
        assertEquals(999, strangeGame.getScore(playerId));
        verify(gameDb, times(1)).getScore(playerId);
    }

    @ParameterizedTest
    @MethodSource("inputEDonate")
    void testEDonate(boolean isHaveMoney, String eReturn) {
        assertEquals(eReturn, strangeGame.donate(new FakePaypalService(isHaveMoney)));
    }

    class FakePaypalService implements paypalService {

        private boolean isHaveMoney;

        public FakePaypalService(boolean isHaveMoney) {
            this.isHaveMoney = isHaveMoney;
        }

        @Override
        public String doDonate() {
            return isHaveMoney ? "Success" : "Unsuccessful";
        }
    }
}