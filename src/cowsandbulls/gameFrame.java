/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cowsandbulls;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
/**
 *
 * @author Karthik Perumal
 */


public class gameFrame extends javax.swing.JFrame {

    /**
     * Creates new form gameFrame
     */
    GridLayout GL;
    public static gameFrame maingame;
    public static int bullflag;
    public static int n;
    public static int timerflag=0;
    public static int counterflag=0;
    public static int rowct=10;
    public gameFrame() {
        initComponents();
    }
    
   public class GamePanel extends JPanel {
       private JLabel CowCount;
    private JLabel BullCount;
    private JButton CheckB;
    private JTextField GameText;
       public GamePanel()
       {
           setVisible(true);
           setBackground(new java.awt.Color(204, 255, 204));
           GameText = new javax.swing.JTextField();
        CowCount = new javax.swing.JLabel();
        BullCount = new javax.swing.JLabel();
        CheckB = new javax.swing.JButton();
        CheckB.setVisible(true);
        add(GameText);
        
        GameText.setColumns(10);
        add(Box.createHorizontalStrut(70));
        add(CowCount);
        add(Box.createHorizontalStrut(40));
        add(BullCount);
        add(Box.createHorizontalStrut(30));
        add(CheckB);
           CowCount.setText("0");

        BullCount.setText("0");

        CheckB.setText("CHECK");
         CheckB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(timerflag==0)
                {
                gamePlay();
                }
               else
                {
                    CowCount.setText("X");
                    BullCount.setText("X");
                }
                CheckB.setEnabled(false);
                GameText.setEditable(false);
            }
        });
         CheckB.requestFocus();
          CheckB.addKeyListener
      (new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
           int key = e.getKeyCode();
           if (key == KeyEvent.VK_ENTER) {
                if(timerflag==0)
                {
                gamePlay();
                }
               else
                {
                    CowCount.setText("X");
                    BullCount.setText("X");
                }
                CheckB.setEnabled(false);
                GameText.setEditable(false);
              }
           }
         }
      );
       }
               
    public void gamePlay()
    {
       
        try {
            // TODO add your handling code here:
            int wordlength;
            String UserGuess=GameText.getText();
            UserGuess=UserGuess.toUpperCase();
            GameText.setText(UserGuess);
            Connector_Class C1=new Connector_Class();
            PreparedStatement pstmt=Connector_Class.con.prepareStatement("select word from Words where serial_no=?");
    ResultSet rs;
    pstmt.setString(1,String.valueOf(n));
    rs=pstmt.executeQuery();
    rs.next();
    String CB_Word=rs.getString(1);
    System.out.println(CB_Word);
            if(playFrame.wordLevel==0)
            {
                wordlength=4;
            }
            else
                wordlength=5;
            pstmt=Connector_Class.con.prepareStatement("select count(*) from Words where word=?");
            pstmt.setString(1,UserGuess);
            rs=pstmt.executeQuery();
            rs.next();
            int wct=Integer.parseInt(rs.getString(1));
            if(wct!=0&&UserGuess.length()==wordlength&&timerflag==0)
            {
            int cowct=0,bullct=0;
            int i,j;

            char[] A=UserGuess.toCharArray();
            char[] B=CB_Word.toCharArray();

            for(i=0;i<wordlength;i++)
            {
                    if(A[i]==B[i])
                            bullct++;
            }

            if(bullct==wordlength)
            {
                    bullflag=-1;
                    
                    if(playFrame.mode==1||playFrame.mode==3)
                    {
                        timer.stop();
                    }
                    Clip clip=null;
                    try
                    {
                    String soundName = "C:\\Users\\Karthik Perumal\\Documents\\NetBeansProjects\\CowsAndBulls\\src\\cowsandbulls\\win.wav";    
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    }
                    catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                      e.printStackTrace();
                    }
                    try{
                    TimeUnit.SECONDS.sleep(5);
                    }
                    catch(Exception e) {
                        
                    }
                    JOptionPane.showMessageDialog(null, "WORD FOUND!!!");
                    
                    if(playFrame.mode==3)
                    {
                        
                         interFrame i0=new interFrame();
                         i0.inter_run();
                         maingame.setVisible(false);
                         playFrame.sequencect++;
                    }
            }
            //Message as word found
    for(i=0;i<wordlength;i++)
    {
            for(j=0;j<wordlength;j++)
            {
                    if(i!=j && A[i]==B[j])
                            cowct++;
            }
    }   
    CowCount.setText(String.valueOf(cowct));
    BullCount.setText(String.valueOf(bullct));
            }
            else
            {
                CowCount.setText("X");
                BullCount.setText("X");
            }
           if(playFrame.mode==2||playFrame.mode==3)
           {
               CounterLabel.setText((Integer.parseInt(CounterLabel.getText())-1)+""+"");
               if(CounterLabel.getText().equals("0"))
               {
                   counterflag=-1;
               }
           }
           
            //Generate gamePanel
           if(counterflag==0&&bullflag==0&&timerflag==0)
           {
            rowct++;
            GL.setRows(rowct);
            GamePanel gpn=new GamePanel();
            jPanel2.add(gpn);
            gpn.setVisible(true);
            gpn.GameText.requestFocus();
            jPanel2.revalidate();
            jPanel2.repaint();
           }
           else if(counterflag==-1&&bullflag!=-1)
           {
                 try
                    {
                    String soundName = "C:\\Users\\Karthik Perumal\\Documents\\NetBeansProjects\\CowsAndBulls\\src\\cowsandbulls\\lose2.wav";    
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    }
                    catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                      e.printStackTrace();
                    }
               JOptionPane.showMessageDialog(null,"NO MORE CHANCES!!!");
               
           }
           if(playFrame.mode==3&&(counterflag==-1||timerflag==-1)&&bullflag!=-1)
           {
               timer.stop();
                String nam = JOptionPane.showInputDialog(null,"YOUR SCORE IS "+playFrame.sequencect+"\nENTER YOUR NAME: ");
                new leaderFrame().leader_frame(playFrame.wordLevel, playFrame.difficulty, playFrame.sequencect, nam);
               playFrame.sequencect=0;
               bullflag=0;
               counterflag=0;
               timerflag=0;
               
           }
           if(bullflag==-1)
               bullflag=0;
           if(timerflag==-1)
           {
               timerflag=0;
           }
           if(counterflag==-1)
               counterflag=0;
        } catch (SQLException ex) {
            Logger.getLogger(gameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }
    }
   
   public class TimerClass
   {
       public TimerClass()
       {
           TimerLabel.setText("300");
           ActionListener timerListener = new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
            TimerLabel.setText((Integer.parseInt(TimerLabel.getText())-1)+""+"");
            if(TimerLabel.getText().equals("0")&&bullflag!=-1)
            {
                timerflag=-1;
                timer.stop();
                  try
                    {
                    String soundName = "C:\\Users\\Karthik Perumal\\Documents\\NetBeansProjects\\CowsAndBulls\\src\\cowsandbulls\\lose2.wav";    
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    }
                    catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                      e.printStackTrace();
                    }
                JOptionPane.showMessageDialog(null,"TIME IS UP!!!");
                if(playFrame.mode==3)
                {
                    String nam = JOptionPane.showInputDialog(null,"YOUR SCORE IS "+playFrame.sequencect+"\nENTER YOUR NAME: ");
                    new leaderFrame().leader_frame(playFrame.wordLevel, playFrame.difficulty, playFrame.sequencect, nam);
                    playFrame.sequencect=0;
                }
            }
        } 
       };
           
           timer = new Timer(1000,timerListener);
           timer.start();
           
           
       }
   }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TimerLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CounterLabel = new javax.swing.JLabel();
        mainB = new javax.swing.JButton();
        startB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COWS 'N' BULLS");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("cowicon1.jpg")));
        setPreferredSize(new java.awt.Dimension(410, 670));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Papyrus", 1, 11)); // NOI18N
        jLabel2.setText("COUNTER : ");

        TimerLabel.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        TimerLabel.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                TimerLabelInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        GL=new GridLayout(rowct,1,0,1);
        jPanel2.setBackground(new java.awt.Color(153, 255, 153));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));
        jPanel2.setLayout(GL);
        jScrollPane1.setViewportView(jPanel2);

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 11)); // NOI18N
        jLabel4.setText("COWS");

        jLabel5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 11)); // NOI18N
        jLabel5.setText("BULLS");

        jLabel3.setFont(new java.awt.Font("Papyrus", 1, 11)); // NOI18N
        jLabel3.setText("TIMER : ");

        CounterLabel.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N

        mainB.setText("MAIN MENU");
        mainB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainBActionPerformed(evt);
            }
        });

        startB.setText("START");
        startB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(CounterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(startB)
                                    .addComponent(jLabel4))
                                .addGap(26, 26, 26)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TimerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mainB)))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainB)
                    .addComponent(startB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CounterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(TimerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TimerLabelInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_TimerLabelInputMethodTextChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_TimerLabelInputMethodTextChanged

    private void mainBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainBActionPerformed
        // TODO add your handling code here:
        playFrame.sequencect=0;
        bullflag=0;
        timerflag=0;
        counterflag=0;
        if(playFrame.mode==1||playFrame.mode==3)
        {
            timer.stop();
        }
        this.setVisible(false);
        String [] args=null;
        MainFrame.main(args);
    }//GEN-LAST:event_mainBActionPerformed

    private void startBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBActionPerformed
        // TODO add your handling code here:
         if(playFrame.mode==1||playFrame.mode==3)
        {
            TimerLabel.setText("300");
            TimerClass Tim=new TimerClass();
        }
         else
         {
             TimerLabel.setText("\u221E");
         }
           if(playFrame.mode==2||playFrame.mode==3)
        {
            CounterLabel.setText("10");
        }
         else
         {
             CounterLabel.setText("\u221E");
         }
         
       n=randomNumber(playFrame.wordLevel,playFrame.difficulty);
       GamePanel gPanel=new GamePanel();
       jPanel2.add(gPanel);
       gPanel.setVisible(true);
       gPanel.GameText.requestFocus();
       jPanel2.revalidate();
       jPanel2.repaint();
       startB.setVisible(false);
    }//GEN-LAST:event_startBActionPerformed

    
    

//Code for random number generation
        public int randomNumber(int w,int d)
        {
	Random rand=new Random(System.currentTimeMillis());
	int start_limit;
	int end_limit;
        if(w==0)
        {
            if(d==0)
            {
                start_limit=1;
                end_limit=654;
            }
            else if(d==1)
            {
                start_limit=1;
                end_limit=4354;
            }
            else
            {
                start_limit=700;
                end_limit=4354;
            }
        }
        else
        {
           if(d==0)
            {
                start_limit=5000;
                end_limit=5847;
            }
            else if(d==1)
            {
                start_limit=5000;
                end_limit=10990;
            }
            else
            {
                start_limit=6000;
                end_limit=10990;
            } 
        }
        int random_no;
        do
        {
	random_no = start_limit + rand.nextInt(end_limit-start_limit);
        }while((random_no>654&&random_no<700)||(random_no>4354&&random_no<5000)||(random_no>5847&&random_no<6000));
        return random_no;
        }

    
    public void game_run()
    {
        maingame=new gameFrame();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
               maingame.setVisible(true);
                
            }
        });
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }
    public static Timer timer;
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CounterLabel;
    private static javax.swing.JLabel TimerLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mainB;
    private javax.swing.JButton startB;
    // End of variables declaration//GEN-END:variables
}
