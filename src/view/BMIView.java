package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vo.BMIVO;

public class BMIView extends JFrame {
	JTextField tfWeight, tfHeight;
	String bmiResult;
	JLabel lblResult;
	JLabel lblIcon;
	
	public BMIView() {
		add(new PanelAbove(),"North");
		add(new PanelBelow(),"Center");
		setTitle("BMI(Body Measure Index)");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(50,50);
		setSize(400,600);
		setVisible(true);
	}
	
	class PanelAbove extends JPanel{
		public PanelAbove() {
			setBackground(new Color(204,204,255));
			setLayout(new BorderLayout());
			JLabel lblTitle = new JLabel("체질량지수(BMI)", JLabel.CENTER); 
			JLabel lblWeight = new JLabel("체중: ");
			JLabel lblHeight = new JLabel("신장: ");
			JLabel lblkg = new JLabel("㎏");
			JLabel lblcm = new JLabel("㎝");
			JButton btnResult = new JButton("결과확인");
			tfWeight = new JTextField(15);
			tfHeight = new JTextField(15);
			add(lblTitle, "North");
			JPanel panCenter = new JPanel(new BorderLayout());
			
			JPanel[] pans = new JPanel[3];
			for (int i = 0; i < pans.length; i++) {
				pans[i] = new JPanel();
			}
			
			panCenter.add(pans[0], "North");
			panCenter.add(pans[1], "Center");
			pans[0].add(lblWeight); pans[0].add(tfWeight); pans[0].add(lblkg);
			pans[1].add(lblHeight); pans[1].add(tfHeight); pans[1].add(lblcm);
			pans[2].add(btnResult);
			
			add(panCenter, "Center");
			add(pans[2], "South");
		}
	}
	
	class PanelBelow extends JPanel{
		public PanelBelow() {
			setBackground(new Color(255,204,229));
			setLayout(new BorderLayout());
			lblResult = new JLabel("<html>당신의 체중은 _kg, 키는 _cm이므로<br>bmi지수는_kg이며,_체중입니다.</html>",JLabel.CENTER);
			Font font = new Font("맑은 고딕",Font.BOLD,20);
			lblResult.setFont(font);
			ImageIcon icon = new ImageIcon("images/bmi_original.jpg");
			JLabel lblIcon = new JLabel(icon, JLabel.CENTER);
			add(lblResult, "North");
			add(lblIcon, "Center");
		}
	}
	ActionListener btnL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			BMIVO vo = new BMIVO();
			double weight= Double.parseDouble(tfWeight.getText());
			double height= Double.parseDouble(tfHeight.getText());
			vo.setWeight(weight);
			vo.setHeight(height);
			vo.setBmi();
			String bmiResult="";
			ImageIcon icon = null;
			if(vo.getBmi()<18.5) {
				bmiResult="저";
				icon = new ImageIcon("images/under.jpg");
			}
			else if(vo.getBmi()<24.9) {
				bmiResult="정상";
				icon = new ImageIcon("images/normal.jpg");
			}
			else if(vo.getBmi()<9.9) {
				bmiResult="과";
				icon = new ImageIcon("images/over.jpg");
			}
			else if(vo.getBmi()<34.9) {
				bmiResult="비만";
				icon = new ImageIcon("images/obese.jpg");
			}
			else {
				bmiResult="고도비만";
				icon = new ImageIcon("images/extremely.jpg");
			}
			
			lblResult.setText("<html>당신의 체중은"+vo.getWeight()+"kg, 키는 "
			+vo.getHeight()+"cm이므로"+"<br>bmi지수는"+String.format("%.1f",vo.getBmi())+"kg이며,"+bmiResult+"체중입니다</html>");
			lblIcon.setIcon(icon);
		}
	};
	
}
