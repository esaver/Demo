package mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Basics {

    @Mock
    List<String> mockedList;

    @Spy
    List<String> spiedList = new ArrayList<>();

    @Test
    public void tesArrayList(){

        mockedList.add("test1");
        Mockito.verify(mockedList).add("test1");
        System.out.println(mockedList.get(0));
        assertEquals(0, mockedList.size());

        spiedList.add("one");
        Mockito.verify(spiedList).add("one");
        System.out.println(spiedList.get(0));
        assertEquals(1, spiedList.size());

        when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
    }

    @Test
    public void whenCreateMock_thenCreated() {
        List mockedList = Mockito.mock(ArrayList.class);
        mockedList.add("one");

        assertNull(mockedList.get(0));

        Mockito.verify(mockedList).add("one");
        assertEquals(0, mockedList.size());
    }

    @Test
    public void whenCreateSpy_thenCreated() {
        List spiedList = Mockito.spy(ArrayList.class);
        spiedList.add("one");

        assertEquals("one", spiedList.get(0));

        Mockito.verify(spiedList).add("one");
        assertEquals(1, spiedList.size());
    }
}
