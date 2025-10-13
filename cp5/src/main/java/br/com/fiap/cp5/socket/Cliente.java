package br.com.fiap.cp5.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {

        try (
                Socket conexao = new Socket("127.0.0.1", 55000);
                DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
                DataInputStream entrada = new DataInputStream(conexao.getInputStream())
        ) {
            saida.writeUTF("oi");

            String resposta = entrada.readUTF();
            System.out.println(resposta);

        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
