package mockito;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MathUtilsTest {

    @Mock
    private MathUtils mathUtils;

    @Captor
    private ArgumentCaptor<Integer> inCaptor2;

    @Captor
    private ArgumentCaptor<String> strCaptor2;

    @Captor
    private ArgumentCaptor<Long> acLong;


    @Test
    public void testCaptor1(){
//        MockitoAnnotations.initMocks(this); // can skip this if using @RunWith(MockitoJUnitRunner.class)

        Mockito.when(mathUtils.add(1,2)).thenReturn(2);
        Mockito.when(mathUtils.isInteger(Mockito.anyString())).thenReturn(true);

        final ArgumentCaptor<Integer> inCaptor = ArgumentCaptor.forClass(Integer.class);
        final ArgumentCaptor<String> strCaptor = ArgumentCaptor.forClass(String.class);

        assertEquals(2, mathUtils.add(1,2));
        assertTrue(mathUtils.isInteger("1"));
        assertTrue(mathUtils.isInteger("999"));

        Mockito.verify(mathUtils).add(inCaptor.capture(), inCaptor.capture());
        final List<Integer> allValues = inCaptor.getAllValues();
        assertThat(Arrays.asList(1,2), Matchers.equalTo(allValues));

        //==========
        Mockito.verify(mathUtils, Mockito.times(2)).isInteger(strCaptor.capture());
        final List<String> stringList = strCaptor.getAllValues();
        assertThat(Arrays.asList("1", "999"), Matchers.equalTo(stringList));
    }

    @Test
    public void testCaptor2(){

        Mockito.when(mathUtils.add(1,2)).thenReturn(2);
        Mockito.when(mathUtils.isInteger(Mockito.anyString())).thenReturn(true);

        assertEquals(2, mathUtils.add(1,2));
        assertTrue(mathUtils.isInteger("1"));
        assertTrue(mathUtils.isInteger("999"));

        Mockito.verify(mathUtils).add(inCaptor2.capture(), inCaptor2.capture());
        final List<Integer> allValues = inCaptor2.getAllValues();
        assertThat(Arrays.asList(1,2), Matchers.equalTo(allValues));

        // verify captured string parameters
        Mockito.verify(mathUtils, Mockito.times(2)).isInteger(strCaptor2.capture());
        final List<String> stringList = strCaptor2.getAllValues();
        assertThat(Arrays.asList("1", "999"), Matchers.equalTo(stringList));
    }

    @Test
    public void test3(){
        Mockito.when(mathUtils.squareLong(2l)).thenReturn(4l);

        //assert method return value
        assertEquals(4l , mathUtils.squareLong(2l));

        //verify call
        Mockito.verify(mathUtils).squareLong(acLong.capture());
        //assert argument passed
        assertThat(acLong.getValue(), Matchers.equalTo(2l));
    }
}