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
	
	public Chauffeur[] getAllChauffeurs()
	{
		Chauffeur[] temp = new Chauffeur[chauffeurs.size()];
		chauffeurs.toArray(temp);
		return temp;
		
		
	}
}
