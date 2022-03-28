package Project수정;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import Project수정.Login.newaccount;


class chatting extends Frame{

	TextArea ta;
	TextField tf;
	TextField tn;
	TextField ts;
	Button click;
	Button sclick;
	Button open;
	String changename;
	chatting(String Title){
		super(Title);
		this.setSize(550,320);
		this.setBackground(Color.lightGray);
		this.setLayout(new FlowLayout());
		Panel upperPanel = new Panel(); 
		Panel lowerPanel = new Panel();
		Panel namePanel = new Panel();
		Panel searchPanel = new Panel();
		upperPanel.setBackground(Color.ORANGE);
		lowerPanel.setBackground(Color.pink);
		click = new Button("변경");
		sclick = new Button("검색");		
		open = new Button("오픈");
		tn = new TextField(10);
		ta = new TextArea("채팅을 시작합니다");
		tf = new TextField(60);
		ts = new TextField(10);
		ts.selectAll();
		tf.selectAll();
		tn.selectAll();
		addWindowListener(new WindowHandler());
		ta.setEditable(false);
		this.add(upperPanel);
		this.add(lowerPanel);
		this.add(namePanel);
		upperPanel.add(ta,"Center");
		lowerPanel.add(tf,"Center");
		namePanel.add(tn,"Center");
		namePanel.add(click);
		this.add(searchPanel);
		searchPanel.add(ts,"Center");
		searchPanel.add(sclick);
		searchPanel.add(open);
		sclick.addMouseListener(new searchlink());
		open.addActionListener(new open());
		this.setVisible(true);
	}
	class searchlink implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			search link = new search();
			try {
				link.linksearch(ts.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if((ts.getText()).equals("")) {
				ta.append("\n"+"검색할 단어를 입력해주세요");
			}
			else {
				ta.append("\n"+"ㅡㅡㅡㅡㅡㅡㅡㅡ연관검색어ㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				ts.setText("");
				ArrayList<String> what = link.getList();
				int i=0;
				for(String a : what) {
					ta.append("\n"+what.get(i));
					i++;
				}
				ta.append("\n"+"ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
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
	class open implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			search want = new search();
			String field = ts.getText();
			ta.append("\n"+"사이트 접속을 하겠습니다");
			want.open(field);
			ta.append("\n"+"사이트 접속에 성공하셨습니다");
		}
	}
}
