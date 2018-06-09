package pl.edu.agh.ki.mwo.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="works")
public class Work implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private String keyWords;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="work_id")
	private Set<Evaluation> workToEvaluate;

	public Work() {
		workToEvaluate = new HashSet<Evaluation>();
	}

	public void addWorkToEvaluate(Evaluation newWorkToEvaluate) {
		workToEvaluate.add(newWorkToEvaluate);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setWorkToEvaluate(Set<Evaluation> workToEvaluate) {
		this.workToEvaluate = workToEvaluate;
	}

	public Set<Evaluation> getWorkToEvaluate() {
		return workToEvaluate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String toString() {
		return "Title: " + getTitle() + " (" + getAuthor() + ", " + getWorkToEvaluate().size() + " workToEvaluate)";
}

}
