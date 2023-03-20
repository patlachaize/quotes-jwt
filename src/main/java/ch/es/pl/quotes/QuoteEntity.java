package ch.es.pl.quotes;


import jakarta.persistence.*;

@Entity(name = "Quote")
@Table(name = "quotes")
public class QuoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String citation;
    private String submitter;

    public QuoteEntity() {}

    public QuoteEntity(int id, String author, String citation, String submitter) {
        this.id = id;
        this.author = author;
        this.citation = citation;
        this.submitter = submitter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }
}
