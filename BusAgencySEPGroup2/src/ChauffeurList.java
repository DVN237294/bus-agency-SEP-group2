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
