package UserTests;

import Entities.User.User;
import UseCase.Security.Security;
import UseCase.User.CreateUser;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserCreationTests {

    String demoLogin;
    String demoPassword;
    String demoName;

    @Before
    public void setUp(){
        demoName = "DemoName";
        demoLogin = "DemoLogin";
        demoPassword = "DemoPass";
    }

    /**
     * Tests that CreateUser does not create the user with the password in plaintext.
     *
     * CreateUser hashes the input password and then creates a User Instance using it as opposed to saving the plaintext
     * password.
     *
     */
    @Test
    public void testUserNotPlaintextPass(){
        User user = new CreateUser().create(demoName, demoLogin, demoPassword);

        assertNotEquals(user.getHashedPassword(), demoPassword);
    }

    /**
     * Tests that CreateUser correctly instantiates a User Object.
     *
     * Also tests User.matchPassword();
     */
    @Test
    public void testCreateUserUserCreation(){
        User user = new CreateUser().create(demoName, demoLogin, demoPassword);

        byte[] salt = Security.fromHex(user.getSalt());
        String hashedPass = Security.toHex(Security.generateHash(demoPassword, salt));

        assertEquals(user.getAccountName(), demoName);
        assertEquals(user.getLogin(), demoLogin);
        assertEquals(user.getHashedPassword(), hashedPass);
        assertTrue(user.matchPassword(hashedPass));
    }
}
