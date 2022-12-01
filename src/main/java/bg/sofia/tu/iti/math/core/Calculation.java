package bg.sofia.tu.iti.math.core;

public class Calculation{
    private final String description;
    private final double result;

    public Calculation(String description, double result){
        this.description = description;
        this.result      = result;
    }

    public String getDescription(){
        return description;
    }

    public double getResult(){
        return result;
    }
}
