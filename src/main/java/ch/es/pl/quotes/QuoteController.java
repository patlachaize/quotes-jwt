package ch.es.pl.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @RequestMapping(value = "/quotes", method = RequestMethod.GET)
    public ResponseEntity<List<Quote>>  listQuotes() {
        List<Quote> quotes = quoteService.findAll();
        return new ResponseEntity<List<Quote>>(quotes, HttpStatus.OK);
    }

    @GetMapping (value = "/quotes/{id}")
    public ResponseEntity<Quote> ListQuote(@PathVariable int id) {
        Quote quote = quoteService.findById(id);
        if (quote != null) {
            return new ResponseEntity<Quote>(quote, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/quotes")
    public ResponseEntity<Void> addQuote(@RequestBody Quote quote) {
        int id = quoteService.save(quote);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
