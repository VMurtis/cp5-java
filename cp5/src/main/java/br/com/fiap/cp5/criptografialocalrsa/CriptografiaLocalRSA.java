package br.com.fiap.cp5.criptografialocalrsa;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class CriptografiaLocalRSA {
    public static KeyPair gerarChavesPublicoPrivada() throws NoSuchAlgorithmException {
        KeyPairGenerator geradorChave = KeyPairGenerator.getInstance("RSA");
        geradorChave.initialize(2048); //inicializando em 2048 bits
        KeyPair par = geradorChave.generateKeyPair();
        return par;
    }

    public static String
    cifrar(String mensagem, PublicKey publicKey) throws Exception {

        byte[] messageToBytes = mensagem.getBytes();
        Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");


        cifrador.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytesCripto = cifrador.doFinal(messageToBytes);

        return Base64.getEncoder().encodeToString(bytesCripto);
    }

    public static String
    decifrar(String mensagem, PrivateKey privateKey) throws Exception {


        byte[] bytesCifrados = Base64.getDecoder().decode(mensagem);
        Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");


        cifrador.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] mensagemDecifrada = cifrador.doFinal(bytesCifrados);


        return new String(mensagemDecifrada, "UTF8");
    }


    public static PublicKey bytesParaChave(byte[] bytesChave) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytesChave);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            KeyPairGenerator geradorChave = KeyPairGenerator.getInstance("RSA");
            geradorChave.initialize(2048); // Iniciamos uma chave de 2048 bits
            KeyPair par = geradorChave.generateKeyPair(); // Gera um par chave pública e privada

            System.out.println("Digite a mensagem secreta:");
            String segredo = sc.nextLine();

            PrivateKey privateKey = par.getPrivate();
            PublicKey publicKey = par.getPublic();

            System.out.println(publicKey);
            System.out.println(privateKey);

            try {
                String mensagemCifrada = CriptografiaLocalRSA.cifrar(segredo, publicKey);
                System.out.println("Essa é a mensagem cifrada:\n" + mensagemCifrada);

                String mensagemDecifrada = CriptografiaLocalRSA.decifrar(mensagemCifrada, privateKey);
                System.out.println("A mensagem: " + mensagemDecifrada + " foi decifrada com sucesso.");
            } catch (Exception e) {
                System.err.println(e.getMessage());

            } finally {
                sc.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}