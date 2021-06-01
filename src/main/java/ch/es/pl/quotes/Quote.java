package ch.es.pl.quotes;

public class Quote {

    private int id;
    private String author;
    private String citation;
    private String submitter;

    public Quote() {}

    public Quote(int id, String author, String citation,String submitter) {
        this.id = id;
        this.author = author;
        this.citation = citation;
        this.submitter = submitter;
    }

    public int getId() { return id; }

    public String getAuthor() {
        return author;
    }

    public String getCitation() {
        return citation;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }
}
