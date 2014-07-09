import javax.swing.JApplet;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// 90 min + 90 min

public class DS_1 extends JApplet {
	private JTextField polynomial_A;
	private JTextField polynomial_B;
	private JTextField result;
	private JTextField cal;
	

	// before split
	String A_BeforeSplit = "";
	String B_BeforeSplit = "";	
	
	// after split
	String[] A_polynomial = new String[100];		
	int[] A_coefficient = new int[50];		
	int[] A_index = new int[50];
		
	String[] B_polynomial = new String[100];	
	int[] B_coefficient = new int[50];		
	int[] B_index = new int[50];		

	
	public void init(){
		

		getContentPane().setLayout(null);
		
		
		// Label
		JLabel lblNewLabel = new JLabel("\u591A\u9805\u5F0F1 (A = )");
		lblNewLabel.setBounds(10, 10, 109, 28);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel);
		
		JLabel lblb = new JLabel("\u591A\u9805\u5F0F1 (B = )");
		lblb.setBounds(10, 48, 109, 28);
		lblb.setFont(new Font("新細明體", Font.PLAIN, 16));
		getContentPane().add(lblb);
		
		JLabel label = new JLabel("\u7D50\u679C\uFF1A");
		label.setBounds(10, 147, 48, 28);
		label.setFont(new Font("新細明體", Font.PLAIN, 16));
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u8655\u7406\u904E\u7A0B\uFF1A");
		label_1.setBounds(10, 185, 88, 28);
		label_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		getContentPane().add(label_1);		
		
		
		// Test Field
		polynomial_A = new JTextField();
		polynomial_A.setBounds(117, 15, 323, 21);
		getContentPane().add(polynomial_A);
		polynomial_A.setColumns(10);
		
		polynomial_B = new JTextField();
		polynomial_B.setBounds(117, 53, 323, 21);
		getContentPane().add(polynomial_B);
		polynomial_B.setColumns(10);
	
		result = new JTextField();
		result.setBounds(61, 152, 379, 21);
		result.setColumns(10);
		getContentPane().add(result);

		cal = new JTextField();
		cal.setBounds(10, 223, 430, 260);
		getContentPane().add(cal);
		cal.setColumns(10);
		
		
		// Button
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBounds(30, 86, 57, 38);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_BeforeSplit = polynomial_A.getText();
				B_BeforeSplit = polynomial_B.getText();
				
				// split and put data in A_polynomial and B_polynomial
				A_polynomial = A_BeforeSplit.split("[, ]+");
				B_polynomial = B_BeforeSplit.split("[, ]+");
				
				// get coefficient and index of polynomial A
				for (int i = 0; i < A_polynomial.length; i++){
					if( i % 2 == 0 && i != 1){
						A_coefficient[i / 2] = Integer.valueOf(A_polynomial[i]);					
					}
					else{ // i % 2 == 1 or i == 1
						A_index[ (i+1)/2 - 1] = Integer.valueOf(A_polynomial[i]);							
					}
				}

				// get coefficient and index of polynomial B				
				for (int i = 0; i < B_polynomial.length; i++){
					if( i % 2 == 0 && i != 1){
						B_coefficient[i / 2] = Integer.valueOf(B_polynomial[i]);					
					}
					else{ // i % 2 == 1 or i == 1
						B_index[ (i+1)/2 - 1] = Integer.valueOf(B_polynomial[i]);							
					}
				}

				
			}
		});
		
		
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 18));
		getContentPane().add(btnNewButton);
		
		
		JButton button = new JButton("-");
		button.setBounds(117, 86, 57, 38);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_BeforeSplit = polynomial_A.getText();
				B_BeforeSplit = polynomial_B.getText();
				
				// split and put data in A_polynomial and B_polynomial
				A_polynomial = A_BeforeSplit.split("[, ]+");
				B_polynomial = B_BeforeSplit.split("[, ]+");
				
				// get coefficient and index of polynomial A
				for (int i = 0; i < A_polynomial.length; i++){
					if( i % 2 == 0 && i != 1){
						A_coefficient[i / 2] = Integer.valueOf(A_polynomial[i]);					
					}
					else{ // i % 2 == 1 or i == 1
						A_index[ (i+1)/2 - 1] = Integer.valueOf(A_polynomial[i]);							
					}
				}

				// get coefficient and index of polynomial B				
				for (int i = 0; i < B_polynomial.length; i++){
					if( i % 2 == 0 && i != 1){
						B_coefficient[i / 2] = Integer.valueOf(B_polynomial[i]);					
					}
					else{ // i % 2 == 1 or i == 1
						B_index[ (i+1)/2 - 1] = Integer.valueOf(B_polynomial[i]);							
					}
				}			
				
				
			}
		});
		button.setFont(new Font("新細明體", Font.PLAIN, 18));
		getContentPane().add(button);
		
		
		JButton btnClear = new JButton("clear");
		btnClear.setBounds(212, 86, 80, 38);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				polynomial_A.setText ( "" );
				polynomial_B.setText ( "" );
				result.setText ( "" );
				cal.setText ( "" );
			}
		});
		btnClear.setFont(new Font("新細明體", Font.PLAIN, 18));
		getContentPane().add(btnClear);
		
		
		JButton btnStep = new JButton("step");
		btnStep.setBounds(313, 86, 69, 38);
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStep.setFont(new Font("新細明體", Font.PLAIN, 18));
		getContentPane().add(btnStep);
		
	}
	
}
		
		
	
	
	

