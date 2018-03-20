package EvalJDBC;

public class Aliment {
		int id;	
		String name;
		int txnrj;
		int txproteine;
		int txlipide;
		int txglucide;	
		Long foreignKey;
		
	//Le constructeur 		
		public Aliment(String name, int txnrj, int txproteine, int txlipide, int txglucide, Long foreignKey) {
			//this.id = 0;
			this.name = name;
			this.txnrj = txnrj;
			this.txproteine = txproteine;
			this.txlipide = txlipide;
			this.txglucide = txglucide;
			this.foreignKey = foreignKey;
		}
		

		public Aliment(int id, String name, int txnrj, int txproteine, int txlipide, int txglucide, Long foreignKey) {
		
		this.id= id;
		this.name= name;
		this.txnrj= txnrj;
		this.txproteine= txproteine;
		this.txlipide= txlipide;
		this.txglucide= txglucide;
		this.foreignKey= foreignKey;
	}


		public Long getForeignKey() {
			return foreignKey;
		}


		public void setForeignKey(Long foreignKey) {
			this.foreignKey = foreignKey;
		}


		public Aliment(int id) {
			this.id = id;
		}

		public Aliment(String name, Long foreignKey) {
			
			this.name = name;
			this.foreignKey = foreignKey;
		}


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getTxnrj() {
			return txnrj;
		}
		public void setTxnrj(int txnrj) {
			this.txnrj = txnrj;
		}
		public double getTxproteine() {
			return txproteine;
		}
		public void setTxproteine(int txproteine) {
			this.txproteine = txproteine;
		}
		public double getTxlipide() {
			return txlipide;
		}
		public void setTxlipide(int txlipide) {
			this.txlipide = txlipide;
		}
		public double getTxglucide() {
			return txglucide;
		}
		public void setTxglucide(int txglucide) {
			this.txglucide = txglucide;
		}
		
		
}
