package ch.es.pl.quotes;

public class Quote {

    private int id;
    private String author;
    private String citation;

    public Quote() {}

    public Quote(int id, String author, String citation) {
        this.id = id;
        this.author = author;
        this.citation = citation;
    }

    public int getId() { return id; }

    public String getAuthor() {
        return author;
    }

    public String getCitation() {
        return citation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

}
