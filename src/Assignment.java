
public class Assignment {

	private String name;
	private double score;
	private String comment;
	
	public Assignment(String name, double score, String comment){
		this.name = name;
		this.score = score;
		this.comment = comment;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	

	
}
