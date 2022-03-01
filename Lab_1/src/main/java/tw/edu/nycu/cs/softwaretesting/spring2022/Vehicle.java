package tw.edu.nycu.cs.softwaretesting.spring2022;

class Vehicle {
    private static int numVehicle = 0; // Class Variable
    private int speed; // Object Variable
    private String direction; // Object Variable, direction is a reference to String Object

    public Vehicle() { // Constructor, called when new an Object
        this(0, "north"); // call another constructor to do initialization
    }

    public Vehicle(int s, String dir) { // Another Constructor. Use overloading to define two constructors
        float speed; // define a local variable
        speed = s; // the speed here refers to the above local variable
        this.speed = s; // If we want to set object variable, use this.speed to refer object variable speed
        direction = dir; // dir is a reference to object, not the object itself
        numVehicle++;   // increase the Vehicle number
    }

    public static int totalVehicle() { // Class Method
        return numVehicle;
    }

    protected void finalize() { // Destructor, called when the object is garbage collected by JVM
        System.out.println("finalize has been called");
        numVehicle--;
    }

    int getSpeed() { // Object Method
        return speed;
    }

    void setSpeed(int newSpeed) { // Object Method
        this.speed = newSpeed;
    }

    String getDir() { // Object Method
        return direction;
    }

    void setDir(String dir) { // Object Method
        this.direction = dir;
    }
}