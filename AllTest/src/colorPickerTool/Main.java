package colorPickerTool;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {

	private ImagePoint imagePoint;
	private TargetImagePoints targetImagePoints;
	private ListTargetImagePoints listTargetImagePoints;
	private int imagePointNum = 1;
	
	private File file;
	private String filePath;
	private ImageIcon icon;
	public int imageWidth;
	public int imageHeight;
	int cutImageWidth = 40;
    int cutImageHeight = 40;
    int imageZoomTimes = 4;
    
	private JLabel lblImage;
	private JButton btnFile;
	private JLabel lblMessage;
	private JLabel lblZoomimage;
	private JButton btnNextTarget;
	private JButton btnOutputResult;
	
	private JFrame frame;

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
		this.listTargetImagePoints = new ListTargetImagePoints();
		this.targetImagePoints = new TargetImagePoints();
		this.imagePoint = new ImagePoint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 576, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -200, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 6, SpringLayout.EAST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, frame.getContentPane());
		
		lblImage = new JLabel();
		
		//*************
		lblImage.addMouseListener(new MouseAdapter()  
		{  
			public void mouseClicked(MouseEvent me)  
			{  
				System.out.println("me.getX() " + me.getX());
				System.out.println("me.getY() " + me.getY());

				int[] temp = getPixel(me.getX(),me.getY());
//				System.out.println( "R:" + temp[0] + " G:" + temp[1] + " B:" + temp[2]);
				
				ImagePoint imagePoint = new ImagePoint(imagePointNum, me.getX(), me.getY(), temp[0], temp[1], temp[2]);
				targetImagePoints.setImagePoint(imagePoint);
				imagePointNum++;

			}  
		}); 
		// add a mouse motion listener, and update my custom mouse cursor with the x/y
		// coordinates as the user moves the mouse
		lblImage.addMouseMotionListener(new MouseMotionAdapter() {
	      public void mouseMoved(MouseEvent me)
	      {
//	    	  System.out.println("me.getX() " + me.getX());
//	    	  System.out.println("me.getY() " + me.getY());
	    	  setZoomInImage(me.getX(), me.getY());
	      }
	    });
		//*************
		panel.add(lblImage);
		frame.getContentPane().add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		btnFile = new JButton("File");
		sl_panel_1.putConstraint(SpringLayout.WEST, btnFile, 0, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnFile, -10, SpringLayout.EAST, panel_1);
		//*************
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filePath = loadFile();
				file = new File(filePath);
				icon = new ImageIcon(filePath);
//				imageLable.setIcon(ResizeImage(path));
				lblImage.setIcon(icon);
				imageWidth = icon.getIconWidth();
				imageHeight = icon.getIconHeight();
				lblImage.setBounds(0,0,imageWidth,imageHeight);
				System.out.println("Image Lable Image Size: X: " + imageWidth + " Y: " + imageHeight);
//				lblImage.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
//				lblImage.repaint();
			}
		});
		
		//*************
		
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnFile, 0, SpringLayout.NORTH, panel_1);
		panel_1.add(btnFile);
		
		lblMessage = new JLabel("Message");
		sl_panel_1.putConstraint(SpringLayout.WEST, lblMessage, 0, SpringLayout.WEST, btnFile);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblMessage, 0, SpringLayout.EAST, btnFile);
		panel_1.add(lblMessage);
		
		lblZoomimage = new JLabel();
		sl_panel_1.putConstraint(SpringLayout.WEST, lblZoomimage, 0, SpringLayout.WEST, btnFile);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblZoomimage, -10, SpringLayout.SOUTH, panel_1);
		panel_1.add(lblZoomimage);
		
		btnNextTarget = new JButton("Next Target");
		btnNextTarget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listTargetImagePoints.setTargetImagePoints(targetImagePoints);
				targetImagePoints = new TargetImagePoints();
				imagePointNum = 1;
			}
		});
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNextTarget, 6, SpringLayout.SOUTH, btnFile);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNextTarget, 0, SpringLayout.WEST, btnFile);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnNextTarget, 0, SpringLayout.EAST, btnFile);
		panel_1.add(btnNextTarget);
		
		btnOutputResult = new JButton("Output Result");
		btnOutputResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnalystListTargetImagePoints a = new AnalystListTargetImagePoints(listTargetImagePoints.getListTargetImagePointsString());
				TXTTool.writeTxt(listTargetImagePoints.getListTargetImagePointsString());
				listTargetImagePoints = new ListTargetImagePoints();
				targetImagePoints = new TargetImagePoints();
				imagePointNum = 1;
			}
		});
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblMessage, 6, SpringLayout.SOUTH, btnOutputResult);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnOutputResult, 6, SpringLayout.SOUTH, btnNextTarget);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnOutputResult, 0, SpringLayout.WEST, btnFile);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnOutputResult, 0, SpringLayout.EAST, btnFile);
		panel_1.add(btnOutputResult);
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
	   * @return int[Red,Green,Blue]
	   */
	public int[] getPixel(int x, int y)
	  {  
	        int[] rgb = new int[3];
			try {
		        BufferedImage bi ;
		        bi = ImageIO.read(file);
		        
//		        int tempCutWidth = 0;
//		        int tempCutHeight = 0;
//		        if(x > (this.imageWidth - this.cutImageWidth))
//		        {
//		        	tempCutWidth = this.imageWidth - this.cutImageWidth;
//		        }else
//		        {
//		        	tempCutWidth = x;
//		        }
//		        
//		        if(y > (this.imageHeight - this.cutImageHeight))
//		        {
//		        	tempCutHeight = this.imageHeight - this.cutImageHeight;
//		        }else
//		        {
//		        	tempCutHeight = y;
//		        }
//		        
//		        lblZoomimage.setIcon(new ImageIcon(zoomInImageTool(cutImage(bi, tempCutWidth, tempCutHeight), this.imageZoomTimes)));

				System.out.println("image size : x: " + bi.getWidth() + " y: " + bi.getHeight());
				Color c = new Color(bi.getRGB(x,y));
				rgb[0] = c.getRed();
				rgb[1] = c.getGreen();
				rgb[2] = c.getBlue();
						
				int red=c.getRed();
				int green=c.getGreen();
				int blue=c.getBlue();

				System.out.print("Red " + red + " Green " + green+ " Blue" + blue + "\n" );
				
				lblMessage.setText("x:" + x + "<br>y:" + y + "<br>Red " + red + "<br>Green " + green+ "<br>Blue" + blue );
				
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
	        
	        return rgb;   
	    }  

	  
	  public BufferedImage  zoomInImage(BufferedImage  originalImage, Integer times){

          int width = originalImage.getWidth()*times;

          int height = originalImage.getHeight()*times;

          BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());

          Graphics g = newImage.getGraphics();

          g.drawImage(originalImage, 0,0,width,height,null);

          g.dispose();

          return newImage;

      }
	  
	  public BufferedImage zoomInImageTool(BufferedImage originalImage, Integer times){
        BufferedImage bufferedImage = null;
        if(originalImage != null){
            bufferedImage = zoomInImage(originalImage,times);
        }
        return bufferedImage;
	  }
	  
	  public BufferedImage cutImage(BufferedImage originalImage, int locationX, int locationY)
	  {
		  BufferedImage cutImageBufferedImage = null;
		  cutImageBufferedImage = originalImage.getSubimage(locationX, locationY, this.cutImageWidth, this.cutImageHeight);
		  return cutImageBufferedImage;
	  }
	  
	  public void setZoomInImage(int x, int y)
	  {
		  BufferedImage bi ;
	        try {
				bi = ImageIO.read(file);

		        int tempCutWidth = 0;
		        int tempCutHeight = 0;
		        if(x > (this.imageWidth - this.cutImageWidth))
		        {
		        	tempCutWidth = this.imageWidth - this.cutImageWidth;
		        }
		        else if(x < (this.cutImageWidth / 2))
		        {
		        	x = 0;
		        }
		        else
		        {
		        	tempCutWidth = x - (this.cutImageWidth / 2);
		        }
		        
		        if(y > (this.imageHeight - this.cutImageHeight))
		        {
		        	tempCutHeight = this.imageHeight - this.cutImageHeight;
		        }
		        else if(y < (this.cutImageHeight / 2))
		        {
		        	y = 0;
		        }
		        else
		        {
		        	tempCutHeight = y - (this.cutImageHeight / 2);
		        }
		        
		        lblZoomimage.setIcon(new ImageIcon(zoomInImageTool(cutImage(bi, tempCutWidth, tempCutHeight), this.imageZoomTimes)));
		  				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
}
