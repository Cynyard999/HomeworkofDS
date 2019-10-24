package Chapter3_1;

public enum Symbols {
    POUND(0,"#"),
    PLUS(1,"+"),
    MINUS(1,"-"),
    MUL(2,"*"),
    DIVI(2,"/"),
    LPAREN(3,"("),
    RPAREN(3,")");
    private int value;
    private Symbols(int i,String j){
        this.value = i;
    }

    public static int getValue(String a) {
        switch (a){
            case "+":return PLUS.value;
            case "-":return MINUS.value;
            case "*":return MUL.value;
            case "/":return DIVI.value;
            case "#":return POUND.value;
            default:return 3;
        }
    }
}
