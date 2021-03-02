import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

import javax.swing.JOptionPane;

interface FunctionList {
	int askSaveNewFile();
	int askCoverFile();
	void newSave();
	void sameSave(File file);
	void openFile();
	void fileExit();
}

public class EditorFunction implements FunctionList, ActionListener {
	private EditorMain editor;
	private File file;
	private String content;
	private boolean openFlag = false; //openFlag: 파일을 열었는 열지 않았는지 여부 체크
	
	private FileDialog fd = new FileDialog(editor, "다른 이름으로 저장", FileDialog.SAVE);
	private FileDialog fr = new FileDialog(editor, "파일 열기", FileDialog.LOAD);

	public EditorFunction(EditorMain editor) {
		this.editor = editor;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == editor.getNewText()) {
			if(!openFlag && !editor.getTextEditor().getText().isEmpty()) {
				int option = askSaveNewFile();
				switch(option) {
				case JOptionPane.OK_OPTION:
					newSave();
					editor.getTextEditor().setText("");
					editor.setTitle("제목 없음 - 자바 메모장");
					break;
				case JOptionPane.NO_OPTION:
					editor.getTextEditor().setText("");
					break;
				}
			}
			else if(openFlag) {
				if(content.equals(editor.getTextEditor().getText())) {
					editor.getTextEditor().setText("");
					editor.setTitle("제목 없음 - 자바 메모장");
				} else {
					int option = askCoverFile();
					switch(option) {
					case JOptionPane.OK_OPTION:
						sameSave(this.file);
						editor.getTextEditor().setText("");
						editor.setTitle("제목 없음 - 자바 메모장");
						break;
					case JOptionPane.NO_OPTION:
						editor.getTextEditor().setText("");
						editor.setTitle("제목 없음 - 자바 메모장");
						break;
					}
				}
				openFlag = !openFlag;
			}
		}

		if (e.getSource() == editor.getOpenFile()) {
			if(!openFlag) {
				if(editor.getTextEditor().getText().isEmpty()) {
					openFile();
				} else {
					int option = askSaveNewFile();
					switch(option) {
					case JOptionPane.OK_OPTION:
						newSave();
						editor.getTextEditor().setText("");
						openFile();
						break;
					case JOptionPane.NO_OPTION:
						editor.getTextEditor().setText("");
						openFile();
						break;
					}
				}
			} else {
				if(editor.getTextEditor().getText().isEmpty()) {
					openFile();
				} else {
					if(content.equals(editor.getTextEditor().getText())) {
						openFile();
					} else {
						int option = askCoverFile();
						switch(option) {
						case JOptionPane.OK_OPTION:
							sameSave(this.file);
							openFile();
							break;
						case JOptionPane.NO_OPTION:
							openFile();
							break;
						}
					}
				}
			}
		}

		if (e.getSource() == editor.getSave()) {
			if(!openFlag && ((editor.getTextEditor().getText().isEmpty())||(!editor.getTextEditor().getText().isEmpty()))) {
				newSave();
			}
			else if(openFlag) {
				sameSave(this.file);
				content = editor.getTextEditor().getText();
			}
		}

		if (e.getSource() == editor.getNewNameSave()) {
			newSave();
		}

		if (e.getSource() == editor.getFileExit()) {
			fileExit();
		}
		
		if(e.getSource() == editor.getInformation()) {
			new EditorInformation();
		}
	}

	public int askSaveNewFile() {
		int option = JOptionPane.showConfirmDialog(editor, "이 메모장을 새로 저장하시겠습니까?");
		return option;
	}

	public int askCoverFile() {
		int option = JOptionPane.showConfirmDialog(editor, "이 메모장을 " + this.file.getAbsolutePath() + "위치에 저장하시겠습니까?");
		return option;
	}

	public void newSave() {
		fd.setFile("*.txt");
		fd.setVisible(true);

		if (fd.getFile() != null) {
			String path = fd.getDirectory() + fd.getFile();
			File thisFile = new File(path);
			this.file = thisFile;
			sameSave(thisFile);
			editor.setTitle(fd.getFile() + " - 메모장");
		}
	}

	public void sameSave(File file) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			String msg = editor.getTextEditor().getText().replaceAll("\n", "\r\n");

			writer.write(msg);
			writer.flush();
			writer.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public void openFile() {
		fr.setVisible(true);
		if(fr.getFile()!=null) {
			try {
				file = new File(fr.getDirectory()+fr.getFile());
				BufferedReader reader = new BufferedReader(new FileReader(file));
				editor.getTextEditor().setText("");
				String msg = "";
				while((msg = reader.readLine())!=null) {
					editor.getTextEditor().append(msg+"\n");
				}
				reader.close();
				
				content = editor.getTextEditor().getText();
				editor.setTitle(fr.getFile()+".txt - 메모장");
				
				if(openFlag == false) {
					openFlag = !openFlag;
				}
				
			} catch(IOException ie) {
				ie.printStackTrace();
			}
		}
	}
	
	public void fileExit() {
		if(!openFlag) {
			if(!editor.getTextEditor().getText().isEmpty()) {
				int option = askSaveNewFile();
				switch(option) {
				case JOptionPane.OK_OPTION:
					newSave();
					if(fd.getFile() != null) {
						System.exit(0);
					}
					break;
				case JOptionPane.NO_OPTION:
					System.exit(0);
				}
			} else {
				System.exit(0);
			}
		}
		else {
			if(content.equals(editor.getTextEditor().getText())) {
				System.exit(0);
			}
			else {
				int option = askCoverFile();
				switch(option) {
				case JOptionPane.OK_OPTION:
					sameSave(this.file);
					System.exit(0);
				case JOptionPane.NO_OPTION:
					System.exit(0);
				}
			}
		}
	}
	
}

