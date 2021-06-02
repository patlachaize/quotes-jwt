package ch.es.pl.quotes;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class QuoteController {

    @Autowired
    private QuoteDAO quoteDAO;

    @Autowired
    private QuoteService quoteService;

    @RequestMapping(value = "/quotes", method = RequestMethod.GET)
    public ResponseEntity<List<Quote>>  listQuotes() {
        List<Quote> quotes = quoteDAO.findAll();
        return new ResponseEntity<List<Quote>>(quotes, HttpStatus.OK);
    }

    @GetMapping (value = "/quotes/{id}")
    public ResponseEntity<Quote> ListQuote(@PathVariable int id) throws QuoteNotFoundException {
        Quote quote = quoteDAO.findById(id);
        return new ResponseEntity<Quote>(quote, HttpStatus.OK);
    }

    @PostMapping(value = "/quotes")
//    @Operation(summary = "Add a quote", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> addQuote(@RequestBody Quote quote, HttpServletRequest request) {
        quote.setSubmitter((String) request.getAttribute("login"));
        int id = quoteDAO.save(quote);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PostMapping(value = "/all-quotes-or-nothing")
    public ResponseEntity<Void> addQuotes(@RequestBody List<Quote> quotes) {
        quoteService.allQuotesOrNothing(quotes);
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }

}
