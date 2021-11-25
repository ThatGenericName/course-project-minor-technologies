package com.minortechnologies.workr_backend.SecurityTests;

import com.minortechnologies.workr_backend.entities.user.User;
import com.minortechnologies.workr_backend.usecase.security.Security;
import com.minortechnologies.workr_backend.usecase.factories.userfactory.CreateUser;
import org.junit.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class SecurityTest {


    String hashString1;
    String hashString2;
    byte[] salt1;
    byte[] salt2;

    @Before
    public void setUp(){
        hashString1 = "TestString";
        hashString2 = "TestString";
        salt1 = Security.generateSalt();
        salt2 = Security.generateSalt();
    }

    @After
    public void tearDown() {
    }

    /**
     * tests that equal inputs generates the same hash (when salt is not used)
     *
     */
    @Test
    public void repeatableHashResults() {
        try {
            byte[] hash1 = Security.generateHash(hashString1);
            byte[] hash2 = Security.generateHash(hashString2);
            assertArrayEquals(hash1, hash2);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            fail();
        }
    }

    /**
     * Tests that hashing the same value with different salts results in different hashes
     */
    @Test
    public void hashWithSaltDifferent(){
        byte[] hash1 = Security.generateHash(hashString1, salt1);
        byte[] hash2 = Security.generateHash(hashString1, salt2);
        assert hash1 != null;
        assert hash2 != null;
        assertEquals(hash1.length, hash2.length);
        int equalBytes = 0;
        for (int i = 0; i < hash1.length; i++) {
            if (hash1[i] == hash2[i]){
                equalBytes++;
            }
        }
        assertNotEquals(equalBytes, hash1.length);
    }

    /**
     * Tests that toHex and fromHex produces reversible results (byte arrays can be converted back and forth)
     */
    @Test
    public void hexReversible(){
        String hex = Security.toHex(salt1);
        byte[] fHex = Security.fromHex(hex);

        assertArrayEquals(fHex, salt1);
    }

    /**
     * Tests that authenticate correctly authenticates a password.
     *
     */
    @Test
    public void testAuthenticate(){
        User user = new CreateUser().create("Demo", "Demo", "DemoEmail", "DemoPassword");
        boolean authRes1 = Security.authenticate("DemoPassword", user);
        boolean authRes2 = Security.authenticate("Demopassword", user);
        boolean authRes3 = Security.authenticate("IncorrectPass", user);

        assertTrue(authRes1);
        assertFalse(authRes2);
        assertFalse(authRes3);
    }


}
