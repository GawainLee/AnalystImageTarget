package colorPicker;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

	private String path;
	private File file;
	
	private JFrame frame;
	private JLabel imageLable;
	private ImageIcon icon;
	private JLabel lblMessage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 100, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//		frame.setUndecorated(true);
//		frame.setVisible(true);
		

		imageLable = new JLabel("");

		// add a mouse motion listener, and update my custom mouse cursor with the x/y
		// mouse click listener

		imageLable.addMouseListener(new MouseAdapter()  
		{  
			public void mouseClicked(MouseEvent me)  
			{  
				System.out.println("me.getX() " + me.getX());
				System.out.println("me.getY() " + me.getY());

				int[] temp = getPixel(me.getX(),me.getY());
				System.out.println( "R:" + temp[0] + " G:" + temp[1] + " B:" + temp[2]);

			}  
		}); 
		// add a mouse motion listener, and update my custom mouse cursor with the x/y
		// coordinates as the user moves the mouse
//		imageLable.addMouseMotionListener(new MouseMotionAdapter() {
//		      public void mouseMoved(MouseEvent me)
//		      {
//		    	  System.out.println("me.getX() " + me.getX());
//		    	  System.out.println("me.getY() " + me.getY());
//		      }
//		    });
		
		frame.getContentPane().add(imageLable, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				path = loadFile();
				file = new File(path);
				icon = new ImageIcon(path);
//				imageLable.setIcon(ResizeImage(path));
				imageLable.setIcon(icon);
				imageLable.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
				System.out.println("Image Lable Image Size: X: " + icon.getIconWidth() + " Y: " + icon.getIconHeight());
				imageLable.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
			}
		});
		panel.add(btnNewButton);
		
		lblMessage = new JLabel("Message");
		panel.add(lblMessage);
	}
	
	  public String loadFile() {
	    String result = "";
	    JFileChooser jfc=new JFileChooser();
	    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
	    jfc.showDialog(new JLabel(), "Choice");
	    
		jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
		//filter the files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		jfc.addChoosableFileFilter(filter);
		
	    File file=jfc.getSelectedFile();
	    if(file.isDirectory()){
	      System.out.println("Floder:"+file.getAbsolutePath());
	    }else if(file.isFile()){
	      System.out.println("File:"+file.getAbsolutePath());
	      result = file.getAbsolutePath();
	    }
	    System.out.println(jfc.getSelectedFile().getName());
	    return result;
	  }

	  
	  /**
	   * @param x
	   * @param y
	   * @return int[Red,Green,Bule]
	   */
	  public int[] getPixel(int x, int y)
	  {  
	        Robot rb;
	        int pixelColor = 0;
	        int[] rgb = new int[3];
			try {
				rb = new Robot();
//				Toolkit tk = Toolkit.getDefaultToolkit(); // get tool
//		        Dimension di = tk.getScreenSize(); //get screen size
//		        Rectangle rec = new Rectangle(0, 0, di.width, di.height);  
//		        BufferedImage bi = rb.createScreenCapture(rec);  
		        BufferedImage bi ;
		        bi = ImageIO.read(file);
				pixelColor = bi.getRGB(x, y);
				System.out.println("image size : x: " + bi.getWidth() + " y: " + bi.getHeight());
				Color c = new Color(bi.getRGB(x,y));
				rgb[0] = c.getRed();
				rgb[1] = c.getGreen();
				rgb[2] = c.getBlue();
						
				int red=c.getRed();
				int green=c.getGreen();
				int blue=c.getBlue();

				System.out.print("Red " + red + " Green " + green+ " Blue" + blue + "\n" );
				
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
	        
	        return rgb;   
	    }  
	  
	  // Methode to resize imageIcon with the same size of a Jlabel
	    public ImageIcon ResizeImage(String ImagePath)
	    {
	        ImageIcon MyImage = new ImageIcon(ImagePath);
	        Image img = MyImage.getImage();
	        Image newImg = img.getScaledInstance(imageLable.getWidth(), imageLable.getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon image = new ImageIcon(img);
	        return image;
	    }
}
