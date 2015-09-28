package controler;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import entity.model.Company;
import entity.model.CompanyProfile;

public class Queries {

	private final static EntityManagerFactory entityManager = Persistence
			.createEntityManagerFactory("BankSystem");
			

	// get All Companies
	public static List<Company> showAllCompany() {
		List<Company> companyList = null;
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Query query = manager.createNamedQuery("Company.findAll");
		companyList = query.getResultList();

		manager.close();
		return companyList;
	}

	// get All CompaniesProfiles
	public static List<CompanyProfile> showAllCompanyProfiles() {
		List<CompanyProfile> companyList = null;
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Query query = manager.createNamedQuery("CompanyProfile.findAll");
		companyList = query.getResultList();

		manager.close();
		return companyList;
	}

	// add new User
	public static int addUser(String companyName, String userName, String pass) {
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		boolean flag = true;
		List<Company> myCompanies = Queries.showAllCompany();
		for (int i = 0; i < myCompanies.size(); i++) {
			if (myCompanies.get(i).getLoginName().equalsIgnoreCase(userName)) {
				flag = false;
				break;
			}
		}

		if (flag) {
			List<CompanyProfile> myCompanyProfiels = Queries
					.showAllCompanyProfiles();
			for (int i = 0; i < myCompanyProfiels.size(); i++) {
				if (myCompanyProfiels.get(i).getCompanyName()
						.equalsIgnoreCase(companyName)) {
					flag = false;
					break;
				}
			}

			if (flag) {
				String hcPass = heshingPass(pass);

				try {
					Company newUser = new Company();
					newUser.setLoginName(userName);
					newUser.setLoginPassword(hcPass);
					newUser.setBalance(0.00);
					newUser.setToken("");

					manager.persist(newUser);

					CompanyProfile newProfile = new CompanyProfile();
					newProfile.setCompany(newUser);
					newProfile.setCompanyName(companyName);

					manager.persist(newProfile);

					manager.getTransaction().commit();

					manager.close();
					return newUser.getId();
				} catch (RollbackException e) {
					return -2;
				}
			} else {
				return -1;
			}
		} else {
			return -3;
		}
	}

	// log in User
	public static int logInUser(String userName, String pass) {
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		boolean flag = false;
		String hcPass = heshingPass(pass);

		manager.close();

		List<Company> myCompanies = Queries.showAllCompany();
		Company tempComp = null;
		for (int i = 0; i < myCompanies.size(); i++) {
			if (myCompanies.get(i).getLoginName().equalsIgnoreCase(userName)) {
				if (myCompanies.get(i).getLoginPassword().equals(hcPass)) {
					flag = true;
					tempComp = myCompanies.get(i);
					break;
				}
			}
		}
		if (flag) {
			return tempComp.getId();
		} else {
			return -5;
		}
	}

	// set Token to DB
	public static void setToken(int id, String token) {
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Company tempComp = manager.find(Company.class, id);

		tempComp.setToken(token);
		manager.persist(tempComp);

		manager.getTransaction().commit();

		manager.close();
	}

	// Create a unique token using SHA-1
	public static String getUniqueToken() {
		String token = "";
		Calendar c = Calendar.getInstance();
		Random rand = new Random();
		long z = c.getTimeInMillis();
		int y = rand.nextInt(1000000);
		token = Long.toString(z) + Integer.toString(y);
		HashFunction hf = Hashing.sha1();
		HashCode hcToken = hf.newHasher().putString(token, Charsets.UTF_8)
				.hash();
		return hcToken.toString();
	}

	// Get Token from DB by id
	public static String getTokenByID(int id) {
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Company tempComp = manager.find(Company.class, id);

		manager.getTransaction().commit();

		manager.close();
		return tempComp.getToken();
	}

	// Deposit sum to balance
	public static boolean depositSum(int id, double sum) {
		double result = 0.00;

		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Company tempComp = manager.find(Company.class, id);
		if (sum > 0) {
			result = tempComp.getBalance() + sum;
			tempComp.setBalance(result);

			manager.persist(tempComp);
			manager.getTransaction().commit();

			manager.close();
			return true;
		} else {
			manager.getTransaction().commit();
			manager.close();
			return false;
		}
	}
	
	// Withdraw sum from balance
	public static boolean withdrawSum(int id, double sum) {
		double result = 0.00;

		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Company tempComp = manager.find(Company.class, id);
		if (sum > 0 && tempComp.getBalance()>=sum) {
			result = tempComp.getBalance() - sum;
			tempComp.setBalance(result);

			manager.persist(tempComp);
			manager.getTransaction().commit();

			manager.close();
			return true;
		} else {
			manager.getTransaction().commit();
			manager.close();
			return false;
		}
	}

	//Get Balance by id
	public static double getBalanceByID(int id){
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Company tempComp = manager.find(Company.class, id);
		manager.getTransaction().commit();

		manager.close();
		return tempComp.getBalance();
	}
	
	//Hashing password
	private static String heshingPass(String pass) {
		HashFunction hf = Hashing.sha256();
		HashCode hcPass = hf.newHasher().putString(pass, Charsets.UTF_8).hash();
		return hcPass.toString();
	}
}
