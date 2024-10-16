package org.suai.protocol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.Random;

public class SchnorrTest {

    private Schnorr schnorr;

    @BeforeEach
    public void setUp() {
        schnorr = new Schnorr();
        schnorr.generateKeys(generateRandomPrivateKey()); // Clave privada generada dinámicamente
    }

    @Test
    public void testKeyGeneration() {
        BigInteger[] publicKeys = schnorr.getPublicKeys();
        assertNotNull(publicKeys, "Las claves públicas no deben ser nulas");
        assertEquals(4, publicKeys.length, "Debe haber 4 claves públicas");
        // Verifica que las claves públicas sean válidas
        assertNotNull(publicKeys[0], "La clave pública p no debe ser nula");
        assertNotNull(publicKeys[1], "La clave pública q no debe ser nula");
        assertNotNull(publicKeys[2], "La clave pública g no debe ser nula");
        assertNotNull(publicKeys[3], "La clave pública y no debe ser nula");
        System.out.println("Exito: Generacion de claves exitosa");
    }

    @Test
    public void testCalculateX() {
        BigInteger r = generateRandomBigInteger(128);
        BigInteger X = schnorr.calculate_X(r);
        assertNotNull(X, "El valor X no debe ser nulo");
        // Verifica que X sea válido
        System.out.println("Exito: Calculo de X exitoso");
    }

    @Test
    public void testCalculateS() {
        BigInteger r = generateRandomBigInteger(128);
        BigInteger e = generateRandomBigInteger(128);
        BigInteger S = schnorr.calculate_S(r, e);
        assertNotNull(S, "El valor S no debe ser nulo");
        // Verifica que S sea válido
        System.out.println("Exito: Calculo de S exitoso");
    }

    @Test
    public void testVerification() {
        for (int i = 0; i < 10; i++) {
            BigInteger r = generateRandomBigInteger(128);
            BigInteger e = generateRandomBigInteger(128);
            BigInteger X = schnorr.calculate_X(r);
            BigInteger S = schnorr.calculate_S(r, e);

            // Verifica que la verificación sea exitosa
            assertTrue(schnorr.checkOut(X, S, e, false), "Fallo en el intento " + (i + 1) + ": Verificacion exitosa esperada");
            System.out.println("Exito en el intento " + (i + 1) + ": Verificacion exitosa");

            // Verifica que la verificación falle con valores incorrectos
            assertFalse(schnorr.checkOut(X, S.add(BigInteger.ONE), e, false), "Fallo en el intento " + (i + 1) + ": Verificacion fallida esperada");
            System.out.println(" Autenticacion con valores incorrectos fallida");
        }
    }

    // Método para generar una clave privada aleatoria
    private String generateRandomPrivateKey() {
        return BigInteger.probablePrime(128, new Random()).toString();
    }

    // Método para generar un BigInteger aleatorio de un tamaño específico
    private BigInteger generateRandomBigInteger(int bitLength) {
        return new BigInteger(bitLength, new Random());
    }
}