package br.com.fiap.cp5.criptografialocalrsa;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RSA {

    public static void main(String[] args) {
        // Primos escolhidos (apenas para estudo)
        BigInteger p = BigInteger.valueOf(277); // seu primo pedido
        BigInteger q = BigInteger.valueOf(331); // outro primo pequeno para o exemplo

        // Calcula n = p * q e phi = (p-1)*(q-1)
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Exponente público (comum)
        BigInteger e = BigInteger.valueOf(65537);
        if (!e.gcd(phi).equals(BigInteger.ONE)) {
            // Se 65537 não for coprimo com phi, escolher outro e (este caso não ocorre aqui)
            e = BigInteger.valueOf(3);
            while (!e.gcd(phi).equals(BigInteger.ONE)) {
                e = e.add(BigInteger.TWO);
            }
        }

        // Calcula d = e^(-1) mod phi
        BigInteger d = e.modInverse(phi);

        System.out.println("p = " + p);
        System.out.println("q = " + q);
        System.out.println("n = p * q = " + n);
        System.out.println("phi = " + phi);
        System.out.println("e (public) = " + e);
        System.out.println("d (private) = " + d);
        System.out.println();

        // Mensagem de exemplo
        String mensagem = "Olá, RSA com p=277!";
        System.out.println("Mensagem original: " + mensagem);

        // Converter a mensagem para número (BigInteger) - usando bytes UTF-8
        BigInteger mensagemNumero = new BigInteger(1, mensagem.getBytes(StandardCharsets.UTF_8));

        // OBS: Para RSA real com dados maiores precisa-se usar padding e dividir em blocos.
        if (mensagemNumero.compareTo(n) >= 0) {
            System.err.println("Erro: a representação numérica da mensagem é >= n. Use primos maiores ou divida em blocos.");
            return;
        }

        // Cifrar: c = m^e mod n
        BigInteger cifrado = mensagemNumero.modPow(e, n);

        // Para exibir de forma legível, codificamos em Base64 o array de bytes do BigInteger
        String cifradoBase64 = Base64.getEncoder().encodeToString(cifrado.toByteArray());
        System.out.println("Mensagem cifrada (Base64): " + cifradoBase64);

        // Decifrar: m = c^d mod n
        BigInteger decifradoNumero = cifrado.modPow(d, n);

        // Converter de volta para bytes e string
        byte[] decifradoBytes = decifradoNumero.toByteArray();
        // BigInteger pode adicionar um byte 0 no início para sinal -- corrigimos:
        if (decifradoBytes.length > 1 && decifradoBytes[0] == 0) {
            byte[] tmp = new byte[decifradoBytes.length - 1];
            System.arraycopy(decifradoBytes, 1, tmp, 0, tmp.length);
            decifradoBytes = tmp;
        }
        String mensagemDecifrada = new String(decifradoBytes, StandardCharsets.UTF_8);
        System.out.println("Mensagem decifrada: " + mensagemDecifrada);

        // Verificação simples
        System.out.println("Decifração correta? " + mensagem.equals(mensagemDecifrada));
    }
}