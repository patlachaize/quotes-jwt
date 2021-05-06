package ch.es.pl.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public ResponseEntity<Quote> ListQuote(@PathVariable int id) throws QuoteNotFoundException {
        try{
            Quote quote = quoteService.findById(id);
            return new ResponseEntity<Quote>(quote, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
           throw new QuoteNotFoundException(id);
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
