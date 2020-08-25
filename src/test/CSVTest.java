

import org.junit.*;
import static org.junit.Assert.*;

import csvloader.CSVLoader;

public class CSVTest {

    @Test
    public void testPrepareCell() {
        assertEquals(CSVLoader.prepareCell("TESTING'SINGLE'QOUTESmessingthingsup'yep"), "TESTING''SINGLE''QOUTESmessingthingsup''yep");
        String[] cells = CSVLoader.prepareCell(new String[]{"test'here", "'number'two'"});
        assertEquals(cells[0], "test''here");
        assertEquals(cells[1], "''number''two''");
    }


}
