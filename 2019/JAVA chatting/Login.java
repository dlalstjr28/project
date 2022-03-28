package Project����;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import Database.AccountDB;

class Login extends Frame { 
	Boolean LoginFlag = false;
	Label lid; // ID ���̺�
	Label lpwd; // PW ���̺�
	TextField tfId; // ID �ʵ�
	TextField tfPwd; // PW �ʵ�
	Dialog dlg;
	Button dlgok;
	Label dlgmsg;
	Button ok; // �α��� ��ư
	Button account;
	AccountDB database;
	Login(String title) {
		super(title); // Frame(String title)�� ȣ���Ѵ�.
		database = new AccountDB();
		database.createNewDatabase("account.db");
		// Components ����
		lid = new Label("ID :", Label.RIGHT); 
		lpwd = new Label("Password :", Label.RIGHT); 
		tfId = new TextField(12);	
		tfPwd = new TextField(12);
		tfPwd.setEchoChar('*'); // �Է��� �� ��� '*'�� ���̰� �Ѵ�.
		dlg= new Dialog(this,"error");
		dlg.setSize(300, 150);
		dlg.setLocation(50,50);
		dlg.setLayout(new FlowLayout());
		dlgmsg = new Label("���� ���̵� Ʋ�� ��й�ȣ�� �Է��Ͽ����ϴ�.", Label.CENTER);
		dlgok = new Button("OK");
		dlgok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dlg.dispose();                         
			}
		});         
		dlg.add(dlgmsg);
		dlg.add(dlgok);
		tfId.setBackground(Color.LIGHT_GRAY); // ���� ����
		tfPwd.setBackground(Color.LIGHT_GRAY); // ���� ����
		account=new Button("ȸ������");
		ok = new Button("�α���");

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String id = tfId.getText();
				String password = tfPwd.getText();
				LoginFlag =database.select(id, password);
				if (LoginFlag==true ) {
					if(id.equals("admin")) {
						setVisible(false);//������ �Ⱥ��̰���
						dispose();//�޸𸮿��� ����
						Thread3 th3 = new Thread3(id);
						th3.start();}

					else{
						setVisible(false);//������ �Ⱥ��̰���
						dispose();//�޸𸮿��� ����
						Thread4 th4 = new Thread4(id);
						th4.start();
					}
				}

				else {
					dlg.setVisible(true);
				}

			}}); 
		setLayout(new FlowLayout());
		add(lid);	// ID Label 
		add(tfId); // ID field
		add(lpwd); // PW Label
		add(tfPwd); // PW field
		add(ok); // �α��� ��ư
		add(account);
		setSize(500, 90); // ũ�⼳��

		addWindowListener(new WindowHandler());//x��ưŬ���� ���α׷�����
		setVisible(true); 
		account.addActionListener(new ActionListener() {//ȸ������ Ŭ���� ȸ������â ��
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new newaccount();

			}
		});

	}
	class newaccount extends Frame{
		Label lid; // ID ���̺�
		Label lpwd; // PW ���̺�
		TextField tfId; // ID �ʵ�
		TextField tfPwd; // PW �ʵ�
		Button ok; // �α��� ��ư
		Button account;
		Account client;
		String id ;
		String password ;

		newaccount(){
			setTitle("account");
			// Components ����
			lid = new Label("ID :", Label.RIGHT); 
			lpwd = new Label("Password :", Label.RIGHT); 
			tfId = new TextField(12);	
			tfPwd = new TextField(12);
			tfPwd.setEchoChar('*'); // �Է��� �� ��� '*'�� ���̰� �Ѵ�.

			tfId.setBackground(Color.lightGray); // ���� ����
			tfPwd.setBackground(Color.lightGray); // ���� ����
			ok = new Button("���ԿϷ�"); 

			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					id = tfId.getText();
					password = tfPwd.getText();
					setVisible(false);//������ �Ⱥ��̰���
					dispose();//�޸𸮿��� ����
					client = new Account(id,password);
					database.insert(client);
				}
			}); 

			setLayout(new FlowLayout()); // LayoutManager�� FlowLayout����
			add(lid);	// ID Label // ������ Component���� Frame�� ���Խ�Ų��.
			add(tfId); // ID field
			add(lpwd); // PW Label
			add(tfPwd); // PW field
			add(ok); // �α��� ��ư
			setSize(500, 90); // ũ�⼳��
			setVisible(true); // Frame�� ȭ�鿡 ���̰� �Ѵ�.
			addWindowListener(new WindowHandler2());//x��ư Ŭ���� â�ݰ� �޸�����
		}

	}
}

class Thread3 extends Thread{
    String name;
    Thread3(String name){
    	this.name=name;
    }
	@Override
	public void run() {
		try {
			new Server(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class Thread4 extends Thread{
	String name;
	Thread4(String name){
    	this.name=name;
    }
	@Override
	public void run() {
		try {
			new Client(name);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
