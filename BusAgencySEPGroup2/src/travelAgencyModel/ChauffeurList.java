package travelAgencyModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * {@link ArrayList} backed list containing instances of {@link Chauffeur}.
 * Implements {@link Serializable}
 * 
 * @author Afonso, Carlos
 *
 */
public class ChauffeurList implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Chauffeur> chauffeurs;

	/**
	 * Default constructor initializes the underlying collection.
	 */
	public ChauffeurList()
	{
		chauffeurs = new ArrayList<Chauffeur>();
	}

	public void addChauffeur(Chauffeur chauffeur)
	{
		chauffeurs.add(chauffeur);
	}

	public void removeChauffeur(Chauffeur chauffeur)
	{
		chauffeurs.remove(chauffeur);
	}

	/**
	 * Returns a collection of {@link String}s containing the first name of
	 * every {@link Chauffeur} in the underlying collection
	 * 
	 * @return An array of {@link String}s
	 */
	public String[] getAllFirstNames()
	{
		String[] firstNames = new String[chauffeurs.size()];
		for (int i = 0; i < firstNames.length; i++)
		{
			firstNames[i] = chauffeurs.get(i).getFirstName();
		}

		return firstNames;
	}

	/**
	 * Returns a collection of {@link String}s containing the last name of every
	 * {@link Chauffeur} in the underlying collection
	 * 
	 * @return An array of {@link String}s
	 */
	public String[] getAllLastNames()
	{
		String[] lastNames = new String[chauffeurs.size()];
		for (int i = 0; i < lastNames.length; i++)
		{
			lastNames[i] = chauffeurs.get(i).getLastName();
		}
		return lastNames;
	}

	/**
	 * Returns a collection of {@link String}s containing the ID of every
	 * {@link Chauffeur} in the underlying collection
	 * 
	 * @return An array of {@link String}s
	 */
	public String[] getAllChauffeurIds()
	{
		String[] chauffeurIds = new String[chauffeurs.size()];
		for (int i = 0; i < chauffeurIds.length; i++)
		{
			chauffeurIds[i] = Integer.toString(chauffeurs.get(i).getChauffeurID());
		}
		return chauffeurIds;
	}

	/**
	 * Returns the first {@link Chauffeur} in the underlying collection which
	 * has their first name, last name or ID equal to any of the passed
	 * parameters. If any of the passed reference type parameters are null, they
	 * are ignored.
	 * 
	 * @param firstName
	 *            The first name of the {@link Chauffeur}.
	 * @param lastName
	 *            The last name of the {@link Chauffeur}.
	 * @param chauffeurID
	 *            The ID of the {@link Chauffeur}.
	 * @return Returns the first {@link Chauffeur} that matches any of the
	 *         parameters.
	 */
	public Chauffeur getChauffeur(String firstName, String lastName, int chauffeurID)
	{
		for (int i = 0; i < chauffeurs.size(); i++)
		{
			if (chauffeurs.get(i).getFirstName().equals(firstName) || chauffeurs.get(i).getLastName().equals(lastName)
					|| chauffeurs.get(i).getChauffeurID() == chauffeurID)
			{
				return chauffeurs.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns a shallow copy collection of {@link Chauffeur}s
	 * 
	 * @return An array of {@link Chauffeur}.
	 */
	public Chauffeur[] getAllChauffeurs()
	{
		Chauffeur[] temp = new Chauffeur[chauffeurs.size()];
		chauffeurs.toArray(temp);
		return temp;

	}

	/**
	 * Searches for {@link Chauffeur}s that satisfies the given input
	 * parameters. Does exclusive search by default.If any of the passed
	 * parameters are {@link null}, they are ignored, in which case they do not
	 * affect the search result.
	 * 
	 * @param firstName
	 *            The fist name to search for.
	 * @param lastName
	 *            The last name to search for.
	 * @param chauffeurID
	 *            The chauffeur ID to search for.
	 * @return Returns an array of {@link Chauffeur}es that satisfies the given
	 *         parameters.
	 */
	public Chauffeur[] search(String firstName, String lastName, int chauffeurID)
	{
		ArrayList<Chauffeur> temp = new ArrayList<Chauffeur>();
		Chauffeur[] tempArray = new Chauffeur[0];

		boolean mustHaveFirstName = firstName != null;
		boolean mustHaveLastName = lastName != null;
		boolean mustHaveChauffeurID = chauffeurID != 0;

		for (Chauffeur chauffeur : chauffeurs)
		{
			boolean hasFirstName = mustHaveFirstName && chauffeur.getFirstName().equals(firstName);
			boolean hasLastName = mustHaveLastName && chauffeur.getLastName().equals(lastName);
			boolean hasChauffeurID = mustHaveChauffeurID && chauffeur.getChauffeurID() == chauffeurID;

			if (mustHaveFirstName == hasFirstName && mustHaveLastName == hasLastName && mustHaveChauffeurID == hasChauffeurID)
			{
				temp.add(chauffeur);
			}

		}

		return temp.toArray(tempArray);
	}
}
