package Project수정;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import Database.AccountDB;

class Login extends Frame { 
	Boolean LoginFlag = false;
	Label lid; // ID 레이블
	Label lpwd; // PW 레이블
	TextField tfId; // ID 필드
	TextField tfPwd; // PW 필드
	Dialog dlg;
	Button dlgok;
	Label dlgmsg;
	Button ok; // 로그인 버튼
	Button account;
	AccountDB database;
	Login(String title) {
		super(title); // Frame(String title)을 호출한다.
		database = new AccountDB();
		database.createNewDatabase("account.db");
		// Components 생성
		lid = new Label("ID :", Label.RIGHT); 
		lpwd = new Label("Password :", Label.RIGHT); 
		tfId = new TextField(12);	
		tfPwd = new TextField(12);
		tfPwd.setEchoChar('*'); // 입력한 값 대신 '*'이 보이게 한다.
		dlg= new Dialog(this,"error");
		dlg.setSize(300, 150);
		dlg.setLocation(50,50);
		dlg.setLayout(new FlowLayout());
		dlgmsg = new Label("없는 아이디나 틀린 비밀번호를 입력하였습니다.", Label.CENTER);
		dlgok = new Button("OK");
		dlgok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dlg.dispose();                         
			}
		});         
		dlg.add(dlgmsg);
		dlg.add(dlgok);
		tfId.setBackground(Color.LIGHT_GRAY); // 배경색 지정
		tfPwd.setBackground(Color.LIGHT_GRAY); // 배경색 지정
		account=new Button("회원가입");
		ok = new Button("로그인");

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String id = tfId.getText();
				String password = tfPwd.getText();
				LoginFlag =database.select(id, password);
				if (LoginFlag==true ) {
					if(id.equals("admin")) {
						setVisible(false);//프레임 안보이게함
						dispose();//메모리에서 제거
						Thread3 th3 = new Thread3(id);
						th3.start();}

					else{
						setVisible(false);//프레임 안보이게함
						dispose();//메모리에서 제거
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
		add(ok); // 로그인 버튼
		add(account);
		setSize(500, 90); // 크기설정

		addWindowListener(new WindowHandler());//x버튼클릭시 프로그램종료
		setVisible(true); 
		account.addActionListener(new ActionListener() {//회원가입 클릭시 회원가입창 뜸
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new newaccount();

			}
		});

	}
	class newaccount extends Frame{
		Label lid; // ID 레이블
		Label lpwd; // PW 레이블
		TextField tfId; // ID 필드
		TextField tfPwd; // PW 필드
		Button ok; // 로그인 버튼
		Button account;
		Account client;
		String id ;
		String password ;

		newaccount(){
			setTitle("account");
			// Components 생성
			lid = new Label("ID :", Label.RIGHT); 
			lpwd = new Label("Password :", Label.RIGHT); 
			tfId = new TextField(12);	
			tfPwd = new TextField(12);
			tfPwd.setEchoChar('*'); // 입력한 값 대신 '*'이 보이게 한다.

			tfId.setBackground(Color.lightGray); // 배경색 지정
			tfPwd.setBackground(Color.lightGray); // 배경색 지정
			ok = new Button("가입완료"); 

			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					id = tfId.getText();
					password = tfPwd.getText();
					setVisible(false);//프레임 안보이게함
					dispose();//메모리에서 제거
					client = new Account(id,password);
					database.insert(client);
				}
			}); 

			setLayout(new FlowLayout()); // LayoutManager를 FlowLayout으로
			add(lid);	// ID Label // 생성한 Component들을 Frame에 포함시킨다.
			add(tfId); // ID field
			add(lpwd); // PW Label
			add(tfPwd); // PW field
			add(ok); // 로그인 버튼
			setSize(500, 90); // 크기설정
			setVisible(true); // Frame이 화면에 보이게 한다.
			addWindowListener(new WindowHandler2());//x버튼 클릭시 창닫고 메모리제거
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
