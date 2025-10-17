package shared;

import java.io.Serializable;

public class Op implements Serializable {
    private static final long serialVersionUID = 1L;

    private int o1;
    private int o2;
    private String opr;

    public Operation(int operand1, int operand2, String opr) {
        this.o1 = o1;
        this.o2 = o2;
        this.opr = opr;}

    public int getO1() { return o1; }
    public int getO2() { return o2; }
    public String getOperator() { return opr; }
}