package mempress;

import com.google.common.base.Preconditions;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by Bartek on 2014-12-02.
 */
public class SmartListTest {
    private SmartList<SerializableClass> _testedList;
    private SerializableClass firstElement, secondElement, thirdElement;
    private List<SerializableClass> _serializableClasses;

    @Before
    public void initTest() {
        _testedList = SmartListBuilder.<SerializableClass>create().build();
        _testedList.add((firstElement = make(1)));
        _testedList.add((secondElement = make(2)));
        _testedList.add((thirdElement = make(3)));

        _serializableClasses = Arrays.asList(make(4), make(5), make(6));
    }


    @Test
    public void testRemove() {
        assertTrue(_testedList.remove(cloneSC(secondElement)));
        assertFalse(_testedList.remove(new SerializableClass(Integer.MIN_VALUE)));
    }

    @Test
    public void testAddAll() {
        assertTrue(_testedList.addAll(_serializableClasses));

        for(int i = 0; i < _serializableClasses.size(); ++i)
            assertTrue(_testedList.contains(_serializableClasses.get(i)));
    }

    @Test
    public void testAddAllInt() {
        assertTrue(_testedList.addAll(0, _serializableClasses));
        for(int i = 0; i < _serializableClasses.size(); ++i) {
            assertTrue(_serializableClasses.contains(_testedList.get(i)));
        }
    }

    @Test
    public void testAddSetRemove() {
        _testedList.add(1, make(4));
        SerializableClass serializableClass = _testedList.set(1, make(5));
        assertEquals(make(4), serializableClass);

        serializableClass = make(5);
        assertEquals(serializableClass, _testedList.get(1));

        assertEquals(serializableClass, _testedList.remove(1));

        assertTrue(_testedList.add(make(6)));
    }

    @Test
    public void testClearIsEmpty() {
        _testedList.clear();
        assertEquals(0, _testedList.size());

        try {
            _testedList.get(0);
            fail();
        } catch (IndexOutOfBoundsException e) {}

        assertTrue(_testedList.isEmpty());
    }

    @Test
    public void testContainsAll() {
        assertTrue(_testedList.addAll(_serializableClasses));
        assertTrue(_testedList.containsAll(_serializableClasses));
        _serializableClasses.add(make(9));
        assertFalse(_testedList.containsAll(_serializableClasses));

        try {
            _serializableClasses.containsAll(null);
            fail();
        } catch (NullPointerException e) {}

        _serializableClasses.clear();
        try {
            _testedList.containsAll(_serializableClasses);
            fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testIndexOf() {
        SerializableClass sc = make(2), sc2 = make(3);
        assertEquals(1, _testedList.lastIndexOf(sc));
        assertEquals(2, _testedList.indexOf(sc2));

    }

    @Test
    public void testToArray() {
        SerializableClass[] serializableClasses = _testedList.toArray(new SerializableClass[3]);
        Object[] objects = _testedList.toArray();

        assertArrayEquals(serializableClasses, objects);
    }


    private SerializableClass cloneSC(SerializableClass sc) {
        Preconditions.checkNotNull(sc);
        return new SerializableClass(sc.getNo());
    }

    private SerializableClass make(int n) {
        return new SerializableClass(n);
    }
}