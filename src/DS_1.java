import javax.swing.JApplet;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList; 
// 90 min + 90 min + 90min +30min + 40min
// 

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

	// result (A+B or A-B)
	ArrayList<Integer> result_coefficient = new ArrayList<Integer>();
	ArrayList<Integer> result_index = new ArrayList<Integer>(); 
	
	int[] result_coefficient_array = new int[50];		
	int[] result_index_array = new int[50];

	boolean plus_finish = false;
	boolean minus_finish = false;	
	
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
						
				if(plus_finish == false)
					result.setText( "" );				
			
				minus_finish = false;

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
				for (int i = 0; i < B_polynomial.length ; i++){
					if( i % 2 == 0 && i != 1){
						B_coefficient[i / 2] = Integer.valueOf(B_polynomial[i]);					
					}
					else{ // i % 2 == 1 or i == 1
						B_index[ (i+1)/2 - 1] = Integer.valueOf(B_polynomial[i]);							
					}
				}
				
				// calculate A+B
				for(int i = 0 ; i < A_polynomial.length / 2  ; i++){
					for(int j = 0 ; j < B_polynomial.length / 2; j++){
						// 將A,B指數相同的部份，係數相加並記錄後刪除
						if( A_index[i] == B_index[j] ){
							result_coefficient.add(A_coefficient[i] + B_coefficient[j]);
							result_index.add(A_index[i]);
							
							A_coefficient[i] = 0;
							B_coefficient[j] = 0;
						}
					}
				}
				
				// 記錄A的其他係數及指數
				for(int i = 0 ; i < A_polynomial.length / 2  ; i++){
					if(A_coefficient[i] != 0){
						result_coefficient.add(A_coefficient[i]);
						result_index.add(A_index[i]);						
					}
				}
				
				// 記錄B的其他係數及指數
				for(int i = 0 ; i < B_polynomial.length / 2  ; i++){
					if(B_coefficient[i] != 0){
						result_coefficient.add(B_coefficient[i]);
						result_index.add(B_index[i]);						
					}
				}	

				// turn arraylist to array
				for(int i = 0 ; i < result_index.size(); i++){
					result_coefficient_array[i] = result_coefficient.get(i);
					result_index_array[i] = result_index.get(i);
				}			
				
				// bubble sort
		        for (int i = 0 ; i < result_index.size() - 1 ; i++){
		              
		        	for (int j = 0 ; j < result_index.size() - i - 1 ; j++)  {
		                   
		        		if ( result_index_array [j+1] > result_index_array[j] ){

		        			int temp_index = result_index_array[j+1];
		        			int temp_coefficient = result_coefficient_array[j+1];	
		        			
		                    result_index_array[j+1] = result_index_array[j];		        			
		        			result_coefficient_array[j+1] = result_coefficient_array[j];		        			
		                    
		                    result_index_array[j] = temp_index;
		                    result_coefficient_array[j] = temp_coefficient;
		        		}
		            }
		        }
		        		        
		        if(plus_finish == false){		        

		        	// show result in the textfield		
		        	for(int i = 0 ; i < result_index.size()  ; i++){
		        		if(i == result_index.size() - 1 ){ // if it is the last polynomial
		        			if(result_index_array[i] > 0){
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^" + result_index_array[i]);
		        			}
		        			else if(result_index_array[i] == 0){ // constant				
		        				result.setText( result.getText() + result_coefficient_array[i] + "" );
		        			}
		        			else{
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^(" + result_index_array[i] + ")" );
		        			}
		        		}
					
		        		else{ 
		        			if(result_index_array[i] > 0){
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^" + result_index_array[i] + "+" );
		        			}
		        			else if(result_index_array[i] == 0){ // constant	
		        				result.setText( result.getText() + result_coefficient_array[i] + "+" );
		        			}
		        			else{
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^(" + result_index_array[i] + ")+");
		        			}
		        		}					
		        	}			
		        }
				plus_finish = true;
				
				// initialize all array
				for(int i = 0 ; i < 50; i++){
					A_coefficient[i] = 0;
					A_index[i] = 0;
					B_coefficient[i] = 0;
					B_index[i] = 0;
					result_coefficient_array[i] = 0;
					result_index_array[i] = 0;
				}
				
				for(int i = 0 ; i < A_polynomial.length; i++){
					A_polynomial[i] = "";
				}
				
				for(int i = 0 ; i < B_polynomial.length; i++){
					B_polynomial[i] = "";
				}
				
				result_coefficient.clear();
				result_index.clear();
				
			}
		});
		
		
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 18));
		getContentPane().add(btnNewButton);
		
		
		JButton button = new JButton("-");
		button.setBounds(117, 86, 57, 38);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(minus_finish == false)
					result.setText( "" );	
				
				plus_finish = false;
				
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
				for (int i = 0; i < B_polynomial.length ; i++){
					if( i % 2 == 0 && i != 1){
						B_coefficient[i / 2] = Integer.valueOf(B_polynomial[i]);					
					}
					else{ // i % 2 == 1 or i == 1
						B_index[ (i+1)/2 - 1] = Integer.valueOf(B_polynomial[i]);							
					}
				}
				
				// calculate A+B
				for(int i = 0 ; i < A_polynomial.length / 2  ; i++){
					for(int j = 0 ; j < B_polynomial.length / 2; j++){
						// 將A,B指數相同的部份，係數相減並記錄後刪除
						if( A_index[i] == B_index[j] ){
							result_coefficient.add(A_coefficient[i] - B_coefficient[j]);
							result_index.add(A_index[i]);
							
							A_coefficient[i] = 0;
							B_coefficient[j] = 0;
						}
					}
				}
				
				// 記錄A的其他係數及指數
				for(int i = 0 ; i < A_polynomial.length / 2  ; i++){
					if(A_coefficient[i] != 0){
						result_coefficient.add(A_coefficient[i]);
						result_index.add(A_index[i]);						
					}
				}
				
				// 記錄B的其他係數及指數 (轉為負值)
				for(int i = 0 ; i < B_polynomial.length / 2  ; i++){
					if(B_coefficient[i] != 0){
						result_coefficient.add( - B_coefficient[i]);
						result_index.add( - B_index[i]);						
					}
				}	

				// turn arraylist to array
				for(int i = 0 ; i < result_index.size(); i++){
					result_coefficient_array[i] = result_coefficient.get(i);
					result_index_array[i] = result_index.get(i);
				}			
				
				// bubble sort
		        for (int i = 0 ; i < result_index.size() - 1 ; i++){
		              
		        	for (int j = 0 ; j < result_index.size() - i - 1 ; j++)  {
		                   
		        		if ( result_index_array [j+1] > result_index_array[j] ){

		        			int temp_index = result_index_array[j+1];
		        			int temp_coefficient = result_coefficient_array[j+1];	
		        			
		                    result_index_array[j+1] = result_index_array[j];		        			
		        			result_coefficient_array[j+1] = result_coefficient_array[j];		        			
		                    
		                    result_index_array[j] = temp_index;
		                    result_coefficient_array[j] = temp_coefficient;
		        		}
		            }
		        }
		        		        
		        if(minus_finish == false){		        

		        	// show result in the textfield		
		        	for(int i = 0 ; i < result_index.size()  ; i++){
		        		if(i == result_index.size() - 1 ){ // if it is the last polynomial
		        			if(result_index_array[i] > 0){
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^" + result_index_array[i]);
		        			}
		        			else if(result_index_array[i] == 0){ // constant				
		        				result.setText( result.getText() + result_coefficient_array[i] + "" );
		        			}
		        			else{
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^(" + result_index_array[i] + ")" );
		        			}
		        		}
					
		        		else{ 
		        			if(result_index_array[i] > 0){
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^" + result_index_array[i] + "+" );
		        			}
		        			else if(result_index_array[i] == 0){ // constant	
		        				result.setText( result.getText() + result_coefficient_array[i] + "+" );
		        			}
		        			else{
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^(" + result_index_array[i] + ")+");
		        			}
		        		}					
		        	}			
		        }
				minus_finish = true;				
				
				// initialize all array
				for(int i = 0 ; i < 50; i++){
					A_coefficient[i] = 0;
					A_index[i] = 0;
					B_coefficient[i] = 0;
					B_index[i] = 0;
					result_coefficient_array[i] = 0;
					result_index_array[i] = 0;
				}
				
				for(int i = 0 ; i < A_polynomial.length; i++){
					A_polynomial[i] = "";
				}
				
				for(int i = 0 ; i < B_polynomial.length; i++){
					B_polynomial[i] = "";
				}				

				result_coefficient.clear();
				result_index.clear();
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
		
		
	
	
	

