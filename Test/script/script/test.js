importPackage(javax.swing);
importPackage(javax.swing.border);
importPackage(java.awt.event);
importClass(java.lang.System);

var contentPane;
var messagePane;
var sentPane;
var button_1;

function main(){
	var frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setBounds(100, 100, 542, 506);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	frame.setContentPane(contentPane);
	contentPane.setLayout(null);
	
	messagePane = new JTextPane();
	messagePane.setContentType("text/html");
	messagePane.setEditable(false);
	var scrollPane1 = new JScrollPane(messagePane);
	scrollPane1.setBounds(10, 10, 506, 290);
	contentPane.add(scrollPane1);
	
	sentPane = new JEditorPane();
	var scrollPane = new JScrollPane(sentPane);
	scrollPane.setBounds(10, 320, 506, 110);
	contentPane.add(scrollPane);
	var button = new JButton("πÿ±’");
	button.addActionListener(
			new JavaAdapter(
					ActionListener, {
						actionPerformed : function(event){
							System.exit(0);
						}
					}
			)
	);
	button.setBounds(388, 435, 58, 27);
	contentPane.add(button);
	
	button_1 = new JButton("∑¢ÀÕ");
	button_1.setEnabled(false);
	
	button_1.setBounds(458, 435, 58, 27);
	contentPane.add(button_1);
	
	var button_2 = new JButton("≈‰÷√");
	button_2.setBounds(10, 431, 52, 30);
	contentPane.add(button_2);
	
	var button_3 = new JButton("µ«¬º");
	button_3.setBounds(69, 433, 52, 30);
	contentPane.add(button_3);
	
	frame.setVisible(true);
} 