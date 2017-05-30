import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.text.*;
public class MovieTheatre extends JApplet implements ListSelectionListener, ItemListener, ActionListener
{
   Container con;
	
   JRadioButton rG, rAll, rPG, r14A, r18A , rR, rAdult, rChild;
   
   JButton bSelect, bClear, bClearAll, bSubmit, bM, bN, bO, bP, bQ, bR, bS, bT, bEnter, bReset;
   
   ButtonGroup rateGroup, genGroup;
   
   JPanel pMain, pRating, pSelectMovie, pShowTime, pShowTime1, pShowTime2, pImg, pSelection, pSelection1, pSelection2, 
         pTotal, pSubTime, pTickets, pTime, pLaTotal, pPassword, pPassword1, pPassword2, pPassword3;
   
   JLabel laImg, laTotal;
   
   JScrollPane jSP, jSPTime, jSPSelect;
   JComboBox cbTickets;
   JList lMovie, lTime, lSelected;
   
   JPasswordField tCredit, tPass;
   Vector vtMNames, vtTimes, vtSelected, vtSltedMov, vtSltedPrice;
   
   MovieList movList;
   
   Image imgMovie;
   ImageIcon bugImg;
   double accumulator;
   double total;
   
   String pass = "";
   String passcode; 
   int selectCounter = 0;
   
   TitledBorder border;
   
   UpdateFrame movStat; 
   NumberFormat dollar;
   
   public void setMovieList(UpdateFrame movie)
   {
      movList.mList = movie.movList.mList;
   }
   
   public void init()
   {
      dollar = NumberFormat.getCurrencyInstance();
      movStat = new UpdateFrame("Movie Status");
      pMain = new JPanel();
      pMain.setLayout(new GridLayout(2, 3));  //Main panel layout
      
   	//=============== Add radio button ===================//
      pRating = new JPanel();
      pRating.setLayout(new GridLayout(3,2,5,5));
      
      rAll = new JRadioButton("All", true);
      rAll.addItemListener(this);
      pRating.add(rAll);
   	
      rG = new JRadioButton("G", false);
      rG.addItemListener(this);
      pRating.add(rG);
   	
      rPG = new JRadioButton("PG", false);
      rPG.addItemListener(this);
      pRating.add(rPG);
      
      r14A = new JRadioButton("14A", false);
      r14A.addItemListener(this);
      pRating.add(r14A);
      
      r18A = new JRadioButton("18A", false);
      r18A.addItemListener(this);
      pRating.add(r18A);
      
      rR = new JRadioButton("R", false);
      rR.addItemListener(this);
      pRating.add(rR);
      
      //=========== Add button in group =========//
      rateGroup = new ButtonGroup();
      rateGroup.add(rAll);
      rateGroup.add(rG);
      rateGroup.add(rPG);
      rateGroup.add(r14A);
      rateGroup.add(r18A);
      rateGroup.add(rR);
   
      //Set Rating panel titled border
      pRating.setBorder(new TitledBorder("Select Movie Rating"));
      pMain.add(pRating);
      
      
      //========== Movie List Box===============//
      pSelectMovie = new JPanel();
      
      vtMNames = new Vector(); //Vector for adding movie names
      movList = new MovieList();
      movList.createList();
      
      for (int i = 0; i < movList.mList.length; i++)  // Get the movie title in to the movie name vector
      {
         vtMNames.add(movList.mList[i].getMovieTitle());
      }
      lMovie = new JList(vtMNames);
       
      lMovie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      lMovie.setVisibleRowCount(18);
      
      jSP = new JScrollPane(lMovie);
      jSP.setBorder(new TitledBorder("Select Movie"));
      
      lMovie.addListSelectionListener(this);
      pMain.add(jSP);
      
      //========= Select Show Times ==============//
      final int MAX_TICKETS = 5; // Maximun tickets for selection
      pShowTime = new JPanel();
      pShowTime.setLayout(new GridLayout(2,1));
      
      pShowTime1 = new JPanel();
      pShowTime1.setLayout(new GridLayout(1,2));
      
      pSubTime = new JPanel();
      pSubTime.setLayout(new BorderLayout());
      
      pTickets = new JPanel();
      pTickets.setLayout(new BorderLayout());
      
      pTime = new JPanel();
      pTime.setLayout(new BorderLayout());
      
      vtTimes = new Vector(); //Vector for times
      lTime = new JList();
      
      MovieInfo.setAdultPrice(5.50);  //Default adult price
      rAdult = new JRadioButton("Adult- $" + MovieInfo.getAdultPrice(), true);
      
      MovieInfo.setChildPrice(3.95);  //Default child price
      rChild = new JRadioButton("Child- $" + MovieInfo.getChildPrice());
      
      //Button group for adult and child price
      genGroup = new ButtonGroup();
      genGroup.add(rAdult);
      genGroup.add(rChild);
      
      cbTickets = new JComboBox();
      
      for (int i = 1; i <= MAX_TICKETS; i++)
      {
         cbTickets.addItem(i);
      }
      
      cbTickets.setSelectedItem(1);  //Set the default for number of tickets
      
      bSelect = new JButton("Select");
      bSelect.addActionListener(this);
      
      pTickets.add(rAdult, BorderLayout.LINE_START);
      pTickets.add(rChild);
      pTickets.add(cbTickets, BorderLayout.SOUTH);
      pTickets.setBorder(new TitledBorder("Tickets"));
      
      lTime = new JList(vtTimes);
      lTime.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      lTime.setFixedCellWidth(getWidth());
      lTime.setFixedCellHeight(30);
      
      jSPTime = new JScrollPane(lTime);
      jSPTime.setBorder(new TitledBorder("Select Show Time"));
      
      pTime.add(jSPTime);
      
      pShowTime1.add(pTime);
      pShowTime1.add(pTickets);
      
      
      pShowTime.add(pShowTime1);
      pShowTime.add(bSelect);
      
      pMain.add(pShowTime);
      
      //========== Image Load ================//
      pImg = new JPanel();
      
      laImg = new JLabel(bugImg);
      border = new TitledBorder("None");
      border.setTitleJustification(TitledBorder.CENTER);
      
      pImg.setBorder(border);
      pImg.add(laImg);
      pImg.setBackground(Color.YELLOW);
   
      pMain.add(pImg);
      
      //========= Your Selection ===========//
      pSelection = new JPanel();
      pSelection.setLayout(new GridLayout(2, 1));
      pSelection.setBorder(new TitledBorder("Your Selection"));
      
      pSelection1 = new JPanel();
      pSelection1.setLayout(new FlowLayout());
      pSelection.setVisible(true);
      pSelection2 = new JPanel();
      pSelection2.setLayout(new GridLayout(1, 2)); 
      
      bClear = new JButton("Clear");
      bClear.addActionListener(this);
      
      bClearAll = new JButton("Clear All");
      bClearAll.addActionListener(this);
      
      
      vtSelected = new Vector();
      vtSltedMov = new Vector();
      vtSltedPrice = new Vector();
      lSelected = new JList(vtSelected);
      jSPSelect = new JScrollPane(lSelected);
      
      pSelection2.add(bClear);
      pSelection2.add(bClearAll);
      pSelection.add(jSPSelect);
      pSelection.add(pSelection2);
      pMain.add(pSelection);
      
      //================ Total ==================// 
      passcode = "m";   // Log in UpdateFrame password
      
      //Totals
      pTotal = new JPanel();
      pTotal.setLayout(new GridLayout(4, 1));
      
      pLaTotal = new JPanel();  //Total label
      pLaTotal.setLayout(new BorderLayout());
      
      laTotal = new JLabel("Totals: $0.00");
      laTotal.setForeground(Color.BLACK);
      laTotal.setFont(new Font("Time New Roman", Font.BOLD, 40));
   
      pLaTotal.add(laTotal, BorderLayout.LINE_START);
      pLaTotal.setBackground(Color.CYAN);
      
      //Credit card
      tCredit = new JPasswordField();
      tCredit.setBorder(new TitledBorder("Enter Credit Card #:"));
      tCredit.setEchoChar('*');
      
      //Password
      pPassword = new JPanel();
      pPassword.setBorder(new TitledBorder("For Employee use ONLY"));
      pPassword.setLayout(new GridLayout(2, 1));
      pPassword.setBackground(Color.RED);
      
      pPassword1 = new JPanel();
      pPassword1.setLayout(new GridLayout(2, 4));
      
      pPassword2 = new JPanel();
      pPassword2.setLayout(new GridLayout(1, 2));
      
      pPassword3 = new JPanel();
      pPassword3.setLayout(new GridLayout(2, 1));
      
      bSubmit = new JButton("Submit");
      bSubmit.addActionListener(this);
      
      // Button for password input
      bM = new JButton("m");
      bM.addActionListener(this);
      
      bN = new JButton("n");
      bN.addActionListener(this);
      
      bO = new JButton("o");
      bO.addActionListener(this);
      
      bP = new JButton("p");
      bP.addActionListener(this);
      
      bQ = new JButton("q");
      bQ.addActionListener(this);
      
      bR = new JButton("r");
      bR.addActionListener(this);
      
      bS = new JButton("s");
      bS.addActionListener(this);
      
      bT = new JButton("t");
      bT.addActionListener(this);
      
      pPassword1.add(bM);
      pPassword1.add(bN);
      pPassword1.add(bO);
      pPassword1.add(bP);
      pPassword1.add(bQ);
      pPassword1.add(bR);
      pPassword1.add(bS);
      pPassword1.add(bT);
      
      //Passwordfield
      tPass = new JPasswordField();
      pPassword2.add(tPass);
      
      bEnter = new JButton("Enter");
      bEnter.addActionListener(this);
      bReset = new JButton("Reset");
      bReset.addActionListener(this);
      
      pPassword3.add(bEnter);
      pPassword3.add(bReset);
      pPassword2.add(pPassword3);
      
      pPassword.add(pPassword1);
      pPassword.add(pPassword2);
      
      pTotal.add(pLaTotal);
      pTotal.add(tCredit);
      pTotal.add(bSubmit);
      pTotal.add(pPassword);
      
      pMain.add(pTotal);
      
      // Add panel in to Container 
      movStat = new UpdateFrame("Movie Status");
      movStat.setSize(400, 600);
      
      con = getContentPane();  
      con.add(pMain);
   }
      
   
   //=========================================================================
   //	                        Event Handling
   //=========================================================================
   public void itemStateChanged(ItemEvent e)
   {
      //Update adult and child price 
      rAdult.setText("Aduld: " + dollar.format(MovieInfo.getAdultPrice()));
      rChild.setText("Child: " + dollar.format(MovieInfo.getChildPrice()));
      
      //Update the data from UpdateFrame
      for (int i = 0; i < movList.mList.length; i++)
      {
         movList.mList[i].setMovieRating(movStat.movList.mList[i].getMovieRating());
         movList.mList[i].setShowTimes(movStat.movList.mList[i].getShowTimes());
      }
       
      
      if(e.getSource() instanceof JRadioButton)
      {
         vtMNames.clear();
         
         if (rAll.isSelected())
         {
            for (int i = 0; i < movList.mList.length; i++)
            {
               vtMNames.add(movList.mList[i].getMovieTitle());
            }
         }
         else
            if(rG.isSelected())
            {
               for (int i = 0; i < movList.mList.length; i++)
               {
                  if(movList.mList[i].getMovieRating().equals("G"))
                     vtMNames.add(movList.mList[i].getMovieTitle());
               }
            }
            else 
               if (rPG.isSelected())
               {
                  for (int i = 0; i < movList.mList.length; i++)
                  {
                     if(movList.mList[i].getMovieRating().equals("PG"))
                        vtMNames.add(movList.mList[i].getMovieTitle());
                  }
               }
               else
                  if(r14A.isSelected())
                  {
                     for (int i = 0; i < movList.mList.length; i++)
                     {
                        if(movList.mList[i].getMovieRating().equals("14A"))
                           vtMNames.add(movList.mList[i].getMovieTitle());
                     }
                  }
                  else
                     if(rPG.isSelected())
                     {
                        for (int i = 0; i < movList.mList.length; i++)
                        {
                           if(movList.mList[i].getMovieRating().equals("PG"))
                              vtMNames.add(movList.mList[i].getMovieTitle());
                        }
                     }
                     else
                        if(r18A.isSelected())
                        {
                           for (int i = 0; i < movList.mList.length; i++)
                           {
                              if(movList.mList[i].getMovieRating().equals("18A"))
                                 vtMNames.add(movList.mList[i].getMovieTitle());
                           }
                        }
                        else
                           if(rR.isSelected())
                           {
                              for (int i = 0; i < movList.mList.length; i++)
                              {
                                 if(movList.mList[i].getMovieRating().equals("R"))
                                    vtMNames.add(movList.mList[i].getMovieTitle());
                              }
                           }
      }
      lMovie.setListData(vtMNames);
   }  // End itemStateChanged event
   
   //========= JList ========//
   public void valueChanged(ListSelectionEvent e)
   {
   
      if(e.getSource() == lMovie)
      {
         vtTimes.clear();
         if (lMovie.getSelectedIndex() != -1)
         {
            String movName = lMovie.getSelectedValue().toString();
         
            for(int i = 0; i < movList.mList.length; i++)
            {
               if (movList.mList[i].getMovieTitle().equals(movName))
               {
                  String mTime = movList.mList[i].getShowTimes();
                  for (int a = 0; a < mTime.length(); a++)
                  {
                     if (mTime.substring(a, a + 1).equals("y"))
                     {
                        if (a == 0)
                           vtTimes.addElement("10.00am");
                        else
                           if (a == 1)
                              vtTimes.addElement("3.00pm");
                           else
                              if(a == 2)
                                 vtTimes.addElement("7.00pm");
                              else
                                 if (a == 3)
                                    vtTimes.addElement("11.30pm - 7% discount");
                     
                     }
                     else
                     {
                        if (mTime.substring(a, a + 1).equals("n"))
                           vtTimes.addElement("-----");
                     }
                  }
               
                  lTime.setListData(vtTimes);
               
                  imgMovie = getImage(getCodeBase(), movList.mList[i].getMovieImg());
                  bugImg = new ImageIcon(imgMovie);
                  laImg.setIcon(bugImg);
                  border = new TitledBorder("Rating: " + movList.mList[i].getMovieRating());
                  border.setTitleJustification(TitledBorder.CENTER);
                  pImg.setBorder(border);
               
               }
            }
         }
      }
      
      if(e.getSource() == lTime)
      {
         if(!String.valueOf(lTime.getSelectedValue()).equals("-----"))
            lTime.setSelectionBackground(Color.BLUE);
         else
            lTime.setSelectionBackground(Color.RED);
      }
   }  //End valueChanged event
   
   public void actionPerformed(ActionEvent e)
   {
     // Select Button
      if (e.getSource() == bSelect)
      {
         int ticNum;
         String sltedTime;
         String movName;
         String sltedMov; 
      
         movName = String.valueOf(lMovie.getSelectedValue());
         ticNum = (int) cbTickets.getSelectedItem();
         sltedTime = String.valueOf(lTime.getSelectedValue());
         
         if(lTime.isSelectionEmpty())
            JOptionPane.showConfirmDialog(lMovie, "Did NOT select Show Time", "Select Show Time Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
         else
         {
            if(sltedTime.equals("-----"))
               JOptionPane.showConfirmDialog(lMovie, "Invalid Show Time", "Select Show Time Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
               
            else
            {
               if(sltedTime.equals("11.30pm - 7% discount"))
               {
                  if(rAdult.isSelected())
                  {
                     total = (MovieInfo.getAdultPrice() * ticNum) * (1 - 0.07);
                     vtSltedMov.addElement(movName);
                     vtSltedPrice.addElement(total);
                     selectCounter++;
                  }
                  else
                     if(rChild.isSelected()) 
                        JOptionPane.showConfirmDialog(lTime, "Children NOT allowed for 11.00pm showing", "Select Time Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
               }
               else
               {
                  if(rAdult.isSelected())
                  {
                     total = (MovieInfo.getAdultPrice() * ticNum);
                     vtSltedMov.addElement(movName);
                     vtSltedPrice.addElement(total);
                     selectCounter++;
                  }
                  else
                     if((!r18A.isSelected()) || (!rR.isSelected()))
                     {
                        total = (MovieInfo.getChildPrice() * ticNum);
                        vtSltedMov.addElement(movName);
                        vtSltedPrice.addElement(total);
                        selectCounter++;
                     }   
                     else
                        JOptionPane.showConfirmDialog(lTime, "Children NOT allowed for 18A or R rated movie", "Select Movie Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
               }
            
               
               if(selectCounter == 1)
               {
                  vtSelected.addElement(movName + " ===> " +  String.valueOf(dollar.format(total)));
               }
               else
               {
                  for(int p = 0; p < vtSltedMov.size() - 1; p++)
                  {
                     int compare = movName.compareTo(String.valueOf(vtSltedMov.elementAt(p)));
                     
                     if(compare == 0)
                     {
                        int result;
                                 
                        result = JOptionPane.showConfirmDialog(lMovie, "WARNING: You have already selected this movie", "Duplicate", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE);
                        if(result == JOptionPane.OK_OPTION)
                           vtSelected.addElement(movName + " ===> " +  String.valueOf(dollar.format(total)));
                           
                        p = vtSltedMov.size() - 1;
                        
                     }
                     else
                     {
                        p = vtSltedMov.size() - 1;
                        vtSelected.addElement(movName + " ===> " +  String.valueOf(dollar.format(total)));
                     }
                  }            	   
               }
               accumulator += total;
            }
         }
            
         laTotal.setText("Totals:" + String.valueOf(dollar.format(accumulator)));
         lSelected.setListData(vtSelected);
      }  //------------------------------------------------------------------------------End Select Button
      else
         if(e.getSource() == bClearAll)    //Clear All button
         {
            int result = JOptionPane.showConfirmDialog(lSelected, "Are YOU SURE?", "WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
          
            if(result == JOptionPane.YES_OPTION)
            {
               vtSelected.clear();
               accumulator = 0;
               lSelected.setListData(vtSelected);
            }
         } //------------------------------------------------------------------------------End Clear All Button
         else
         {
            if (e.getSource() == bClear)
            {
               if(lSelected.getSelectedIndex() != -1)
               {
                  int idx = lSelected.getSelectedIndex();
                  double priceRemove = 0.0;
                  for(int i = 0; i < vtSltedPrice.size(); i++)
                  {
                     if (i == idx)
                     {
                        priceRemove = Double.parseDouble((String.valueOf(vtSltedPrice.elementAt(lSelected.getSelectedIndex()))));
                        vtSltedPrice.remove(i);
                        vtSltedMov.remove(i);
                     
                     }
                  }
                  accumulator -= priceRemove;
                  vtSelected.remove(lSelected.getSelectedIndex());
                  laTotal.setText("Totals:" + String.valueOf(dollar.format(accumulator)));
                  lSelected.setListData(vtSelected);
               }
               else
                  JOptionPane.showConfirmDialog(lSelected, "Please Select a movie tp Clear", "Not Allowed", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
         } //------------------------------------------------------------------------------End Clear Button
      
      // Password input Alphabet button
      if(e.getSource() == bM)
         pass += bM.getText();
      else
         if(e.getSource() == bN)
            pass += bN.getText();
         else
            if(e.getSource() == bO)
               pass += bO.getText();
            else
               if(e.getSource() == bP)
                  pass += bP.getText();
               else
                  if(e.getSource() == bQ)
                     pass += bQ.getText();
                  else
                     if(e.getSource() == bR)
                        pass += bR.getText();
                     else
                        if(e.getSource() == bS)
                           pass += bS.getText();
                        else
                           if(e.getSource() == bT)
                              pass += bT.getText();
      tPass.setText(pass);
      tPass.setEchoChar('*');   
      
      // Enter Button 
      if(e.getSource() == bEnter)
      {
         if(pass.equals(passcode))
         {
            movStat.setVisible(true);
            rateGroup.clearSelection();
          
            vtMNames.clear();
            lMovie.setListData(vtMNames);
            
            cbTickets.setSelectedItem(1);
            
            rAdult.setText("Adult-");
            rChild.setText("Child-");
            rAdult.isSelected();
            
            border = new TitledBorder("None");
            border.setTitleJustification(TitledBorder.CENTER);
            laImg.setIcon(null);
            laImg.setBorder(border);
            
            pass = "";
            tPass.setText(pass);
         }
         else
         {
            JOptionPane.showConfirmDialog(tPass, "WRONG Password, please re-enter", "Not Allowed", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            pass = "";
            tPass.setText(pass);
         }
      } //------------------------------------------------------------------------------End Enter Button
      else
         if(e.getSource() == bReset)  // Reset button
         {
            pass = "";
            tPass.setText(pass);
         }
   	   
      if(e.getSource() == bSubmit)
      {
         int result;
         result = JOptionPane.showConfirmDialog(tCredit, "Are You SURE?", "WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
         
         if(result == JOptionPane.YES_OPTION)
         {
            if (lMovie.getSelectedIndex() != -1)
               JOptionPane.showConfirmDialog(tCredit, "Total: " + accumulator + " has been charged to your card, Thank you", "Confirmation",JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            else
               JOptionPane.showConfirmDialog(tCredit, "No Movie selecte, Please select a Movie", "Not Allowed", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
         }
         else
            JOptionPane.showConfirmDialog(tCredit, "Please ENTER a VALID Credit Card", "Not Allowed", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
      }
   	   
   } // End actionPerform event		
}


