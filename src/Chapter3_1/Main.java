package Chapter3_1;

public class Main {
    public static void main(String []args) {
        String a = "12 * 4 +34/5-(56+67*4)+32";
        Calculator calculator = new Calculator();
        System.out.println(calculator.ExpressionCalculation(a));
        //YangHui.print(10);
    }
}
