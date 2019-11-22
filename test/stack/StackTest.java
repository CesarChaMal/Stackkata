package stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    private Stack stack;

    @Before
    public void setUp() throws Exception {
        stack = BoundedStack.make(2);
    }

    @Test
    public void newlyCreateStack_ShouldBeEmpty() throws Exception {
        assertTrue(stack.isEmpty());
        //assertEquals(Integer.valueOf(0), stack.getSize());
        assertEquals(0, stack.getSize().intValue());
    }

    @Test
    public void afterOnePush_StackSizeShouldBeOne() throws Exception {
        stack.push(1);
        assertEquals(1, stack.getSize().intValue());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void afterOnePop_ShouldBeEmpty() throws Exception {
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test(expected = BoundedStack.Overflow.class)
    public void whenPushedPastLimit_StackOverflows() throws Exception {
        stack.push(1);
        stack.push(1);
        stack.push(1);
    }

    @Test(expected = BoundedStack.Underflow.class)
    public void whenEmptyStackIsPopped_ShouldThrowUnderFlow() throws Exception {
        stack.pop();
    }

    @Test
    public void whenOneIsPushed_OneIsPopped() throws Exception {
        stack.push(1);
        assertEquals(1, stack.pop().intValue());
    }

    @Test
    public void whenOneAndTwoArePushed_TwoAndOneArePopped() throws Exception {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop().intValue());
        assertEquals(1, stack.pop().intValue());
    }

    @Test(expected = BoundedStack.IllegalCapacity.class)
    public void whenCreatingStackWithNegativeSize_ShouldThrowIllegalCapacity() throws Exception {
        BoundedStack.make(-1);
    }

    @Test(expected = BoundedStack.Overflow.class)
    public void whenCreatingStackWithZeroCapacity_AnyPushShouldOverflow() throws Exception {
        stack = BoundedStack.make(0);
        stack.push(1);
    }

    @Test
    public void whenOneIsPushed_OneIsOnTop() throws Exception {
        stack.push(1);
        assertEquals(1, stack.top().intValue());
    }

    @Test(expected = Stack.Empty.class)
    public void whenStackIsEmpty_TopThrowsEmpty() throws Exception {
        stack.top();
    }

    @Test(expected = Stack.Empty.class)
    public void withZeroCapacityStack_TopThrowsEmpty() throws Exception {
        stack = BoundedStack.make(0);
        stack.top();
    }

    @Test
    public void givenStackWithOneTwoPushed_FindOneAndTwo() throws Exception {
        stack.push(1);
        stack.push(2);
        assertEquals(1, stack.find(1).intValue());
        assertEquals(0, stack.find(2).intValue());
    }

    @Test
    public void givenStackWithNo2_Find2ShouldShouldReturnNull() throws Exception {
        assertNull(stack.find(2));
    }
}
