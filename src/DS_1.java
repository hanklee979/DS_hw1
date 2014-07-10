import javax.swing.JApplet;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList; 

import javax.swing.JTextArea;

import java.lang.Thread;
import java.util.Timer;

// 90 min + 90 min + 90min + 30min + 40min + 50min + 20min + 100min + 40min
// 23:10



public class DS_1 extends JApplet {

	
	private JTextField polynomial_A;
	private JTextField polynomial_B;
	private JTextField result;
	private JTextArea show_step = new JTextArea();	

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
	
	boolean select_plus = false;
	boolean select_minus = false;	
	
	int temp_A_coefficient;
	int temp_B_coefficient;
	

    	
    void Delay(){
    		 
    	for(int t = 0 ; t < 10000 ; t++){
    		Thread.yield();
    		}

    }
    
    
	// get tha data of textfield
    void get_polynomial(){
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
    }	
    
    
    // calculate A+B or A-B
    void calculate(){
    	if(select_plus){ // A + B
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
    	}
    	
    	else{ // A - B
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
			
			// 記錄B的其他係數及指數 (係數轉為負值)
			for(int i = 0 ; i < B_polynomial.length / 2  ; i++){
				if(B_coefficient[i] != 0){
					result_coefficient.add( - B_coefficient[i]);
					result_index.add( B_index[i]);						
				}
			}	
    	}
    }
    
    
	// initialize all array
    void initialize_array(){
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
    
    
    // turn arraylist to array and sort by bubble sort
    void sort(){
    	
		// turn arraylist to array
		for(int i = 0 ; i < result_index.size(); i++){
			result_coefficient_array[i] = result_coefficient.get(i);
			result_index_array[i] = result_index.get(i);
		}			
		
		// bubble sort (sort by index)
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
    }
    
    
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
		
		
		// Button
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBounds(30, 86, 57, 38);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				select_plus = true;
				select_minus = false;	
					
				if(plus_finish == false)
					result.setText( "" );				
			
				minus_finish = false;

				// get tha data of textfield
				get_polynomial();
				
				// calculate A+B or A-B
				calculate();
								
			    // turn arraylist to array and sort by bubble sort			
				sort();
				
		        if(plus_finish == false){		        

		        	// show result in the textfield		
		        	for(int i = 0 ; i < result_index.size()  ; i++){
		        		if(i == 0){ // if it is the last polynomial
		        			if(result_index_array[i] > 0){
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^" + result_index_array[i]);
		        			}
		        			else if(result_index_array[i] == 0){ // constant				
		        				result.setText( result.getText() + result_coefficient_array[i] );
		        			}
		        			else{
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^(" + result_index_array[i] + ")" );
		        			}
		        		}
					
		        		else{ 
		        			if(result_index_array[i] > 0 && result_coefficient_array[i] >= 0){
		        				result.setText( result.getText() + "+" + result_coefficient_array[i] + "X^" + result_index_array[i] );
		        			}
		        			else if(result_index_array[i] > 0 && result_coefficient_array[i] < 0){
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^" + result_index_array[i] );
		        			}		        			
		        			else if(result_index_array[i] == 0 && result_coefficient_array[i] >= 0){ // constant	
		        				result.setText( result.getText() + "+" + result_coefficient_array[i] );
		        			}
		        			else if(result_index_array[i] == 0 && result_coefficient_array[i] < 0){ // constant	
		        				result.setText( result.getText() + result_coefficient_array[i] );
		        			} 
		        			else if(result_index_array[i] < 0 && result_coefficient_array[i] >= 0){
		        				result.setText( result.getText() + "+" + result_coefficient_array[i] + "X^(" + result_index_array[i] + ")" );
		        			}
		        			else{ // result_index_array[i] < 0 && result_coefficient_array[i] < 0
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^(" + result_index_array[i] + ")" );
		        			}
		        		}					
		        	}			
		        }
				plus_finish = true;
				
				// initialize all array
				initialize_array();
				
			}
		});
		
		
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 18));
		getContentPane().add(btnNewButton);
		
		
		JButton button = new JButton("-");
		button.setBounds(117, 86, 57, 38);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				select_plus = false;
				select_minus = true;	
				
				if(minus_finish == false)
					result.setText( "" );	
				
				plus_finish = false;
								
				// turn tha data of textfield to array
				get_polynomial();
				
			    // calculate A+B or A-B
			    calculate();

			    // turn arraylist to array and sort by bubble sort			
				sort();
		        		        
		        if(minus_finish == false){		        
		        	// show result in the textfield		
		        	for(int i = 0 ; i < result_index.size()  ; i++){
		        		if(i == 0 ){ // if it is the last polynomial
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
		        			if(result_index_array[i] > 0 && result_coefficient_array[i] >= 0){
		        				result.setText( result.getText() + "+" + result_coefficient_array[i] + "X^" + result_index_array[i] );
		        			}
		        			else if(result_index_array[i] > 0 && result_coefficient_array[i] < 0){
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^" + result_index_array[i] );
		        			}		        			
		        			else if(result_index_array[i] == 0 && result_coefficient_array[i] >= 0){ // constant	
		        				result.setText( result.getText() + "+" + result_coefficient_array[i] );
		        			}
		        			else if(result_index_array[i] == 0 && result_coefficient_array[i] < 0){ // constant	
		        				result.setText( result.getText() + result_coefficient_array[i] );
		        			} 
		        			else if(result_index_array[i] < 0 && result_coefficient_array[i] >= 0){
		        				result.setText( result.getText() + "+" + result_coefficient_array[i] + "X^(" + result_index_array[i] + ")" );
		        			}
		        			else{ // result_index_array[i] < 0 && result_coefficient_array[i] < 0
		        				result.setText( result.getText() + result_coefficient_array[i] + "X^(" + result_index_array[i] + ")" );
		        			}
		        		}					
		        	}			
		        }
				minus_finish = true;				
				
				// initialize all array
				initialize_array();
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
				show_step.setText ( "" );
				
				plus_finish = false;
				minus_finish = false;	
				
				select_plus = false;
				select_minus = false;	
			}
		});
		btnClear.setFont(new Font("新細明體", Font.PLAIN, 18));
		getContentPane().add(btnClear);
		
		
		JButton btnStep = new JButton("step");
		btnStep.setBounds(313, 86, 69, 38);
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// get tha data of textfield
				get_polynomial();
				
				// calculate A+B or A-B
				calculate();					
				
				// bubble sort (polynomial A)
		        for (int i = 0 ; i < A_polynomial.length / 2 - 1 ; i++){
		              
		        	for (int j = 0 ; j < A_polynomial.length / 2 - i - 1 ; j++)  {
		                   
		        		if ( A_index [j+1] > A_index[j] ){

		        			int temp_index = A_index[j+1];
		        			int temp_coefficient = A_coefficient[j+1];	
		        			
		        			A_index[j+1] = A_index[j];		        			
		        			A_coefficient[j+1] = A_coefficient[j];		        			
		                    
		        			A_index[j] = temp_index;
		        			A_coefficient[j] = temp_coefficient;
		        		}
		            }
		        }

				// bubble sort (polynomial B)  
		        for (int i = 0 ; i < B_polynomial.length / 2 - 1 ; i++){
		              
		        	for (int j = 0 ; j < B_polynomial.length / 2 - i - 1 ; j++)  {
		                   
		        		if ( B_index [j+1] > B_index[j] ){

		        			int temp_index = B_index[j+1];
		        			int temp_coefficient = B_coefficient[j+1];	
		        			
		        			B_index[j+1] = B_index[j];		        			
		        			B_coefficient[j+1] = B_coefficient[j];		        			
		                    
		        			B_index[j] = temp_index;
		        			B_coefficient[j] = temp_coefficient;
		        		}
		            }
		        }        
		        
				// 回復因進行計算而刪除的數據
				get_polynomial();
				
			    // turn arraylist to array and sort by bubble sort			
				sort();
		        
		        
				// step
		        for(int i = 0; i < result_index.size(); i++){
		        	        	
		        	// initialize
		        	temp_A_coefficient = 0;
		        	temp_B_coefficient = 0;
		        	
		        	show_step.setText( show_step.getText() + "step" + (i+1) + ": " );
		        			        	
		        	// 先決定處理的指數，再找A,B符合該指數的係數		        	
		        	if(result_index_array[i] >= 0)
		        		show_step.setText( show_step.getText() + "處理x^"  + result_index_array[i] + "\n");		        	
		        	else
		        		show_step.setText( show_step.getText() + "處理x^(" + result_index_array[i] + ")\n" );				        		
		        
		        	// 找A符合的係數
		        	for(int j = 0 ; j <= A_index.length / 2 ; j++){
		        		if( result_index_array[i] == A_index[j] ){
		        			temp_A_coefficient = A_coefficient[j];
		        	  	}
		        	}
		        	
		        	show_step.setText( show_step.getText() + "A:" + temp_A_coefficient + "x^" + result_index_array[i] + "\n" );			        	
		        	
		        	// 找B符合的係數
		        	for(int k = 0 ; k <= B_index.length / 2 ; k++){
		        		if( result_index_array[i] == B_index[k] ){
		        			temp_B_coefficient = B_coefficient[k];
		        		}
		        	}
		        	
		        	show_step.setText( show_step.getText() + "B:" + temp_B_coefficient + "x^" + result_index_array[i] + "\n" );			        		
		       
		        	if(select_plus)
		        		show_step.setText( show_step.getText() + "A+B = (" + temp_A_coefficient + " + " + temp_B_coefficient + ")x^" + result_index_array[i] + "\n" );
		        	else
		        		show_step.setText( show_step.getText() + "A-B = (" + temp_A_coefficient + " - " + temp_B_coefficient + ")x^" + result_index_array[i] + "\n" );
		        	
		        	// delay 
		        	Delay();
		        
		        }

				// initialize all array
		        initialize_array();
				
				select_plus = false;
				select_minus = false;
			}
		});
		btnStep.setFont(new Font("新細明體", Font.PLAIN, 18));
		getContentPane().add(btnStep);
		show_step.setBounds(10, 223, 430, 425);
		getContentPane().add(show_step);
		
	}
}
		
		

	
	

