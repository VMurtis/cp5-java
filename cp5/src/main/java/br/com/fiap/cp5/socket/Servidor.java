package br.com.fiap.cp5.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {


        try (ServerSocket servidor = new ServerSocket(55000);
             Socket conexao = servidor.accept();
             DataInputStream entrada = new DataInputStream(conexao.getInputStream());
             DataOutputStream saida = new DataOutputStream(conexao.getOutputStream())){
            {
                String mensagem = entrada.readUTF();

                String resultado = "Não recebo msg";

                if(mensagem.equalsIgnoreCase("oi")){
                    resultado = "recebido pelo servidor";
                }

                saida.writeUTF(resultado);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
