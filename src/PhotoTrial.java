import javax.swing.*;


public class PhotoTrial extends JFrame{
	
	JPanel display;
	JLabel photo;
	JButton jb;
	
	public PhotoTrial() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		display = new JPanel();
		photo = new JLabel();
		jb = new JButton("Hello");
		
		display.add(photo);
		display.add(jb);
		setContentPane(display);
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i=1;
				while(true){
					photoChanger(i);
					i++;
					if(i>3){
						i = 1;
					}
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		t.start();
	}
	
	private void photoChanger(int i){
		photo.setIcon(new ImageIcon("Images\\"+i+".jpg"));
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				PhotoTrial jf = new PhotoTrial();
				jf.setVisible(true);
			}
			
		});

	}

}
