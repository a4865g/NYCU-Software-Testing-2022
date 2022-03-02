package tw.edu.nycu.cs.softwaretesting.spring2022;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {
    private Vehicle vehicle;

    private static ArrayList<Vehicle> vehicles() {
        return new ArrayList<Vehicle>(Arrays.asList(new Vehicle(), new Vehicle(0, "north")));
    }

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle();
    }

    @AfterEach
    void tearDown() {
        vehicle = null;
    }

    @ParameterizedTest
    @MethodSource("vehicles")
    void testVehicle(Vehicle vehicle) {
        assertEquals(0, vehicle.getSpeed());
        assertEquals("north", vehicle.getDir());
    }

    @Test
    void testTotalVehicle() throws NoSuchFieldException, IllegalAccessException {
        // Java reflection, bad.
        Field field = Vehicle.class.getDeclaredField("numVehicle");
        field.setAccessible(true);
        field.setInt(null, 0);
        assertEquals(0, Vehicle.totalVehicle());
    }

    @Test
    void testFinalize() {
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        vehicle.finalize();
        assertEquals("finalize has been called", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    void testGetSpeed() {
        assertEquals(0, vehicle.getSpeed());
    }

    @Test
    void testSetSpeed() {
        vehicle.setSpeed(1);
        assertEquals(1, vehicle.getSpeed());
    }

    @Test
    void testGetDir() {
        assertEquals("north", vehicle.getDir());
    }

    @Test
    void testSetDir() {
        vehicle.setDir("south");
        assertEquals("south", vehicle.getDir());
    }
}