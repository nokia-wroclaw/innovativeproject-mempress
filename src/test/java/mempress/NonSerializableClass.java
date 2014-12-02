package mempress;

import java.io.Serializable;

/**
 * Created by Bartek on 2014-11-28.
 */
public class NonSerializableClass implements Serializable {
    protected int no = 2;

    public NonSerializableClass() {}

    public NonSerializableClass(int n) {
        no = n;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}