package streams;

import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;


public class Writer implements Runnable {
	//-------------------------ATRIBUTOS -----------------------------------------------------------
	private Socket server;
	private String myName;
	private String message;
	private JTextField textField;
	private JTextArea textArea;
	private boolean on; // mantem a thread em loop
	private boolean canWrite; // funciona como um "mutex", travando e destravando a escrita
	private DataOutputStream out;

	//-----------------------------------------------------------------------------------------------
	public Writer(Socket server, String myname, JTextField textField, JTextArea textArea){
		this.server = server;
		this.myName = myname;
		this.textField = textField;
		this.textArea = textArea;
		this.on = true;
		this.canWrite = false;
		this.out = null;
		this.message = "";

	}//Construtor

	public void run(){
		try{
			System.out.println("Witer criado\n"); //teste para o console
			out = new DataOutputStream(server.getOutputStream()); //gerando Stream que será enviada

			/*while(on){

				if(Thread.currentThread().isInterrupted()){ // Condição para funcionamento do loop, quando a thread é interronpida sai
					on = false;
				}//if

				if(canWrite && on){ // se canWrite -> on, então é possível enviar a mensagem
					message = textField.getText(); // pegando a mensagem que será enviada no textField
					out.writeBytes(myName + ": " + message + "\n"); // escrevendo a mensagem com o DataOutputStream
					System.out.println(message + "\n"); // teste no console

					if(message.equalsIgnoreCase("exit")){ // caso seja digitado "exit", encerra o programa
						out.flush();
						out.close();
						server.close();
						Thread.currentThread().isInterrupted();
					}//if
				}//if
			}//while*/

		}
		catch(Exception e){
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}//catch
	}//run

	public void sendMessage(){
		try{
			message = textField.getText(); // pegando a mensagem que será enviada no textField
			out.writeBytes(myName + ": " + message + "\n"); // escrevendo a mensagem com o DataOutputStream
			System.out.println(message + "\n"); // teste no console
			textArea.append(myName + ": " + message + "\n");

			if(message.equalsIgnoreCase("exit")){ // caso seja digitado "exit", encerra o programa
				out.flush();
				out.close();
				server.close();
				Thread.currentThread().isInterrupted();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}




	//----------------------- Getters n Setters ------------------------------------------------------
	public Socket getServer() {
		return server;
	}

	public void setServer(Socket server) {
		this.server = server;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public boolean isCanWrite() {
		return canWrite;
	}

	public void setCanWrite(boolean canWrite) {
		this.canWrite = canWrite;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
	}



}
