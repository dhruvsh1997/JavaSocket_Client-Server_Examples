import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.net.*;
import javax.swing.JOptionPane;

class MyServer extends JFrame
{
JFrame f;
JPanel pn1,pn2,pn3,p1,p2;
JLabel l1,l2,l3,l4,l5;
JTextField t1,t2,t3,tc1,tc2;
JTextArea ta1,ta2;
JButton bc1,bc2,bs1,bs2,b1,b2,b3,b4;

String sc;

String str1,str2,str3,str4,str5,str6;

ServerSocket ss;
Socket s;
PrintWriter pw;
BufferedReader br;
Thread th;
 
DataInputStream din;  
DataOutputStream dout;  

MyServer()
{
//frame
f=new JFrame();
f.setTitle("Server Site:");
f.setLayout(null);
f.setVisible(true);
f.setSize(410,430);//ww,hh
f.setLocation(200,200);
//panels
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
pn1=new JPanel();
pn1.setSize(400,100);//ww,hh
pn1.setLocation(0,0);
pn1.setLayout(null);
pn1.setBackground(Color.ORANGE);
f.add(pn1);
pn3=new JPanel();
pn3.setLayout(null);
pn3.setSize(400,120);//ww,hh
pn3.setLocation(0,350);
pn3.setBackground(Color.YELLOW);
f.add(pn3);
pn2=new JPanel();
pn2.setLayout(null);
pn2.setSize(400,250);//ww,hh
pn2.setLocation(0,100);
pn2.setBackground(Color.WHITE);
f.add(pn2);
p1=new JPanel();
p1.setLayout(null);
p1.setSize(200,250);//ww,hh
p1.setLocation(0,0);
p1.setBackground(Color.RED);
pn2.add(p1);
p2=new JPanel();
p2.setLayout(null);
p2.setSize(200,250);//ww,hh
p2.setLocation(200,0);
p2.setBackground(Color.GRAY);
pn2.add(p2);
//labels for main panels
l2 = new JLabel("IP Address :");
l2.setBounds(10,10,100,25);
l2.setForeground(Color.BLUE);
pn1.add(l2);
l2 = new JLabel("Server Name :");
l2.setBounds(10,40,100,25);
l2.setForeground(Color.BLUE);
pn1.add(l2);
l3=new JLabel("Port No. :");
l3.setBounds(10,70,100,25);
l3.setForeground(Color.BLUE);
pn1.add(l3);
//textfields
t1 = new JTextField();
t1.setBounds(120,10,220,25);
pn1.add(t1);
t2 = new JTextField();
t2.setBounds(120,40,220,25);
pn1.add(t2);
t1 = new JTextField();
t1.setBounds(120,70,220,25);
pn1.add(t1);
//labels for Chat panels
l4 = new JLabel("Client Response :");
l4.setBounds(10,5,100,25);
l4.setForeground(Color.GREEN);
p1.add(l4);
l5 = new JLabel("Server Massage :");
l5.setBounds(10,5,100,25);
l5.setForeground(Color.GREEN);
p2.add(l5);
//textareas
ta2=new JTextArea("");
ta2.setEditable(false);
ta2.setBounds(10,30,170,130);
p1.add(ta2);
ta1=new JTextArea("");
ta1.setBounds(10,30,170,130);
p2.add(ta1);
//textfields for chat
tc1 = new JTextField();
tc1.setBounds(10,165,170,25);
p1.add(tc1);
tc2 = new JTextField();
tc2.setBounds(10,165,170,25);
tc2.setEditable(false);
p2.add(tc2);
//buttons for chat
/*bs1=new JButton("Send");
bs1.setBounds(10,200,70,25);
p1.add(bs1);*/
bs2=new JButton("Clear");
bs2.setBounds(100,200,70,25);
bs2.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	ta1.setText("");
	tc1.setText("");
	}
	});
p1.add(bs2);

bc1=new JButton("Send");
bc1.setBounds(10,200,70,25);
bc1.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
  pw.println(t2.getText());//write the value of textfield into PrintWriter
  tc2.setText("");
	}
	});
p2.add(bc1);
bc2=new JButton("Clear");
bc2.setBounds(100,200,70,25);
bc2.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	ta2.setText("");
	tc2.setText("");
	}
	});
p2.add(bc2);
//buttons for action
b1=new JButton("Save");
b1.setBounds(10,10,80,25);
pn3.add(b1);
b2=new JButton("Connect");
b2.setBounds(100,10,100,25);
b2.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	Object o = e.getSource();
	if(o==b1)
	{
	try{
   ss=new ServerSocket(12000);//Socket for server
   s=ss.accept();//accepts request from client
   System.out.println(s);
   //below line reads input from InputStreamReader
   br=new BufferedReader(new InputStreamReader(s.getInputStream()));
   //below line writes output to OutPutStream
   pw=new PrintWriter(s.getOutputStream(),true);
  }catch(Exception ext)
  {System.out.println(ext);
  }
  th=new Thread();//start a new thread
  th.setDaemon(true);//set the thread as demon
  th.start();
	}
	}
	});
pn3.add(b2);
b3=new JButton("Disconnect");
b3.setBounds(205,10,100,25);
pn3.add(b3);
b4=new JButton("Exit");
b4.setBounds(310,10,80,25);
b4.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	System.exit(0);
	}
	});
pn3.add(b4);

 try{
   ss=new ServerSocket(12000);//Socket for server
   s=ss.accept();//accepts request from client
   System.out.println(s);
   //below line reads input from InputStreamReader
   br=new BufferedReader(new InputStreamReader(s.getInputStream()));
   //below line writes output to OutPutStream
   pw=new PrintWriter(s.getOutputStream(),true);
  }catch(Exception e)
  {System.out.println(e);
  }
  
  th=new Thread();//start a new thread
  th.setDaemon(true);//set the thread as demon
  th.start();
  
}

 void run()
 {
  while(true)
  {
   try{
    String s=br.readLine();//reads the input from textfield
    ta1.append(s+"\n");//Append to TextArea
   }catch(Exception e)
   {
   }
  } 
 }
	public static void main(String arr[])
	{
		new MyServer();
	}
} 