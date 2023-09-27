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
        if(args == null || args.length != 2) throw new IllegalArgumentException("passe o IP e a PORTA do server");

        try (Socket socket = new Socket(args[0], Integer.parseInt(args[1]))){

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

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }    }
}