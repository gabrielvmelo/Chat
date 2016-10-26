package streams;

import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

public class Reader implements Runnable{
//------------------------------- ATRIBUTOS ------------------------------------------
	private Socket server;
	private JTextArea textArea;
	private BufferedReader buff;
	private String message;
	private boolean on;
//------------------------------------------------------------------------------------
	public Reader(Socket server, JTextArea textArea){
		this.server = server;
		this.textArea = textArea;
		this.buff = null;
		this.message = "";
		this.on = true;
	}//Construtor


	public void run(){
		try{
			System.out.println("Reader criado\n"); //teste no console
			buff = new BufferedReader(new InputStreamReader(this.server.getInputStream()));// Buffer de leitura

			while(on){

				if(Thread.currentThread().isInterrupted()){
					on = false;
				}//if

				message = buff.readLine(); // pega a mensagem que foi enviada pelo writer
				textArea.append(message + "\n"); // acrescenta a mensagem no campo textArea

				if(message.equalsIgnoreCase("exit")){ //condição para desligar a conexão
					textArea.append("Chat desligado\n");
					this.server.close();// fecha servidor
					Thread.currentThread().interrupt(); //interrompe a thread
				}//if
			}//while

		}//try
		catch(Exception e){
			e.printStackTrace();
		}//catch

	}//run

//-------------------------------- Getters n Setters -------------------------------------------------
	public Socket getServer() {
		return server;
	}


	public void setServer(Socket server) {
		this.server = server;
	}


	public JTextArea getTextArea() {
		return textArea;
	}


	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}


	public BufferedReader getBuff() {
		return buff;
	}


	public void setBuff(BufferedReader buff) {
		this.buff = buff;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public boolean isOn() {
		return on;
	}


	public void setOn(boolean on) {
		this.on = on;
	}


}
