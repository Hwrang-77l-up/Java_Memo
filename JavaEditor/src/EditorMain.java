import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.*;

@SuppressWarnings("serial")

    public class EditorMain extends JFrame {
	private JTextArea textEditor;
	private JMenu file;	
	private JMenuItem information;
	private JMenuBar text_menuBar;
	
	private JMenuItem newText;
	private JMenuItem openFile;
	private JMenuItem save;
	private JMenuItem newName_save;
	private JMenuItem fileExit;
	int x = 10;

	public void startState() {
		setTitle("���� ���� - �ڹ� �޸���");
		
		text_menuBar = new JMenuBar();
		textEditor = new JTextArea();
		
		JScrollPane text_scroll = new JScrollPane(textEditor);
		file = new JMenu("����");		
		information = new JMenuItem("�޸��� ����");
		
		newText = new JMenuItem("���� �����");
		openFile = new JMenuItem("����");
		save = new JMenuItem("����");
		newName_save = new JMenuItem("�ٸ� �̸����� ����");
		fileExit = new JMenuItem("������");
		
		file.add(newText);
		file.add(openFile);
		file.add(save);
		file.add(newName_save);
		file.addSeparator();
		file.add(fileExit);
		

		text_menuBar.add(file);
		text_menuBar.add(information);

		setLayout(new BorderLayout());
		setJMenuBar(text_menuBar);
		add(text_scroll);
		
		setBounds(100, 100, 500, 400);
		setVisible(true);
		addFunction();
}
	


	private void addFunction() {
		EditorFunction function = new EditorFunction(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				function.fileExit();
			}
		});
		newText.addActionListener(function);
		openFile.addActionListener(function);
		save.addActionListener(function);
		newName_save.addActionListener(function);
		fileExit.addActionListener(function);
		information.addActionListener(function);
		
	}
	
	public JTextArea getTextEditor() {
		return textEditor;
	}
	
	public JMenuItem getNewText() {
		return newText;
	}
	
	public JMenuItem getOpenFile() {
		return openFile;
	}
	
	public JMenuItem getSave() {
		return save;
	}
	
	public JMenuItem getNewNameSave() {
		return newName_save;
	}
	
	public JMenuItem getFileExit() {
		return fileExit;
	}
	
	public JMenuItem getInformation() {
		return information;
	}

}
