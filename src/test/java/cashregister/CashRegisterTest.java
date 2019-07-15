package cashregister;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CashRegisterTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    static void initTestEnv() {
    }

    @BeforeEach
    void initEveryMethod() {
        System.setOut(new PrintStream(outContent));
    }


    @Test
    public void should_print_the_real_purchase_when_call_process() {
        Item item1 = new Item("item1",1.0);
        Item item2 = new Item("item2",1.0);
        Item item3 = new Item("item3",1.0);
        Item item4 = new Item("item4",1.0);
        Item items[] = new Item[]{item1,item2,item3,item4};
        Printer printer = new Printer();
        CashRegister cashRegister = new CashRegister(printer);
        Purchase purchase = new Purchase(items);
        assertThrows(UnsupportedOperationException.class,()-> cashRegister.process(purchase),"Not Implemented");

    }

    @Test
    public void should_print_the_stub_purchase_when_call_process() {
        Item item1 = new Item("item1",1.0);
        Item item2 = new Item("item2",1.0);
        Item item3 = new Item("item3",1.0);
        Item item4 = new Item("item4",1.0);
        Item items[] = new Item[]{item1,item2,item3,item4};
        Printer printer = new Printer();
        CashRegister cashRegister = new CashRegister(printer);
        Purchase purchase = new Purchase(items);
        cashRegister.process(purchase);
        assertEquals(outContent.toString(),"item1\t1.0\nitem2\t1.0\nitem3\t1.0\nitem4\t1.0\n");
    }

    @Test
    public void should_verify_with_process_call_with_mockito() {
        Printer printer = mock(Printer.class);
        Purchase purchase = mock(Purchase.class);
        when(purchase.asString()).thenReturn("purchase");
        CashRegister cashRegister = new CashRegister(printer);
        cashRegister.process(purchase);
        verify(purchase).asString();
    }

}
