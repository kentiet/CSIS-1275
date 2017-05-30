// Jan. 2017 for Assign4 Winter 2017
/*
  Note for movieShowTimes:
   
   showtimes is a string  like "ynny"
                 for 10.00am    
                     3.00pm
                     7.00pm
                     11.30pm 
                 where y for means showtime available for that time slot
                       n  for not available at that time slot
                       so above is y for 10.00am
                                   n for 3.00pm
                                   n  for 7.00pm
                                   y  for 11.30pm
*/
class MovieInfo
{

   private String movieTitle;
   private String movieRating;
   private String movieImg;
   private String movieShowTimes;
  
   private static double adultPrice;
  
   private static double childPrice;

  

   MovieInfo(String title, String rating, String img, String showTimes)
   {
   
      movieTitle = title;
      movieRating = rating;
      movieImg = img;
      movieShowTimes = showTimes;
      
   
   }
   /////////////////////////////////////////////////
   // Please finish this class with the set/get methods 
  /////////////////////////////////////////////// 
  
   public void setMovieTitle(String a)
   {
      movieTitle = a;
   }
   
   public void setMovieRating(String a)
   {
      movieRating = a;
   } 
   
   public void setMovieImg(String a)
   {
      movieImg = a;
   }
   
   public void setShowTimes(String a)
   {
      movieShowTimes = a;
   }
   
   public static void setAdultPrice(double a)
   {
      adultPrice = a;
   }
   
   public static void setChildPrice(double a)
   {
      childPrice = a;
   }
   
   public String getMovieTitle()
   {
      return movieTitle;
   }
          
   public String getMovieRating()
   {
      return movieRating;
   }
          
   public String getMovieImg()
   {
      return movieImg;
   }
          
   public String getShowTimes()
   {
      return movieShowTimes;
   }
    
   public static double getAdultPrice()
   {
      return adultPrice;
   }
   
   public static double getChildPrice()
   {
      return childPrice;
   }
}

///////////////////////////////

class MovieList
{

   MovieInfo[] mList;
  
   public void createList()
   {
   
      mList = new MovieInfo[22];
     
      mList[0] = new MovieInfo("A United Kingdom","PG","A_United_Kingdom.jpg","yyyn");
      mList[1] = new MovieInfo("Amitiville The Awakening","18A","AmitivilleAwakening.jpg","yyyn");
      mList[2] = new MovieInfo("Arrival","14A","arrival.jpg","yyyy");
      mList[3] = new MovieInfo("Baywatch","14A","baywatch.jpg","yyyy");
      mList[4] = new MovieInfo("Beauty and the Beast","PG","Beauty_and_the_Beast.jpg","yyyn");
      mList[5] = new MovieInfo("Blade Runner 2049","14A","Blade_Runner_2049.png","yyyn");
      mList[6] = new MovieInfo("Blazing Samurai","G","BlazingSamurai.png","yyyn");
      mList[7] = new MovieInfo("Fifty Shades Darker","R","Fifty_Shades_Darker.jpg","nnyy");
      mList[8] = new MovieInfo("Furious 8: The Fate of the Furious","14A","TheFateoftheFurious.jpg","yyyy");
      mList[9] = new MovieInfo("Ghost in the Shell","PG","Ghost_in_the_Shell.png","yyyn");
      mList[10] = new MovieInfo("Guardians of the Galaxy: Vol 2","14A","GotG_Vol2.jpg","yyyy");
   
      mList[11] = new MovieInfo("John Wick Chapter 2","PG","John_Wick_Chapter_two.png","yyyn");
      mList[12] = new MovieInfo("Justice League","PG","JusticeLeague.jpg","yyyn");
      mList[13] = new MovieInfo("Logan","14A","logan.jpg","nyyy");
      mList[14] = new MovieInfo("Power Rangers","G","Power_Rangers.png","yyyn");
      mList[15] = new MovieInfo("Rogue One: A Star Wars Story","14A","RogueOne.jpg","yyyn");
      mList[16] = new MovieInfo("Spider-man: Homecoming","G","spiderman_homecoming.gif","yyyy");
      mList[17] = new MovieInfo("Star Wars Episode VIII","G","StarWarsEp8.jpg","yyyn");
      mList[18] = new MovieInfo("The Legend of Tarzan","18A","The_Legend_of_Tarzan.jpg","nnyy");
      mList[19] = new MovieInfo("The Lego Batman Movie","G","the_lego_batman_movie.jpg","yyyy");
      mList[20] = new MovieInfo("The Mummy","14A","The_Mummy.jpg","yyyy");
     
      mList[21] = new MovieInfo("XXX: The Return of Xander Cage","18A","XXX_Return_of_Xander_Cage.jpg","nyyy");
   
   
   
   
   
   }


} 
