import java.util.ArrayList;
public class ChauffeurList
{
   private ArrayList<Chauffeur> chauffeurs;

   public ChauffeurList()
   {
      chauffeurs = new ArrayList<Chauffeur>();
   }

   public void addChauffeur(Chauffeur chauffeur)
   {
      chauffeurs.add(chauffeur);
   }

   public void deleteChauffeur(Chauffeur chauffeur)
   {
      chauffeurs.remove(chauffeur);
   }

   public String getFirstName(String firstName)
   {
      for (int i = 0; i < chauffeurs.size(); i++)
      {
         if (chauffeurs.get(i).getFirstName().equals(firstName))
         {
            return chauffeurs.get(i).getFirstName();
         }
      }
      return null;
   }
   
   public String[] getAllFirstNames()
   {
      String[] firstNames = new String[chauffeurs.size()];
      for (int i = 0; i < firstNames.length; i++)
      {
         firstNames[i] = chauffeurs.get(i).getFirstName();
      }
      
      return firstNames;
   }
   
   public String[] getAllLastNames()
   {
      String[] lastNames = new String[chauffeurs.size()];
      for (int i = 0; i < lastNames.length; i++)
      {
         lastNames[i] = chauffeurs.get(i).getLastName();
      }
      return lastNames;
   }
   
   public String[] getAllChauffeurIds()
   {
      String[] chauffeurIds = new String[chauffeurs.size()];
      for (int i = 0; i < chauffeurIds.length; i++)
      {
         chauffeurIds[i] = Integer.toString(chauffeurs.get(i).getChauffeurID());
      }
      return chauffeurIds;
   }
   
   public String getLastName(String lastName)
   {
      for (int i = 0; i < chauffeurs.size(); i++)
      {
         if (chauffeurs.get(i).getLastName().equals(lastName))
         {
            return chauffeurs.get(i).getLastName();
         }
      }
      return null;
   }
   
   public int getChauffeurId(int ID)
   {
      for (int i = 0; i < chauffeurs.size(); i++)
      {
         if (chauffeurs.get(i).getChauffeurID() == ID)
         {
            return chauffeurs.get(i).getChauffeurID();
         }
      }
      return -1;
   }

   public Chauffeur getChauffeur(String firstName, String lastName,
         int chauffeurID)
   {
      for (int i = 0; i < chauffeurs.size(); i++)
      {
         if (chauffeurs.get(i).getFirstName().equals(firstName)
               || chauffeurs.get(i).getLastName().equals(lastName)
               || chauffeurs.get(i).getChauffeurID() == chauffeurID)
         {
            return chauffeurs.get(i);
         }
      }
      return null;
   }

   public Chauffeur[] getAllChauffeurs()
   {
      Chauffeur[] temp = new Chauffeur[chauffeurs.size()];
      chauffeurs.toArray(temp);
      return temp;

   }
}
