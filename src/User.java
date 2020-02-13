// Classe abstracte mère 
public abstract class User {
	
	protected int id;
	protected String firstname, lastname;
	private static int counter = 0;
	
	public User() {};
	
	public User(String firstname, String lastname) {
		
		this.setId(++counter);
		this.setFirstname(firstname);
		this.setLastname(lastname);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
