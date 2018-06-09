package pl.edu.agh.ki.mwo.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="worksToEvaluation")
public class Evaluation implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private String keyWords;
	
	@Column
	private String grade;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String toString() {
		return "Grade: " + grade + " (Title: " + getTitle() + ", Author: " + getAuthor() + ")";
	}
}