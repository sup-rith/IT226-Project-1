
public class Project {

	private String name;
	private String score;
	private String comment;
	
	public Project(String name, String score, String comment){
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String toString(){
		return score + "," + comment;
	}

	
}