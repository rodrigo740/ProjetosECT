package lib;
import java.util.*;
//import org.antlr.v4.runtime.ParserRuleContext;

public class SymbolTable {

    HashMap<String, VariableSymbol> map;

    public SymbolTable() {
        map = new HashMap<>();
    }

    public void insert(String s, VariableSymbol var) {
        map.put(s, var);
    }

    public VariableSymbol getVar(String s) {
        return map.get(s);
    }

    public boolean containsVar(String s) {
        return map.containsKey(s);
    }
}