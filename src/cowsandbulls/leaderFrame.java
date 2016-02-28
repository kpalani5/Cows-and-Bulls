/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cowsandbulls;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

class leaderboard
{
  String name;
  int score;
  public leaderboard()
  {
   name=null;
   score=0;
  }
  public leaderboard(String n,int i)
  {
    name=n;
    score=i;
  }
  public int check(leaderboard[] l,int i,int n)
  {
      if(l[i-1].score>=n)
          return 0;
      return 1;
  }
  public void sortboard(leaderboard[] l,int n)
  {
     int i,j;
     for(i=0;i<n;i++)
     {
      for(j=0;j<n;j++)
      {
       if(l[i].score>l[j].score&&i!=j)
       {
         leaderboard temp;
         temp=l[i];
         l[i]=l[j];
         l[j]=temp; 
       }
      }
     }
  }
}
  
  class read
{
      
  public void reading(leaderboard[] arr,String filename,leaderboard temp)
  {
    try
    {
    int i=0,j=0;
    String buf=null;
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String line = null;
    while ((line = reader.readLine()) != null)
    {
       String[] parts = line.split("#");
       for (String part : parts)
        {
          if(j==0)
          {
            buf=part;
            j++;
          }
          else if(j==1)
          {
            arr[i]=new leaderboard(buf,Integer.parseInt(part));
            j=0;
            i++;
          }
        }
    }
    arr[0].sortboard(arr,i);
    if(temp!=null)
    {
    if(i<10)
    {
        arr[i]=new leaderboard(temp.name,temp.score);
        i++;
    }
    else if(i==10&&temp.check(arr, i, temp.score)==1)
    {
        arr[9]=new leaderboard(temp.name,temp.score);
    }
    arr[0].sortboard(arr,i);
    }
    arr[0].sortboard(arr,i);
    writing(arr,i,filename);
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }
  }
  public void writing(leaderboard[] l,int n,String filename)
  {
      try {
          PrintWriter buf=new PrintWriter(filename);
          String line=null;
          int i;
          for(i=0;i<n;i++)
          {
              if(l[i].score!=0)
              {
              line=l[i].name+"#"+String.valueOf(l[i].score);
              buf.println(line);
              }
          }
          buf.flush();
          buf.close();
      } catch (IOException ex) {
          System.out.println("1");
      }
  }
}

     
public class leaderFrame extends javax.swing.JFrame {

    public leaderFrame() {
        initComponents();
    }
    
    public void leader(int w,int d,String nam,int sc)
    {
        String filename=null;
        if(w==0)
        {
            wordL.setText("4");
            switch(d)
            {
                case 0: filename="C:\\Users\\Karthik Perumal\\Documents\\NetBeansProjects\\CowsAndBulls\\src\\cowsandbulls\\Easy4.txt";
                                  diffL.setText("EASY");  
                                    break;
                case 1: filename="C:\\Users\\Karthik Perumal\\Documents\\NetBeansProjects\\CowsAndBulls\\src\\cowsandbulls\\Norm4.txt";
                                    diffL.setText("NORMAL");  
                                    break;
                case 2: filename="C:\\Users\\Karthik Perumal\\Documents\\NetBeansProjects\\CowsAndBulls\\src\\cowsandbulls\\Hard4.txt";
                        diffL.setText("HARD");  
                                    break;
            }
        }
        else
        {
            wordL.setText("5");
            switch(d)
            {
                case 0: filename="C:\\Users\\Karthik Perumal\\Documents\\NetBeansProjects\\CowsAndBulls\\src\\cowsandbulls\\Easy5.txt";
                                  diffL.setText("EASY");  
                                    break;
                case 1: filename="C:\\Users\\Karthik Perumal\\Documents\\NetBeansProjects\\CowsAndBulls\\src\\cowsandbulls\\Norm5.txt";
                                    diffL.setText("NORMAL");  
                                    break;
                case 2: filename="C:\\Users\\Karthik Perumal\\Documents\\NetBeansProjects\\CowsAndBulls\\src\\cowsandbulls\\Hard5.txt";
                        diffL.setText("HARD");  
                                    break;
            }
        }
        read x=new read();
        leaderboard[] l = new leaderboard[10];
        int i;
        for(i=0;i<10;i++)
        {
            l[i]=new leaderboard();
        }
        leaderboard temp=null;
        if(nam!=null)
        {
           temp=new leaderboard();
            temp.name=nam;
            temp.score=sc;
        }
        x.reading(l,filename,temp);
        leaderText.setText(null);
        leaderText.append("RANK\t\tNAME\t\t\t\tSCORE\n");
        String buf1="";
        if(l!=null)
        {
        for(i=0;i<10&&l[i].score!=0;i++)
        {
            int k;
            buf1="";
            for(k=l[i].name.length();k<32;k++)
            {
                buf1=buf1+" ";
            }
            leaderText.append(String.valueOf(i+1)+"\t\t"+l[i].name+buf1+String.valueOf(l[i].score)+"\n");  
        }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        diffL = new javax.swing.JLabel();
        wordL = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        leaderText = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("COWS 'N' BULLS");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("cowicon1.jpg")));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 3, 12)); // NOI18N
        jLabel1.setText("LEADERBOARD");

        jLabel2.setText("MODE : SEQUENCE");

        jLabel3.setText("DIFFICULTY : ");

        jLabel4.setText("WORD LEVEL : ");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cowsandbulls/grassbackground.jpg"))); // NOI18N

        leaderText.setEditable(false);
        leaderText.setBackground(new java.awt.Color(0, 0, 0));
        leaderText.setColumns(20);
        leaderText.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        leaderText.setForeground(new java.awt.Color(255, 255, 255));
        leaderText.setRows(5);
        leaderText.setWrapStyleWord(true);
        jScrollPane2.setViewportView(leaderText);

        jButton1.setText("VIEW");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(diffL, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(wordL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(58, 58, 58))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(diffL, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wordL, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        leader(w,d,nam,sc);
        jButton1.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    public void leader_frame(int x,int y,int z,String n)
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new leaderFrame().setVisible(true);
            }
        });
        w=x;
        d=y;
        sc=z;
        nam=n;
    }
 private static int w;
 private static int d;
 private static int sc;
 private static String nam;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel diffL;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private static javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTextArea leaderText;
    private javax.swing.JLabel wordL;
    // End of variables declaration//GEN-END:variables
}

