package Chapter3_1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private SimpleStack<String> operators;
    private SimpleStack<String> symbols;
    Calculator(){
        operators = new SimpleStack<>();
        symbols = new SimpleStack<>();
        symbols.push("#");
    }
    public void parenthesisMatching(String str){//匹配字符
    }
    //divide方法用来分隔字符串为数字和符号
    private String[] divide(String string){
        String[] temp_components = string.replace(" ","").split("");
        List<String> components = new ArrayList<>();
        for (int i=0;i<temp_components.length;i++){
            if (temp_components[i].matches("[0-9]")){
                StringBuilder numberCombiner = new StringBuilder();
                while (i<temp_components.length&&temp_components[i].matches("[0-9]")){
                    numberCombiner.append(temp_components[i]);
                    i++;
                }//跳出循环的时候i已经指向非数字符号
                i--;
                components.add(numberCombiner.toString());
            }
            else components.add(temp_components[i]);
        }
        return (String[])components.toArray(new String[0]);
    }
    public String ExpressionCalculation(String expression){
        float sum = 0;
        if (expression==null){
            return null;
        }
        String[] components = divide(expression);
        for (String i: components){
            if (!(i.matches("\\d+")||i.matches("[()*/+-]"))){
                System.out.println("Wrong Input");
                return null;
            }
            if (i.matches("[*/+-]")){

                String top = symbols.getTop();//栈顶元素都是权值最大的符号（除了括号）
                //栈顶元素小于读到的操作符，那么压栈,或者栈顶元素为"("
                if (Symbols.getValue(top)<Symbols.getValue(i)||top.equals("(")){
                    symbols.push(i);
                }
                //栈顶元素大于等于读到的操作符，那么弹出一直栈顶元素并且输出
                else {
                    String currenttop = top;
                    while (Symbols.getValue(currenttop)>=Symbols.getValue(i)){
                        System.out.print(currenttop);
                        compute();
                        symbols.pop();
                        currenttop = symbols.getTop();
                        if (currenttop.equals("(")){//如果读到了"(",只有遇到")"的时候才会弹出
                            break;
                        }
                    }
                    symbols.push(i);
                }
            }
            else if (i.matches("\\d+")){//如果匹配的是数字
                System.out.print(i);
                operators.push(i);
            }
            else if (i.matches("[(]")){
                symbols.push(i);
            }
            else if (i.matches("[)]")){
                String currentTop = symbols.getTop();
                while (!currentTop.equals("(")){
                    compute();
                    symbols.pop();
                    System.out.print(currentTop);
                    currentTop = symbols.getTop();
                }
                symbols.pop();//栈顶到左括号的时候，直接弹出
            }
        }
        //将剩下的操作数以及操作符进行运算，算完一个弹出一个，并且输出
        while (!symbols.getTop().equals("#")){
            compute();
            System.out.print(symbols.pop());
        }
        System.out.println();
        return String.valueOf(operators.pop());
    }
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");//定义最后的值的小数位数，因为除法可能会带来小数
    private void compute(){//取栈顶进行运算 a?b,不是取出
        String sign = symbols.getTop();
        float b = Float.parseFloat(operators.pop());
        float a = Float.parseFloat(operators.pop());
        switch (sign){
            case "+":operators.push(String.valueOf(a+b));break;
            case "-":operators.push(String.valueOf(a-b));break;
            case "*":operators.push(decimalFormat.format(a*b));break;
            case "/":operators.push(decimalFormat.format(a/b));break;
        }
    }
}
