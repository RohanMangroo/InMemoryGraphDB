package Types;

public class IntegerWrapper implements TypeWrapper{
    private Integer value;

    public IntegerWrapper(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


}
