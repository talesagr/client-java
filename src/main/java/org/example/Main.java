package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String serverIP = "192.168.0.110"; // Substituir pelo endereço IP do servidor
        int port = 1234;

        try {
            Socket socket = new Socket(serverIP, port);

            ObjectMapper objectMapper = new ObjectMapper();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Random random=new Random();

            InetAddress localhost = InetAddress.getLocalHost();
            String ip = localhost.getHostAddress();

            HashMap<String,Integer> dataToSend = new HashMap<>();
            int randomNumber = random.nextInt(101);

            dataToSend.put(ip,randomNumber);

            String json = objectMapper.writeValueAsString(dataToSend);

            out.println(json);

//            String answerJson = in.readLine();
//            System.out.println("Server answer: " + answerJson);

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }    }
}