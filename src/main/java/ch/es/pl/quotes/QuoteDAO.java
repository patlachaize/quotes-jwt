package ch.es.pl.quotes;


import ch.es.pl.quotes.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class QuoteDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Quote> findAll() {
        return jdbcTemplate.query(
                "select * from quotes",
                (rs,rownum) ->
                        new Quote(
                                rs.getInt("id"),
                                rs.getString("author"),
                                rs.getString("citation"),
                                rs.getString("submitter")
                        )
        );
    }

    public Quote findById(int id) throws QuoteNotFoundException {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from quotes where id=?",
                    new Object[]{id},
                    (rs, rownum) ->
                            new Quote(
                                    rs.getInt("id"),
                                    rs.getString("author"),
                                    rs.getString("citation"),
                                    rs.getString("submitter")
                            )
            );
        } catch (EmptyResultDataAccessException e) {
            throw new QuoteNotFoundException(id);
        }
    }

    public int save(Quote quote) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into quotes (author,citation,submitter) values(?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1,quote.getAuthor());
                    ps.setString(2,quote.getCitation());
                    ps.setString(3,quote.getSubmitter());

                    return ps;
                },
                keyHolder
        );
        return keyHolder.getKey().intValue();
    }

}
