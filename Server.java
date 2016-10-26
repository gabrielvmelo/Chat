package server;

import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

import streams.Reader;
import streams.Writer;

public class Server implements Runnable{

//------------------------------ ATRIBUTOS --------------------------------------------------
	//private String ipAdress;
	private String name;
	private int port;
	private JTextArea textArea;
	private JTextField textField;
	private Writer write;
	private Reader read;
	private Socket server;
	private ServerSocket servSocket;
	private Thread send;
	private Thread receive;
//--------------------------------------------------------------------------------------------

	public Server(int port, JTextArea textArea, JTextField textField, String name){
//-------------------------ATRIBUTOS-----------------------------------------------------------
		//this.ipAdress = ipAdress;
		this.port = port;
		this.textArea = textArea;
		this.textField = textField;
		this.name = name;
		this.send = null;
		this.receive = null;
		this.write = null;
		this.read = null;
	}//Construtor
//-----------------------------------------------------------------------------------------------

	public void run(){
		try{
			System.out.println("Server criado\n");// teste no console
			servSocket = new ServerSocket(port);
			server = servSocket.accept();
			write = new Writer(server, name, textField, textArea);
			read = new Reader(server, textArea);
			send = new Thread(write);
			receive = new Thread(read);
			send.start();
			receive.start();
		}//try
		catch(Exception e){
			e.printStackTrace();
		}//catch
	}//run


//---------------------------------Getters n Setters -------------------------------------------------

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Socket getServer() {
		return server;
	}
	public void setServer(Socket server) {
		this.server = server;
	}
	public ServerSocket getServSocket() {
		return servSocket;
	}
	public void setServSocket(ServerSocket servSocket) {
		this.servSocket = servSocket;
	}
	public Thread getSend() {
		return send;
	}
	public void setSend(Thread send) {
		this.send = send;
	}
	public Thread getReceive() {
		return receive;
	}
	public void setReceive(Thread receive) {
		this.receive = receive;
	}


}
