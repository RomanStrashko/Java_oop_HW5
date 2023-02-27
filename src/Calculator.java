public class Calculator {
    double x, y;


    public Calculator() {

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }



    public double summation(double x, double y){
        return x + y;
    }
    public double multiplication(double x, double y){
        return x * y;
    }
    public double subtraction(double x, double y){
        return x - y;
    }
    public double division(double x, double y){
        return x / y;
    }
}
