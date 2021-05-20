package a;

import static a.CarType.*;

public class Car {
    private String brand;
    private String model;
    private final Enum type;
    private int engineSize;
    private int horsePower;

    public Car(){
        this.brand = "unknown";
        this.model = "unknown";
        this.type = HATCHBACK;
        this.engineSize = 1;
        this.horsePower = 1;
    }

    public Car(String brand, String model, Enum type, int engineSize, int horsePower){
        this.brand = brand;
        this.model = model;
        this. type = type;
        this.engineSize = engineSize;
        this.horsePower = horsePower;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Enum getType() {
        return type;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    private double PowerEngineRatio(){
        return this.horsePower/this.engineSize;
    }

    public String drive(){
        return "Enjoy driving " + this.brand + " " + this.model;
    }

}
