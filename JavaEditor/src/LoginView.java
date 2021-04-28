
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class LoginView extends JFrame{
    private MainProcess main;
    private TestFrm testFrm;
   
    private JButton btnLogin;
    private JButton btnInit;
    private JPasswordField passText;
    private JTextField userText;
    private boolean bLoginCheck;
   
    public static void main(String[] args) {
    
    }
 
    public LoginView() {
       
        setTitle("보안된 메모장");
        setSize(280, 150);
        setResizable(false);
        setLocation(800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        
        JPanel panel = new JPanel();
        placeLoginPanel(panel);
       
       
        
        add(panel);
       
      
        setVisible(true);
    }
   
    public void placeLoginPanel(JPanel panel){
        panel.setLayout(null);     
        JLabel userLabel = new JLabel("ID");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);
       
        JLabel passLabel = new JLabel("PW");
        passLabel.setBounds(10, 40, 80, 25);
        panel.add(passLabel);
       
        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);
       
        passText = new JPasswordField(20);
        passText.setBounds(100, 40, 160, 25);
        panel.add(passText);
        passText.addActionListener(new ActionListener() {          
          
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();        
            }
        });
       
        btnInit = new JButton("초기화");
        btnInit.setBounds(10, 80, 100, 25);
        panel.add(btnInit);
        btnInit.addActionListener(new ActionListener() {
           //action e
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });
       
        btnLogin = new JButton("로그인");
        btnLogin.setBounds(160, 80, 100, 25);
        panel.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
    }
    
    public void isLoginCheck(){
        if(userText.getText().equals("1") && new String(passText.getPassword()).equals("1")){
            JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다.");
            bLoginCheck = true;
          
    		
            if(isLogin()){
                //main.showFrameTest(); 
            	 JOptionPane.showMessageDialog(null, "보안된 메모장에 \n오신 것을 환영합니다.");

         		EditorMain working = new EditorMain();
         		working.startState();
         	
}
        }else{
            JOptionPane.showMessageDialog(null, "로그인에 실패하셨습니다.");
        }
    }
 
   
  
    public void setMain(MainProcess main) {
        this.main = main;
    }
   
 
    public boolean isLogin() {     
        return bLoginCheck;
    }
 
}