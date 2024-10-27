import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        RSA rsa = new RSA();
        rsa.saveKeys();

        // Массив тестовых сообщений
        String[] testMessages1 = {
                "This is a test message 1",
                "This is a test message 2",
                "This is a test message 3",
                "This is a test message 4",
                "This is a test message 5"
        };
        String[] testMessages2 = {
                "This is a test message 6",
                "This is a test message 7",
                "This is a test message 8",
                "This is a test message 9",
                "This is a test message 10"
        };


        // Запуск тестов для каждого сообщения
        for (String originalMessage : testMessages1) {
            runTest(originalMessage, rsa);
        }

        // Для загрузки ключей и тестирования второго экземпляра RSA
        RSA rsa2 = RSA.loadKeys();
        for (String originalMessage : testMessages2) {
            runTest(originalMessage, rsa2);
        }
    }

    private static void runTest(String originalMessage, RSA rsa) throws IOException {
        System.out.println("Testing with message: " + originalMessage);

        // Преобразование сообщения в BigInteger
        BigInteger message = new BigInteger(originalMessage.getBytes());

        // Шифрование
        BigInteger encryptedMessage = rsa.encrypt(message);
        Files.write(Paths.get("encrypted_message.txt"), encryptedMessage.toString().getBytes());
        System.out.println("Encrypted message: " + encryptedMessage);

        // Расшифрование
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Decrypted message: " + new String(decryptedMessage.toByteArray()));

        // Подпись сообщения
        BigInteger signature = rsa.sign(message);
        Files.write(Paths.get("signature.txt"), signature.toString().getBytes());
        System.out.println("Message signed: " + signature);

        // Проверка подписи
        boolean isValid = rsa.verify(message, signature);
        System.out.println("Signature is valid: " + isValid);
        System.out.println();
    }
}
