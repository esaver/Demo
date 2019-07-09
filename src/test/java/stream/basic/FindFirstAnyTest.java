package stream.basic;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class FindFirstAnyTest {

    @Test
    public void createStream_whenFindFirstResultIsPresent_thenCorrect() {

        List<String> list = Arrays.asList("A", "B", "C", "D");

        Optional<String> result = list.stream().findFirst();

        assertTrue(result.isPresent());
        assertThat(result.get(), is("A"));
    }

    @Test
    public void testFinAny() {
        List<String> list = Arrays.asList("A", "B", "C", "D");

        Optional<String> result = list.stream().findAny();

        assertTrue(result.isPresent());
//        assertThat(result.get(), anyOf(is("A")));
        assertThat(result.get(), anyOf(is("A"), is("B"), is("C"), is("D")));
    }

    @Test
    public void createParallelStream_whenFindAnyResultIsNotFirst_thenCorrect() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> result = list
                .stream().parallel()
                .filter(num -> num < 4).findAny();

        assertTrue(result.isPresent());
        assertThat(result.get(), anyOf(is(1), is(2), is(3)));
    }

}