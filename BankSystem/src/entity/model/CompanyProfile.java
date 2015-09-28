package entity.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the company_profile database table.
 * 
 */
@Entity
@Table(name="company_profile")
@NamedQuery(name="CompanyProfile.findAll", query="SELECT c FROM CompanyProfile c")
public class CompanyProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="COMPANY_NAME")
	private String companyName;

	//bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;

	public CompanyProfile() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}