public abstract class GameObject
   {
   public String getClassName()
      {
      String temp = "" + getClass();
      String delims = "[ ]+";
      String[] tempName = temp.split( delims );
      String name = tempName[1];

      return name;
      }//end getClassName
   }//end class GameObject