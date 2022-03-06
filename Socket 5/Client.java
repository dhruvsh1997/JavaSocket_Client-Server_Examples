import java.awt.*;
import java.net.*; 
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;

class Client extends JFrame 
{
JFrame f;
JLabel l1;
JTextField t1;
JTextArea t2;
JButton b1;

static Socket s; 
static DataInputStream din;
static DataOutputStream dout;  

Client()throws Exception
{
f=new JFrame();
f.setTitle("Client");
f.setLayout(null);
f.setVisible(true);
f.setSize(250,320);
f.setLocation(400,300);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

l1 = new JLabel("Server Says:");
l1.setBounds(10,10,150,25);
l1.setForeground(Color.RED);
f.add(l1);

t2 = new JTextArea();
t2.setBounds(10,40,160,130);
f.add(t2);

t1 = new JTextField();
t1.setBounds(10,180,160,25);
f.add(t1);

b1= new JButton("Send");
b1.setBounds(10,220,90,30);
b1.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
    String msgin="";
        try{
            String str="";
            s=new Socket("localhost",1219);
            din = new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());
            while(!msgin.equals("exit")){
                msgin=din.readUTF();
                str=t1.getText().trim();
                t1.setText(str+"\n"+msgin); 
            }
			din.close();  
			s.close();  
			//ss.close();
        }
    catch(Exception ex){
        System.out.println(ex);
    }
}});
	f.add(b1);
	
}

public static void main(String arr[]) throws Exception
{
	new Client();


}
}