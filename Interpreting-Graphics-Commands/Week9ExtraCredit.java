import java.util.HashMap;

public class Week9ExtraCredit {

    private HashMap<String, String> variables ;

    public Week9ExtraCredit() {
        variables = new HashMap<String, String>();
    }
    
    public void addVariable(String key, String value) {
        variables.put(key, value);
    }

    public String getVariable(String key) {
        return variables.get(key);
    }
}
