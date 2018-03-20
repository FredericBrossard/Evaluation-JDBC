package EvalJDBC;

public class Categories {
	Long id_categories;
	String name;

	public Categories(String name) {
		this.name = name;
	}

	
	public Categories(Long id_categories, String name) {
		this.id_categories = id_categories;
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId_categories() {
		return id_categories;
	}

	public void setId_categories(Long id_categories) {
		this.id_categories = id_categories;
	}

	
	
}
