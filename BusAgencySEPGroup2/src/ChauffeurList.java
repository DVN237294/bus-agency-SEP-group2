import java.util.ArrayList;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

public class ChauffeurList
{
   private ArrayList<Chauffeur> chauffeurs;
   private String[] chauffeurList;

   public ChauffeurList()
   {
      chauffeurs = new ArrayList<Chauffeur>();
      chauffeurList = new String[chauffeurs.size()];
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
      for (int i = 0; i < chauffeurList.length; i++)
      {
         chauffeurList[i] = chauffeurs.get(i).getFirstName();
      }
      return chauffeurList;
   }
   
   public String[] getAllLastNames()
   {
      for (int i = 0; i < chauffeurList.length; i++)
      {
         chauffeurList[i] = chauffeurs.get(i).getLastName();
      }
      return chauffeurList;
   }
   
   public String[] getAllChauffeurIds()
   {
      for (int i = 0; i < chauffeurList.length; i++)
      {
         chauffeurList[i] = Integer.toString(chauffeurs.get(i).getChauffeurID());
      }
      return chauffeurList;
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
               && chauffeurs.get(i).getLastName().equals(lastName)
               && chauffeurs.get(i).getChauffeurID() == chauffeurID)
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
