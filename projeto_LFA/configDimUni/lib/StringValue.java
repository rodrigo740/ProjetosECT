package lib;
public class StringValue extends Value {

    private String val;
    private static StringType type = new StringType();

    public StringValue(String var) {
        setStringValue(var);
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public void setStringValue(String val) {
        this.val = val;
    }

    @Override
    public String stringValue() {
        return val;
    }

    @Override
    public String toString(){
        return ""+val;
    }   
}
