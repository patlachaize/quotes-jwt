package ch.es.pl.quotes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuoteNotFoundException extends Exception {
    public QuoteNotFoundException(int id) {
        super("Quote "+ id + " introuvable");
    }
}

