package com.hoyski.bogglesolverservice.controller;

import com.hoyski.boggle.BoggleSolver;

import com.hoyski.boggle.WordAndChains;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class BoggleSolverController {

    private BoggleSolver boggleSolver = new BoggleSolver();

    /**
     * Returns the WordAndChains that can be made by the boggle board defined
     * by the given letters. The first four letters are the first row, the next
     * four letters are the second row, etc
     */
    @GetMapping("/words/{letters}")
    public List<WordAndChains> findWords(@PathVariable String letters) {
        try {
            return boggleSolver.getWords(letters);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
