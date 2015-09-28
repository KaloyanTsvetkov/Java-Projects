package entity.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private double balance;

	@Column(name="LOGIN_NAME")
	private String loginName;

	@Column(name="LOGIN_PASSWORD")
	private String loginPassword;

	private String token;

	//bi-directional many-to-one association to CompanyProfile
	@OneToMany(mappedBy="company", fetch=FetchType.EAGER)
	private List<CompanyProfile> companyProfiles;

	public Company() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<CompanyProfile> getCompanyProfiles() {
		return this.companyProfiles;
	}

	public void setCompanyProfiles(List<CompanyProfile> companyProfiles) {
		this.companyProfiles = companyProfiles;
	}

	public CompanyProfile addCompanyProfile(CompanyProfile companyProfile) {
		getCompanyProfiles().add(companyProfile);
		companyProfile.setCompany(this);

		return companyProfile;
	}

	public CompanyProfile removeCompanyProfile(CompanyProfile companyProfile) {
		getCompanyProfiles().remove(companyProfile);
		companyProfile.setCompany(null);

		return companyProfile;
	}

}