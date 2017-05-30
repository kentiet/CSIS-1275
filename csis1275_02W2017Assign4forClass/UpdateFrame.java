import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.text.*;
import java.util.*;

public class UpdateFrame extends JFrame implements ItemListener, ActionListener 
{
   MovieList movList;

   Container con;
   JPanel p1, p2, p3, pTimes, pPrice;
   JComboBox jCbSelectMov, jCbRate;

   Menu mFrame;
   JMenuBar mb;
   JMenu mainmenu;
   JMenuItem showAll, clear;
   JCheckBox cb10a, cb3p, cb7p, cb11p;
   JTextField textPrice;
   JButton bUpdateMov, bUpdatePrice;

   JRadioButton rAdult, rChild;
   ButtonGroup rGroup;
   JLabel lblPrice;
   JList sumList;
   JTextArea movSummary;
   JScrollPane jpMovSum;
   Vector vtMovSum, movNameSum, movRateSum, movTimeSum;

   String t1, t2, t3, t4;
   String tCondition2;
   MovieTheatre movThea;
   String[] rateArr = { "G", "PG", "14A", "18A", "R" };

   UpdateFrame(String title) {
      super(title);
      movList = new MovieList();
      movList.createList();
      movThea = new MovieTheatre();
   
      con = getContentPane();
      con.setLayout(new GridLayout(3, 1));
   
      p1 = new JPanel();
      p1.setLayout(new GridLayout(1, 4));
   
      p2 = new JPanel();
      p2.setLayout(new GridLayout(1, 3));
   
      p3 = new JPanel();
      p3.setLayout(new FlowLayout());
   
      mb = new JMenuBar();
      mainmenu = new JMenu("Movie Status");
      showAll = new JMenuItem("Show All");
      showAll.addActionListener(this);
      clear = new JMenuItem("Clear");
      clear.addActionListener(this);
   
      mainmenu.add(showAll);
      showAll.setMnemonic('S');
   
      mainmenu.add(clear);
      clear.setMnemonic('C');
   
      mb.add(mainmenu);
      setJMenuBar(mb);
   
   	// Select movie combo box
      jCbSelectMov = new JComboBox();
      jCbSelectMov.setBorder(new TitledBorder("SelectMovie"));
      jCbSelectMov.addItemListener(this);
   
   	// Rating Combo box
      jCbRate = new JComboBox();
      jCbRate.setBorder(new TitledBorder("Current/New Rating"));
      jCbRate.addItemListener(this);
   
      pTimes = new JPanel();
      pTimes.setLayout(new GridLayout(2, 2));
      pTimes.setBorder(new TitledBorder("Current/New Show Times"));
   
      cb10a = new JCheckBox("10.00am");
   
      cb3p = new JCheckBox("3.00om");
   
      cb7p = new JCheckBox("7.00pm");
   
      cb11p = new JCheckBox("11.30pm - 7% disc.");
   
      for (int i = 0; i < movList.mList.length; i++) 
         jCbSelectMov.addItem(movList.mList[i].getMovieTitle());
   
      for (int i = 0; i < rateArr.length; i++) 
         jCbRate.addItem(rateArr[i]);
   	
      jCbRate.setSelectedItem(rateArr[1]);
   
      jCbSelectMov.setSelectedItem(movList.mList[0].getMovieTitle());
   
   	// Current / New Show Times
      pTimes.add(cb10a);
      pTimes.add(cb3p);
      pTimes.add(cb7p);
      pTimes.add(cb11p);
   
   	// Update movie button
      bUpdateMov = new JButton("Update Movie");
      bUpdateMov.addActionListener(this);
   
   	// Component for the fist row
      p1.add(jCbSelectMov);
      p1.add(jCbRate);
      p1.add(pTimes);
      p1.add(bUpdateMov);
   
   	// Adult and Child price
      rAdult = new JRadioButton("Adult Price", true);
      rAdult.addActionListener(this);
      rChild = new JRadioButton("Child Price");
      rChild.addActionListener(this);
   
      rGroup = new ButtonGroup();
      rGroup.add(rAdult);
      rGroup.add(rChild);
   
      pPrice = new JPanel();
      pPrice.setLayout(new GridLayout(2, 1));
   
      pPrice.add(rAdult);
      pPrice.add(rChild);
   
      textPrice = new JTextField();
      textPrice.setText("$5.50");
      textPrice.setBorder(new TitledBorder("Price"));
      textPrice.setBackground(Color.YELLOW);
      textPrice.setHorizontalAlignment(SwingConstants.CENTER);
   	// textPrice.setVerticalAlignment(SwingConstants.CENTER);
      Font priceFont = new Font("San Serif", Font.BOLD, 56);
      textPrice.setFont(priceFont);
   
      bUpdatePrice = new JButton("Update Adult Price");
      bUpdatePrice.addActionListener(this);
   
      p2.add(pPrice);
   
      p2.add(textPrice);
      p2.add(bUpdatePrice);
   
      movSummary = new JTextArea();
      vtMovSum = new Vector();
      movNameSum = new Vector();
      movRateSum = new Vector();
      movTimeSum = new Vector();
      sumList = new JList();
      sumList.setBackground(Color.ORANGE);
      jpMovSum = new JScrollPane(sumList);
   
      Font font = new Font("Helvetica", Font.BOLD, 14);
      jpMovSum.setFont(font);
      jpMovSum.setBorder(new TitledBorder("Movie Summary"));
      jpMovSum.setBackground(Color.ORANGE);
   
      jpMovSum.add(movSummary);
   
   	// Main Container
      con.add(p1);
      con.add(p2);
      con.add(jpMovSum);
   
   }

   public void itemStateChanged(ItemEvent e) 
   {
      if (e.getSource() == jCbSelectMov) 
      {
         if (jCbSelectMov.getSelectedIndex() != -1) 
         {
            String tCondition1;
            for (int i = 0; i < movList.mList.length; i++) 
            {
               if (jCbSelectMov.getSelectedItem().equals(movList.mList[i].getMovieTitle())) 
               {
                  jCbRate.setSelectedItem(movList.mList[i].getMovieRating());
               
                  tCondition1 = movList.mList[i].getShowTimes();
               
                  for (int idx = 0; idx < tCondition1.length(); idx++) 
                  {
                     if (tCondition1.substring(idx, (idx + 1)).equals("y")) 
                     {
                        if (idx == 0)
                           cb10a.setSelected(true);
                        else if (idx == 1)
                           cb3p.setSelected(true);
                        else if (idx == 2)
                           cb7p.setSelected(true);
                        else if (idx == 3)
                           cb11p.setSelected(true);
                     } 
                     else 
                     {
                        if (tCondition1.substring(idx, (idx + 1)).equals("n")) 
                        {
                           if (idx == 0)
                              cb10a.setSelected(false);
                           else if (idx == 1)
                              cb3p.setSelected(false);
                           else if (idx == 2)
                              cb7p.setSelected(false);
                           else if (idx == 3)
                              cb11p.setSelected(false);
                        }
                     }
                  }
               }
            }
         }
      }
   }  //------------------------------------------------------------------------------------End itemStateChanged event

   public void actionPerformed(ActionEvent e) 
   {
      NumberFormat dollar = NumberFormat.getCurrencyInstance();
      if (e.getSource() instanceof JRadioButton) 
      {
         if (rAdult.isSelected()) 
         {
            textPrice.setText(String.valueOf(dollar.format(MovieInfo.getAdultPrice())));
            textPrice.setBackground(Color.yellow);
            bUpdatePrice.setText("Update Adult Price");
         } 
         else if (rChild.isSelected()) 
         {
            textPrice.setText(String.valueOf(dollar.format(MovieInfo.getChildPrice())));
            textPrice.setBackground(Color.white);
            bUpdatePrice.setText("Update Child Price");
         }
      }	// End Adult / Child Price change
   
      if (e.getSource() instanceof JButton) 
      {
         if (e.getSource() == bUpdateMov) 
         {
            tCondition2 = null;
         
            for (int i = 0; i < movList.mList.length; i++) 
            {
               if (jCbSelectMov.getSelectedItem().equals(movList.mList[i].getMovieTitle())) 
               {
                  movList.mList[i].setMovieRating(String.valueOf(jCbRate.getSelectedItem()));
               
                  if (cb10a.isSelected())
                     t1 = "y";
                  else if (!cb10a.isSelected())
                     t1 = "n";
               
                  if (cb3p.isSelected())
                     t2 = "y";
                  else if (!cb3p.isSelected())
                     t2 = "n";
               
                  if (cb7p.isSelected())
                     t3 = "y";
                  else if (!cb7p.isSelected())
                     t3 = "n";
               
                  if (cb11p.isSelected())
                     t4 = "y";
                  else if (!cb11p.isSelected())
                     t4 = "n";
               
                  tCondition2 = t1 + t2 + t3 + t4;
                  movList.mList[i].setShowTimes(tCondition2);
               }
            
            }
            JOptionPane.showConfirmDialog(bUpdateMov, "Changes to movie updated", "Movie Update",
               	JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
         } //------------------------------------------------------------------------------------End Update Movie button
         else 
         {
            if (e.getSource() == bUpdatePrice) 
            {
               String setPrice = textPrice.getText().substring(1);
               double price = Double.parseDouble(setPrice);
               if (rAdult.isSelected()) 
               {
                  MovieInfo.setAdultPrice(price);
                  JOptionPane.showConfirmDialog(bUpdatePrice, "Adult Price Updated", "Update Price",
                     	JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
               } 
               else if (rChild.isSelected()) 
               {
                  MovieInfo.setChildPrice(price);
                  JOptionPane.showConfirmDialog(bUpdatePrice, "Child Price Updated", "Update Price",
                     	JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
               }
            }
         }  //------------------------------------------------------------------------------------End Update Price button
      }
   
      if (e.getSource() instanceof JMenuItem) 
      {
         if (e.getSource() == showAll) 
         {
            vtMovSum.clear();
            for (int i = 0; i < movList.mList.length; i++) 
            {
               movNameSum.addElement(movList.mList[i].getMovieTitle());
               movRateSum.addElement(movList.mList[i].getMovieRating());
               movTimeSum.addElement(movList.mList[i].getShowTimes());
               vtMovSum.add(movNameSum.elementAt(i) + " ====> " + movRateSum.elementAt(i) + " ====> " + movTimeSum.elementAt(i));
            }
         
         } //------------------------------------------------------------------------------------End Show All selection
         else 
         {
            if (e.getSource() == clear)
               vtMovSum.clear();
         } //------------------------------------------------------------------------------------End clear selection
         sumList.setListData(vtMovSum);
      }
   
   }  // End actionPerformed event
}
