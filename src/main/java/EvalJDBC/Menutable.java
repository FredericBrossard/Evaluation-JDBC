package EvalJDBC;

public class Menutable {
	private final String answer;
	private final String question;

	public static void afficheMenu() {

		// Gestion du sommaire et options proposées à l'utilisateur
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!-----  Hello and Weclome in  'CRUD application'  ------!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("! 1) Add an aliment in database                         !");
		System.out.println("! 2) Delete an aliment in database                      !");
		System.out.println("! 3) Show me all aliment in database                    !");
		System.out.println("! 0) Quit the 'CRUD Application'. Bye                   !");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	// CONSTRUCTEUR
	// question, ici, est un argument de type string
	public Menutable(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	public String getQuestion() {
		return question;
	}

	@Override
	public String toString() {
		return "Menutable [answer=" + answer + ", question=" + question + "]";
	}

}
