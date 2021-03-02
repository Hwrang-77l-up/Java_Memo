import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EditorInformation extends JDialog implements ActionListener{
	
	private JButton confirm;
	
	public EditorInformation() {
		super(new EditorMain(), "메모장 정보", true);
		
		JPanel introduction = new JPanel();
		JLabel maker = new JLabel("<html> 간단하게 만들어본 자바 메모장 <br> 제작자: KDW <br> 2021-03-02 <br> License is free<br>");
		maker.setFont(new Font("Serif", Font.BOLD, 15));
		introduction.add(maker);
		
		confirm = new JButton("확인");
		
		setLayout(null);
		setResizable(false);
		introduction.setBounds(50, 40, 250, 150);
		confirm.setBounds(150, 170, 100, 50);
		
		confirm.addActionListener(this);
		add(introduction);
		add(confirm);
		
		setBounds(new EditorMain().getX()+50, new EditorMain().getY()+50, 400, 300);
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confirm) {
			dispose();
		}
	}
}
