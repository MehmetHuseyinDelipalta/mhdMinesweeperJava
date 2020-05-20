package com.mhd.minesweeperJava;
import sun.audio.AudioStream;
import sun.audio.AudioPlayer;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Random;
import java.net.URISyntaxException;
import java.net.URI;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Desktop;
import java.awt.Color;
import java.awt.BorderLayout;

public final class mhdMine implements ActionListener, MouseListener 
{
    private final JFrame mhdScreen; 
    private JFrame mhdNewScreen;
    private final JButton mhdSmile = new JButton("");
    private final JPanel mhdMixed = new JPanel();
    private final JPanel mhdTopBar = new JPanel();
    private JTextField mhdScoreIndicator=new JTextField();
    private JTextField mhdElapsedTime= new JTextField();
    ImageIcon mhdSmileIcon = null;
    ImageIcon mhdBlockIcon = null;
    ImageIcon mhdMineIcon = null;
    ImageIcon mhdLoserIcon = null;
    ImageIcon mhdUnhappyIcon = null;
    ImageIcon mhdOneIcon = null;
    ImageIcon mhdTwoIcon = null;
    ImageIcon mhdThreeIcon = null;
    ImageIcon mhdFourIcon = null;
    ImageIcon mhdFiveIcon = null;
    Timer mhdTimeDisplay = new Timer();
    Timer mhdTimeDisplay2 = new Timer();
    InputStream mhdExplosionMusic;
    InputStream mhdSelection;
    InputStream mhdBackroundMusic;
    
public mhdMine() 
{
    mhdPlayBackgroundMusic();
    mhdScreen = new JFrame("mhdMinesweeperJava");
    mhdScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mhdScreen.setVisible(true);
    mhdScreen.setLocation(500, 100);              
    mhdScreen.setResizable(false);             
    mhdScreen.setJMenuBar(mhdMenu());
    mhdMinesweeperImages();              
    mhdMixed.setLayout(new BorderLayout()); 
    mhdScoreIndicator = new JTextField(""+mhdMine);
    mhdScoreIndicator.setBackground(Color.YELLOW);
    mhdScoreIndicator.setForeground(Color.BLACK);
    mhdScoreIndicator.setFont(mhdScoreIndicator.getFont().deriveFont(Font.BOLD,19f));            
    mhdTopBar.add(mhdScoreIndicator);              
    JLabel mhdPass1 = new JLabel();
    mhdPass1.setPreferredSize(new Dimension(40, 30));
    mhdTopBar.add(mhdPass1);             
    mhdSmile.setPreferredSize(new Dimension(30, 30));
    mhdSmile.setIcon(mhdSmileIcon);
    mhdTopBar.add(mhdSmile);
    JLabel mhdPass2 = new JLabel();
    mhdPass2.setPreferredSize(new Dimension(60, 30));
    mhdTopBar.add(mhdPass2);             
    mhdElapsedTime = new JTextField("0");
    mhdElapsedTime.setBackground(Color.YELLOW);
    mhdElapsedTime.setForeground(Color.BLACK);
    mhdElapsedTime.setFont(mhdElapsedTime.getFont().deriveFont(Font.BOLD,19f));
    mhdElapsedTime.setPreferredSize(new Dimension(50, 25));
    mhdTopBar.add(mhdElapsedTime);
    mhdMixed.add(mhdTopBar, BorderLayout.NORTH);
    mhdSmile.addActionListener(this);
    mhdSmile.addMouseListener(this);
    mhdEditButton();
    mhdScreen.add(mhdMixed); 
    mhdScreen.pack();
    mhdStartTime();
}
          
public void mhdStartTime()
{
    TimerTask mhdConterTimerTask= new TimerTask()
    {
        int mhdNumber=0;
        @Override
        public void run()
        {
            mhdNumber++;
            String mhdConterTime = String.valueOf(mhdNumber);
            mhdElapsedTime.setText(mhdConterTime);
        }
    };
    mhdTimeDisplay.schedule(mhdConterTimerTask, 0,1000);
}

public void  mhdStopTheTime()
{
    mhdTimeDisplay.cancel();
    TimerTask sayacTask = new TimerTask() 
    {
        int mhdNumber=0;
        @Override
        public void run()
        {
            mhdNumber++;
            String mhdConterTime = String.valueOf(mhdNumber);
            mhdElapsedTime.setText(mhdConterTime);
        }
    };
    mhdTimeDisplay2.schedule(sayacTask, 0, 1000);
}

public void mhdResetTime()
{
    mhdElapsedTime.setText("0");
}      
       
public void mhdPlayBlastMusic()
{
    try 
    {
        mhdExplosionMusic = new FileInputStream("png/mhdExplosionMusicGame.wav");
        AudioStream mhdBoomMusic= new AudioStream(mhdExplosionMusic);
        AudioPlayer.player.start(mhdBoomMusic);
    }
    
    catch (FileNotFoundException ex)
    {
        Logger.getLogger(mhdMine.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    catch (IOException ex)
    {
        Logger.getLogger(mhdMine.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void mhdPlayClickedMusic()
{
    try
    {
        mhdSelection = new FileInputStream("png/mhdSelectionMusicGames.wav");
        AudioStream mhdClickedMusic= new AudioStream(mhdSelection);
        AudioPlayer.player.start(mhdClickedMusic);
    }
    
    catch (FileNotFoundException ex)
    {
        Logger.getLogger(mhdMine.class.getName()).log(Level.SEVERE, null, ex);
    } 
    
    catch (IOException ex)
    {
        Logger.getLogger(mhdMine.class.getName()).log(Level.SEVERE, null, ex);
    } 
}

public void mhdPlayBackgroundMusic()
{
    try
    {
        mhdBackroundMusic = new FileInputStream("png/mhdBackroundMusicGames.wav");
        AudioStream arkaplanMuzik= new AudioStream(mhdBackroundMusic);
        AudioPlayer.player.start(arkaplanMuzik);
    }
    
    catch (FileNotFoundException ex)
    {
        Logger.getLogger(mhdMine.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    catch (IOException ex) 
    {
        Logger.getLogger(mhdMine.class.getName()).log(Level.SEVERE, null, ex);
    } 
}

public void mhdMinesweeperImages()
{
    mhdSmileIcon = getScaledImage("png/mhdSmileIconGame.png");
    mhdBlockIcon = getScaledImage("png/mhdBlockIconGame.png");
    mhdMineIcon = getScaledImage("png/mhdMineIconGame.png");
    mhdLoserIcon = getScaledImage("png/mhdLoserIconGame.png");
    mhdUnhappyIcon = getScaledImage("png/mhdUnhappyIconGame.png");
    mhdOneIcon = getScaledImage("png/mhdOneIconGame.png");
    mhdTwoIcon = getScaledImage("png/mhdTwoIconGame.png");
    mhdThreeIcon = getScaledImage("png/mhdThreeIconGame.png");
    mhdFourIcon = getScaledImage("png/mhdFourIconGame.png");
    mhdFiveIcon = getScaledImage("png/mhdFiveIconGame.png");
}

public ImageIcon getScaledImage(String ResimIcon)
{
    ImageIcon imageIcon = new ImageIcon(ResimIcon);
    Image mhdImage = imageIcon.getImage();
    Image mhdNewImage = mhdImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
    imageIcon = new ImageIcon(mhdNewImage);
    return imageIcon;
}

public JMenuBar mhdMenu()
{
    JMenuBar mhdBar = new JMenuBar();
    JMenu oyun = new JMenu("Oyun"); 
    JMenu yardim = new JMenu("Yardım"); 
    final JMenuItem mhdNewGameIntent = new JMenuItem("Yeni Oyun");
    final JMenuItem mhdEasyGameIntent = new JMenuItem("Kolay");
    final JMenuItem mhdNormalGameIntent = new JMenuItem("Normal");
    final JMenuItem mhdDifficultGameIntent = new JMenuItem("Zor");
    final JMenuItem mhdManualAdjustmentIntent = new JMenuItem("Özelleştir");
    final JMenuItem mhdExitTheGameIntent = new JMenuItem("Kapat"); 
    final JMenuItem mhdHelp = new JMenuItem("Nasıl Oynanır ?"); 
    oyun.add(mhdNewGameIntent);
    oyun.add(mhdEasyGameIntent);
    oyun.add(mhdNormalGameIntent);
    oyun.add(mhdDifficultGameIntent);
    oyun.add(mhdManualAdjustmentIntent);
    oyun.add(mhdExitTheGameIntent); 
    yardim.add(mhdHelp);
    
    ActionListener MenuListener = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent mhdVariable)
        {
            if (mhdNewGameIntent == mhdVariable.getSource())
            {
                mhdResetTime();
                mhdRefresh();
            }
            
            if (mhdEasyGameIntent == mhdVariable.getSource())
            {
                mhdWidthButton = 10;
                mhdHeightButton = 10;
                mhdMine = 12;
                mhdScoreIndicator.setText(""+mhdMine);
                mhdResetTime();
                mhdRefresh();
            }
            
            if (mhdNormalGameIntent == mhdVariable.getSource())
            {
                mhdWidthButton = 20;
                mhdHeightButton = 20;
                mhdMine = 50;
                mhdScoreIndicator.setText(""+mhdMine);
                mhdResetTime();
                mhdRefresh();
            }
            
            if (mhdDifficultGameIntent == mhdVariable.getSource())
            {
                mhdWidthButton = 24;
                mhdHeightButton = 30;
                mhdMine = 100;
                mhdScoreIndicator.setText(""+mhdMine);
                mhdResetTime();
                mhdRefresh();
            }
            
            if (mhdManualAdjustmentIntent== mhdVariable.getSource())
            {
                mhdScreen.enable(false);
                mhdNewScreen = new JFrame("Özelleştir");
                mhdNewScreen.setVisible(true);
                mhdNewScreen.setResizable(false);
                mhdNewScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                mhdNewScreen.addWindowListener(new WindowAdapter()
               {
                   @Override
                   public void windowClosing(WindowEvent e)
                   {
                       System.out.println("Kapanıyor.");
                       mhdScreen.enable(true);
                       mhdScreen.setVisible(true);
                    }
        
                    @Override
                    public void windowClosed(WindowEvent e) 
                    {
                        System.out.println("Kapatıldı.");
                    }
                });
                
                mhdNewScreen.setBounds(300, 300, 250, 150);
                JPanel mhdBoard = new JPanel();
                mhdNewScreen.add(mhdBoard);
                mhdBoard.setLayout(null);
                
                JLabel mhdLength = new JLabel("Uzunluk:");
                mhdLength.setBounds(12, 16, 60, 20);
                mhdBoard.add(mhdLength);
                
                JLabel mhdWidth = new JLabel("Genişlik:")   ;       
                mhdWidth.setBounds(10, 45, 50, 15);
                mhdBoard.add(mhdWidth);
                               
                JLabel mhdMine_Label = new JLabel("Mayın:");
                mhdMine_Label.setBounds(10, 75, 50, 15);
                mhdBoard.add(mhdMine_Label);
                               
                JTextField mhdLengthCopy= new JTextField(""+mhdHeightButton);
                mhdLengthCopy.setBounds(70,12,50,20);
                mhdBoard.add(mhdLengthCopy);
                                
                JTextField mhdWidthCopy= new JTextField(""+mhdWidthButton);
                mhdWidthCopy.setBounds(70,42,50,20);
                mhdBoard.add(mhdWidthCopy);
                               
                JTextField mhdMineText= new JTextField(""+mhdMine);
                mhdMineText.setBounds(70,72,50,20);
                mhdBoard.add(mhdMineText);
                        
                JButton mhdCreateButton = new JButton("Oluştur");
                mhdCreateButton.setBounds(130, 12, 90, 20);
                mhdBoard.add(mhdCreateButton);
                              
                mhdCreateButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                {
                    mhdHeightButton = Integer.valueOf(mhdLengthCopy.getText());
                    mhdWidthButton = Integer.valueOf(mhdWidthCopy.getText());                    
                    mhdMine = Integer.valueOf(mhdMineText.getText());
                    mhdScoreIndicator.setText(""+mhdMine);
                    mhdRefresh();
                    mhdResetTime();
                    mhdNewScreen.setVisible(false);
                    mhdScreen.setVisible(true);
                    mhdScreen.enable(true);
                }
                });
                JButton mhdGiveUpButton = new JButton("Vazgeç");
                mhdGiveUpButton.setBounds(130, 42, 90, 20);
                mhdBoard.add(mhdGiveUpButton);
                              
                mhdGiveUpButton.addActionListener(new ActionListener()
                {                
                  @Override
                  public void actionPerformed(ActionEvent e)
                  {
                      mhdNewScreen.setVisible(false);
                      mhdScreen.setVisible(true);
                      mhdScreen.enable(true);
                  }
                });
                                        
            }
            
            if (mhdExitTheGameIntent == mhdVariable.getSource())
            {
                if (mhdScreen != null)
                {
                    mhdScreen.dispose();
                }
                
                System.exit(0);
                
            }
            
            if (mhdHelp == mhdVariable.getSource())
            {
                Desktop d = Desktop.getDesktop();
                String mhdHowToPlayInternetAddress = "https://tr.wikipedia.org/wiki/May%C4%B1n_Tarlas%C4%B1";
                
                try 
                {
                    d.browse(new URI(mhdHowToPlayInternetAddress));
                }
                
                catch (URISyntaxException | IOException ex)
                {
                    Logger.getLogger(mhdMine.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        } 
    };
 
    mhdNewGameIntent.addActionListener(MenuListener);
    mhdEasyGameIntent.addActionListener(MenuListener);
    mhdNormalGameIntent.addActionListener(MenuListener);
    mhdDifficultGameIntent.addActionListener(MenuListener);
    mhdManualAdjustmentIntent.addActionListener(MenuListener);
    mhdExitTheGameIntent.addActionListener(MenuListener);
    mhdHelp.addActionListener(MenuListener);
    mhdBar.add(oyun);
    mhdBar.add(yardim);
    return mhdBar;
}        
        private int mhdWidthButton = 10; 
        private int mhdHeightButton = 10; 
        private int mhdMine = 12;      
        int[][] mhdMineArray; 
        JButton[][] mhdButtonArray;
        JPanel mhdMineMixingArray = null; 
        public void mhdEditButton() {
            mhdMineArray = new int[mhdWidthButton][mhdHeightButton];
            mhdButtonArray = new JButton[mhdWidthButton][mhdHeightButton];
            boolean mhdGo = true;
            if (mhdMineMixingArray != null) {
                mhdMixed.remove(mhdMineMixingArray);
                mhdMineMixingArray = null;
                mhdGo = false; 
              }
              mhdMineMixingArray = new JPanel();
              mhdMineMixingArray.setLayout(new GridLayout(mhdWidthButton, mhdHeightButton)); 
            for (int i = 0; i < mhdWidthButton; i++) {
                for (int j = 0; j < mhdHeightButton; j++) {
                    mhdMineArray[i][j] = 0;
                    mhdButtonArray[i][j] = new JButton("");
                         
                    mhdButtonArray[i][j].setPreferredSize(new Dimension(16, 16));
                    mhdButtonArray[i][j].addActionListener(this);
                    mhdButtonArray[i][j].addMouseListener(this);
                    mhdMineMixingArray.add(mhdButtonArray[i][j]);
                    }
              } 
                    mhdMineMixingArray.setVisible(true);
                    mhdMixed.add(mhdMineMixingArray, BorderLayout.CENTER);
              if (mhdGo) {
                  mhdMineBuilder(mhdButtonArray);
              }
                  mhdScreen.pack();
       }
        public void mhdRefresh() 
        {
            mhdResetTime();
            mhdSmile.setIcon(mhdSmileIcon);
            mhdEditButton();
            
            for (int i = 0; i < mhdWidthButton; i++)
            {
                for (int j = 0; j < mhdHeightButton; j++)
                {
                    mhdMineArray[i][j] = 0;
                    mhdButtonArray[i][j].addActionListener(this);
                    mhdButtonArray[i][j].addMouseListener(this);
                    mhdButtonArray[i][j].setText("");
                }
              }
            mhdMineBuilder(mhdButtonArray);
            System.out.println("");
            System.out.println("");
        }
        
        public void mhdMineBuilder(JButton button[][])
        {
            int[] mhdMineMethod = mhdGenerateRandom(mhdWidthButton, mhdHeightButton, mhdMine);
            int mhdNumber = 1;
            for (int i = 0; i < mhdWidthButton; i++)
            {
                for (int j = 0; j < mhdHeightButton; j++)
                {
                      for (int k = 0; k < mhdMineMethod.length && mhdMineMethod[k] != 0; k++)
                      {
                          if (mhdNumber == mhdMineMethod[k]) 
                      {
                          mhdMineArray[i][j] = 9;
                      }
                          
                      }
                          mhdNumber++; 
                      }
            }
            
            int mhdNumberOfBoxes = 0;
            for (int i = 0; i < mhdWidthButton; i++)
            {
                for (int j = 0; j < mhdHeightButton; j++)
                {
                    mhdNumberOfBoxes = 0;
                    if (mhdMineArray[i][j] != 9)
                    {
                        if (i > 0 && j > 0)
                        {
                            if (mhdMineArray[i - 1][j - 1] == 9)
                                mhdNumberOfBoxes++;
                        }
                        
                        if (i > 0)
                        {
                            if (mhdMineArray[i - 1][j] == 9)
                                mhdNumberOfBoxes++;
                        } 
                        
                        if (i > 0 && j < mhdHeightButton - 1)
                        {
                            if (mhdMineArray[i - 1][j + 1] == 9)
                                mhdNumberOfBoxes++;
                        }
                        
                        if (i < mhdWidthButton - 1 && j > 0)
                        {
                            if (mhdMineArray[i + 1][j - 1] == 9)
                                mhdNumberOfBoxes++;
                        }
                        
                        if (i < mhdWidthButton - 1)
                        {
                            if (mhdMineArray[i + 1][j] == 9)
                                mhdNumberOfBoxes++;
                        }
                        
                        if (i < mhdWidthButton - 1 && j < mhdHeightButton - 1)
                        {
                            if (mhdMineArray[i + 1][j + 1] == 9)
                                mhdNumberOfBoxes++;
                        }
                        
                        if (j > 0)
                        {
                            if (mhdMineArray[i][j - 1] == 9)
                                mhdNumberOfBoxes++;
                        }
                        
                        if (j < mhdHeightButton - 1)
                        {
                            if (mhdMineArray[i][j + 1] == 9)
                                mhdNumberOfBoxes++;
                        }
                        
                        mhdMineArray[i][j] = mhdNumberOfBoxes;
                    }
                }
            } 
            
            for (int i = 0; i < mhdWidthButton; i++)
            {
                for (int j = 0; j < mhdHeightButton; j++)
                {
                    System.out.print(" " + mhdMineArray[i][j]);
                }
                    System.out.println("");
            } 
        }

public int[] mhdGenerateRandom(int mhdButtonWidth, int mhdButtonHeight, int mhdMine)
{
    Random rand = new Random();
    int[] mhdRandomMine = new int[mhdButtonWidth * mhdButtonHeight];
    boolean mhdTesting = false;
    int mhdNumber = 0;
    
    while (mhdNumber < mhdMine)
    {
        int   mhdRandomNumber = (int) ((mhdButtonWidth * mhdButtonHeight) * (rand.nextDouble())) + 1;
        mhdTesting = false;
        
        for (int i = 0; i < mhdNumber; i++)
        {
            if (mhdRandomMine[i] == mhdRandomNumber)
            {
                mhdTesting = true;
                break;
            }
        }
        
        if (!mhdTesting)
        {
            mhdRandomMine[mhdNumber++] = mhdRandomNumber;
        }
    }              
    return mhdRandomMine;
}

@Override
public void actionPerformed(ActionEvent mhdVariable)
{
    if (mhdVariable.getSource() == mhdSmile)
    {
        mhdRefresh();
    } 
    
    else
    {
        for (int i = 0; i < mhdWidthButton; i++)
            for (int j = 0; j < mhdHeightButton; j++)
            {
                if (mhdButtonArray[i][j] == mhdVariable.getSource())
                {
                    if (mhdMineArray[i][j] == 9)
                    {
                        for (int k = 0; k < mhdWidthButton; k++)
                        {
                            for (int l = 0; l < mhdHeightButton; l++)
                            {
                                if (mhdMineArray[k][l] == 9)
                                {
                                    mhdButtonArray[k][l].setIcon(mhdMineIcon);
                                }
                                
                                mhdButtonArray[k][l].removeActionListener(this);
                                mhdButtonArray[k][l].removeMouseListener(this); 
                            }
                        } 
                    }
                    
                    if (mhdMineArray[i][j] == 1)
                    {
                        mhdButtonArray[i][j].setIcon(mhdOneIcon);
                    }
                    
                    if (mhdMineArray[i][j] == 2)
                    {
                        mhdButtonArray[i][j].setIcon(mhdTwoIcon);
                    }
                    
                    if (mhdMineArray[i][j] == 3)
                    {
                        mhdButtonArray[i][j].setIcon(mhdThreeIcon);
                    }
                    
                    if (mhdMineArray[i][j] == 4)
                    {
                        mhdButtonArray[i][j].setIcon(mhdFourIcon);
                    }
                    
                    if (mhdMineArray[i][j] == 5)
                    {
                        mhdButtonArray[i][j].setIcon(mhdFiveIcon);
                    }
                    
                    if (mhdMineArray[i][j] == 0)
                    {
                        mhdFindEmptyBox(i, j);
                    }
                }
            }
    }
}

public void mhdFindEmptyBox(int mhdBoxX, int mhdBoxY)
{
    int[] mhdCountX = new int[(mhdWidthButton) * (mhdHeightButton)];
    int[] mhdCountY = new int[(mhdWidthButton) * (mhdHeightButton)];
    int mhdSpace = 0;
    
    for (int i = 0; i < ((mhdWidthButton) * (mhdHeightButton)); i++)
    {
        mhdCountX[i] = -1;
        mhdCountY[i] = -1;
    }
    
    mhdCountX[mhdSpace] = mhdBoxX;
    mhdCountY[mhdSpace] = mhdBoxY;
    mhdSpace++;
    
    for (int i = 0; i < mhdSpace; i++)
    {
        if (mhdCountX[i] > 0)
        {
            int mhdXxx = mhdCountX[i] - 1;
            int mhdYyy = mhdCountY[i];
            
            if (mhdMineArray[mhdXxx][mhdYyy] == 0)
            {
                if (!mhdDiscover(mhdCountX, mhdCountY, mhdSpace, mhdXxx, mhdYyy))
                {
                    mhdCountX[mhdSpace] = mhdXxx;
                    mhdCountY[mhdSpace] = mhdYyy;
                    mhdSpace++;
                }
            }
        }
        
        if (mhdCountX[i] < (mhdWidthButton - 1))
        {
            int mhdXxx = mhdCountX[i] + 1;
            int mhdYyy = mhdCountY[i];
            
            if (mhdMineArray[mhdXxx][mhdYyy] == 0)
            {
                if (!mhdDiscover(mhdCountX, mhdCountY, mhdSpace, mhdXxx, mhdYyy))
                {
                    mhdCountX[mhdSpace] = mhdXxx;
                    mhdCountY[mhdSpace] = mhdYyy;
                    mhdSpace++;
                }
            }
        }
        
        if (mhdCountY[i] > 0)
        {
            int mhdXxx = mhdCountX[i];
            int mhdYyy = mhdCountY[i] - 1;
            
            if (mhdMineArray[mhdXxx][mhdYyy] == 0)
            {
                if (!mhdDiscover(mhdCountX, mhdCountY, mhdSpace, mhdXxx, mhdYyy))
                {
                    mhdCountX[mhdSpace] = mhdXxx;
                    mhdCountY[mhdSpace] = mhdYyy;
                    mhdSpace++;
                }
            }
            
        }
        
        if (mhdCountY[i] < (mhdHeightButton - 1))
        {
            int mhdXxx = mhdCountX[i];
            int mhdYyy = mhdCountY[i] + 1;
            
            if (mhdMineArray[mhdXxx][mhdYyy] == 0)
            {
                if (!mhdDiscover(mhdCountX, mhdCountY, mhdSpace, mhdXxx, mhdYyy))
                {
                    mhdCountX[mhdSpace] = mhdXxx;
                    mhdCountY[mhdSpace] = mhdYyy;
                    mhdSpace++;
                }
            }
        }
    }
    
    for (int k = 0; k < mhdSpace; k++)
    {
        mhdButtonArray[mhdCountX[k]][mhdCountY[k]].setBackground(new Color(200, 200, 250));
    } 
}

public boolean mhdDiscover(int[] mhdCountX, int[] mhdCountY, int mhdCountIt, int mhdXxx, int mhdYyy)
{
    int j = 0;
    
    for (j = 0; j < mhdCountIt; j++)
    {
        if ((mhdCountX[j] == (mhdXxx)) && (mhdCountY[j] == (mhdYyy)))
        {
            return true;
        }
    }
    return false;
}

@Override
public void mouseClicked(MouseEvent arg0)
{}

@Override
public void mousePressed(MouseEvent me)
{
    for (int i = 0; i < mhdWidthButton; i++)
        for (int j = 0; j < mhdHeightButton; j++)
        {
            if (mhdButtonArray[i][j] == me.getSource())
            {
                mhdSmile.setIcon(mhdUnhappyIcon);
            }
        }
    if (me.getSource() == mhdSmile)
    {
        mhdSmile.setIcon(mhdUnhappyIcon);
    }
}

@Override
public void mouseReleased(MouseEvent me)
{
    if (me.getSource() == mhdSmile)
    {
        mhdSmile.setIcon(mhdSmileIcon);
    }
    
    for (int i = 0; i < mhdWidthButton; i++)
        
        for (int j = 0; j < mhdHeightButton; j++)
        {
            if (mhdButtonArray[i][j] == me.getSource())
            {
                if (mhdMineArray[i][j] == 9)
                {
                    mhdPlayBlastMusic();
                    mhdSmile.setIcon(mhdLoserIcon);
                }
                
                else
                {
                    mhdPlayClickedMusic();
                    mhdSmile.setIcon(mhdSmileIcon);
                }
            } 
        }                    
}

@Override
public void mouseEntered(MouseEvent arg0)
{

}

@Override
public void mouseExited(MouseEvent arg0)
{

}

public static void main(String[] args)
{
    mhdMine baglanti=new mhdMine();
}
}
