package Project����;
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

import Project����.Login.newaccount;


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
		click = new Button("����");
		sclick = new Button("�˻�");		
		open = new Button("����");
		tn = new TextField(10);
		ta = new TextArea("ä���� �����մϴ�");
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
				ta.append("\n"+"�˻��� �ܾ �Է����ּ���");
			}
			else {
				ta.append("\n"+"�ѤѤѤѤѤѤѤѿ����˻���ѤѤѤѤѤѤѤѤ�");
				ts.setText("");
				ArrayList<String> what = link.getList();
				int i=0;
				for(String a : what) {
					ta.append("\n"+what.get(i));
					i++;
				}
				ta.append("\n"+"�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
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
			ta.append("\n"+"����Ʈ ������ �ϰڽ��ϴ�");
			want.open(field);
			ta.append("\n"+"����Ʈ ���ӿ� �����ϼ̽��ϴ�");
		}
	}
}
