package mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.AbstractList;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnswerMockTest {

//    @Rule
//    public VerificationCollector verificationCollector = MockitoJUnit.collector();

    @Before
    public void setUp(){

    }

    @Test
    public void test1() {

        MyList myListMock = mock(MyList.class);
        Mockito.doNothing().when(myListMock).add(Mockito.isA(Integer.class), Mockito.isA(String.class));
        myListMock.add(0, "");

        Mockito.verify(myListMock, Mockito.times(1)).add(0, "");

//        Assert.assertEquals(1, myListMock.size());
    }

    @Test
    public void testDoCallRealMethod() {

        MyList myListMock = mock(MyList.class);
        Mockito.doCallRealMethod().when(myListMock).add(Mockito.isA(Integer.class), Mockito.isA(String.class));
        myListMock.add(0, "real");

        Mockito.verify(myListMock, Mockito.times(1)).add(0, "real");

//        Assert.assertEquals(1, myListMock.size());
    }

    @Test
    public void testArgumentCapture() {
        MyList myListMock = mock(MyList.class);
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> valueCaptureInt = ArgumentCaptor.forClass(Integer.class);
        Mockito.doNothing().when(myListMock).add(valueCaptureInt.capture(), valueCapture.capture());
        myListMock.add(0, "captured");

        Mockito.verify(myListMock, Mockito.times(1)).add(0, "captured");

        Assert.assertEquals(new Integer(0), valueCaptureInt.getValue());
        Assert.assertEquals("captured", valueCapture.getValue());
    }

    @Test
    public void whenMockFinalMethodMockWorks() {

        MyList1 myList1 = new MyList1();

        MyList1 mock1 = mock(MyList1.class);
        when(mock1.finalMethod()).thenReturn(1);

        assertNotEquals(mock1.finalMethod(), myList1.finalMethod());
    }

    public class MyList extends AbstractList<String> {

        @Override
        public void add(int index, String element) {
            //no-op
        }

        @Override
        public String get(int index) {
            return null;
        }

        @Override
        public int size() {
            return 1;
        }

        final public int finalMethod() {
            return 0;
        }
    }

    public final class FinalList extends MyList {

        @Override
        public int size() {
            return 1;
        }
    }

}
