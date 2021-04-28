
  
import javax.swing.JFrame;
 
public class MainProcess{
    LoginView loginView;
    TestFrm testFrm;
   
    public static void main(String[] args) {
       
       
        MainProcess main = new MainProcess();
        main.loginView = new LoginView(); 
        main.loginView.setMain(main); 

		    }
   
    // 테스트
    public void showFrameTest(){
        loginView.dispose(); // 창닫기
        this.testFrm = new TestFrm(); 
    }
 
}
 