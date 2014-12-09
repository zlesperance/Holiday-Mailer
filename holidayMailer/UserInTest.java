package holidayMailer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserInTest {

    private UserIn ui;

    @Before
    public void setUp() throws Exception {
        ui = new UserIn();
    }

    @After
    public void tearDown() throws Exception {
        ui = null;
    }

    @Test
    public void testValidateEMail() throws Exception {
        assertTrue(ui.validateEMail("Steven.Berg@eagles.ewu.edu"));
        assertTrue(ui.validateEMail("bobby@gmail.com"));
        assertFalse(ui.validateEMail("bob..@gmail.com"));
        assertFalse(ui.validateEMail("bob@gmail..com"));
    }

    @Test
    public void testValidateName() throws Exception {
        assertTrue(ui.validateName("Steven"));
        assertTrue(ui.validateName("Berg"));
        assertTrue(ui.validateName("O'hara"));
        assertTrue(ui.validateName("O'Doul"));
        assertTrue(ui.validateName("McDonald"));
        assertTrue(ui.validateName("La-a"));
        assertFalse(ui.validateName("Optimus Prime"));
        assertFalse(ui.validateName("Culvert856"));
        assertFalse(ui.validateName("#!"));
        assertFalse(ui.validateName(""));
        assertFalse(ui.validateName("@ndrew"));
    }

    @Test
    public void testValidateInt() throws Exception {
        assertTrue(ui.validateInt("1958"));
        assertTrue(ui.validateInt("1915"));
        assertTrue(ui.validateInt("2014"));
        assertFalse(ui.validateInt("10000000000000000000000000"));
        assertFalse(ui.validateInt("2015"));
        assertFalse(ui.validateInt("1914"));
    }
}