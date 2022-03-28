package Project����;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	static String name = "��";
	chatting board;
	ArrayList<Socket> socketlist;
	Server(){}	
	Server(String firstname) throws IOException{
		name = firstname;
		board = new chatting("server");
		board.ta.append("\n"+"������(����)�� �α��� �ϼ̽��ϴ�");
		board.tf.addKeyListener(new ActionServer());
		board.click.addMouseListener(new Changeserver());
		ServerSocket serverSocket = new ServerSocket(7777);
		socketlist = new ArrayList<Socket>();
		while(!serverSocket.isClosed()) {
			Socket socket=serverSocket.accept();
			socketlist.add(socket);	
			board.ta.append("\n"+"���� "+socketlist.size() + " ���� �����ڰ� �ֽ��ϴ�");
			Thread5 th5 = new Thread5(socket);
			th5.start();
		} 
	}
	class Thread5 extends Thread{
		Socket socket ;
		Thread5(Socket socket){
			this.socket = socket;
		}
		public void run() {
			try {
				InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				while(socket.isConnected()) {
					serversend send = new serversend(dis.readUTF());
					send.start();
				}
			}
			catch(IOException e) {}
		}
	}
	class serversend extends Thread{
		String text;
		serversend(String text){
			this.text = text;
		}
		public void run() {
			board.ta.append(text);
			for(Socket transport : socketlist) { 
				try {
					OutputStream os = transport.getOutputStream();
					DataOutputStream dos = new DataOutputStream(os);
					dos.writeUTF(text);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	class ActionServer implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyChar() == KeyEvent.VK_ENTER) {
				try 
				{		
					String text = board.tf.getText();
					for(Socket transport : socketlist) { 
						OutputStream os = transport.getOutputStream();
						DataOutputStream dos = new DataOutputStream(os);
						dos.writeUTF("\n"+name+ " : " +text);
					}
					board.ta.append("\n"+name + ": "+text);
					board.tf.setText("");
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
	class Changeserver implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			String text = board.tn.getText();
			if(!text.equals("")) {
				try {
					for(Socket transport : socketlist) { 
						OutputStream os = transport.getOutputStream();
						DataOutputStream dos = new DataOutputStream(os);
						dos.writeUTF("\n"+"������(����)�� �̸��� "+text+" �� ����Ǿ����ϴ�");
					}
					board.ta.append("\n"+"������(����)�� �̸��� "+text+" �� ����Ǿ����ϴ�");
				}
				catch(IOException e1) {}
				name = text;
				board.tn.setText("");
			}
			else {
				board.ta.append("\n" + "�г����� �Է� ���ּ���!");
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}

