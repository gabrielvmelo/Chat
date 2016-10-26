package client;

import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

import streams.Reader;
import streams.Writer;

public class Client implements Runnable{
//------------------------------------ATRIBUTOS------------------------------------------------------------------
	private String ipAdress;
	private String name;
	private int port;
	private JTextArea textArea;
	private JTextField textField;
	private Writer write;
	private Reader read;
	private Socket client;
	private Thread send;
	private Thread receive;
//----------------------------------------------------------------------------------------------------------------

	public Client(String ipAdress, int port, JTextArea textArea, JTextField textField, String name){
		this.ipAdress = ipAdress;
		this.port = port;
		this.textArea = textArea;
		this.textField = textField;
		this.name = name;
		this.send = null;
		this.receive = null;
		this.write = null;
		this.read = null;
	}//Construtor

	public void run(){
		try{
			System.out.println("Client criado\n");
			client = new Socket(ipAdress, port);
			write = new Writer(client, name, textField, textArea);
			read = new Reader(client, textArea);
			send = new Thread(write);
			receive = new Thread(read);
			send.start();
			receive.start();
		}//try
		catch(Exception e){
			e.printStackTrace();
		}//catch

	}//run




	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public Writer getWrite() {
		return write;
	}

	public void setWrite(Writer write) {
		this.write = write;
	}

	public Reader getRead() {
		return read;
	}

	public void setRead(Reader read) {
		this.read = read;
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}



}
