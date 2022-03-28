package Project����;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


import Project����.Login.newaccount;

public class Client {
	static String name = "��" ;
	InputStream is; 
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	chatting board;
	Client(){}
	Client(String firstname) throws UnknownHostException, IOException{
		name = firstname;
		board = new chatting("client");
		board.ta.append("\n"+"�մ����� �����ϼ̽��ϴ�");
		board.click.addMouseListener(new Changeclient());
		board.tf.addKeyListener(new ClientEvent());
		String serverIp ="192.168.0.102"; //��� PC���� �ڱ� �ڽ��� ��Ÿ���� IP
		System.out.println("������ �������Դϴ�. ����IP :" + serverIp);
			Socket socket = new Socket(serverIp,7777);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			dos = new DataOutputStream(os);
			dis = new DataInputStream(is);
			while(socket.isConnected()) {
					board.ta.append(dis.readUTF());
			}
	}
	class ClientEvent implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyChar() == KeyEvent.VK_ENTER)
				try 
			{
					String text = board.tf.getText();
					dos.writeUTF("\n"+name + ": " +board.tf.getText());
					board.tf.setText("");
			} catch (IOException e) {
				e.printStackTrace();
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
	class Changeclient implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			String text = board.tn.getText();
			if(!text.equals("")) {
				try {
					dos.writeUTF("\n"+name + " ���� " + text + " �� �г����� ����Ǿ����ϴ�.");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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




