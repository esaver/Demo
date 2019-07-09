package mockito;

import java.util.AbstractList;

public class MyList1 extends AbstractList<String> {


    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public void add(int i, String str) {
        //no-op
    }

    @Override
    public int size() {
        return 0;
    }

    final public int finalMethod() {
        return 0;
    }
}
