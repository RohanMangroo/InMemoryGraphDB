package Types;

public class StringWrapper implements TypeWrapper{
    private String value;

    public StringWrapper(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
