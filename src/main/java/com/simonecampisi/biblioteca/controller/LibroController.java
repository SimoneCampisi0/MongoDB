package com.simonecampisi.biblioteca.controller;

import com.simonecampisi.biblioteca.dto.request.CreateLibroRequest;
import com.simonecampisi.biblioteca.dto.response.CreateLibroResponse;
import com.simonecampisi.biblioteca.dto.response.StatoResponse;
import com.simonecampisi.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("gestione-libri")
public class LibroController {
    @Autowired
    private LibroService libroService;

    /**
     * Metodo POST per aggiungere un libro alla biblioteca.
     * @param createLibroRequest - Paramtero preso in input, da cui ricavo l'oggetto Libro da salvare nel DB.
     * @return un oggetto CreateLibroResponse, con tutte le informazioni del libro appena creato
     * oppure un oggetto StatoResponse, che si occupa di gestire quei casi in cui il valore di ritorno è inesistente.
     *
     * In questo caso, se il libro è già presenta nella biblioteca, viene ritornato StatoResponse con valore null.
     */
    @PostMapping("/aggiungi-libro")
    public ResponseEntity<?> aggiungiLibro(@RequestBody CreateLibroRequest createLibroRequest) {
        CreateLibroResponse l = libroService.aggiungiLibro(createLibroRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Objects.isNull(l) ? new StatoResponse(false) : l);
    }

    /**
     * Metodo DELETE che, presa in input una String contenente il nomeLibro, trova e cancella il libro dal DB.
     * @param nomeLibro - Parametro contenente il nome del libro da eliminare.
     * @return Un oggetto StatoResponse. Nel caso in cui il libro è stato trovato ed eliminato con successo, StatoResponse è impostato a true, altrimenti a false.
     */
    @DeleteMapping("/rimuovi-libro")
    public ResponseEntity<?> rimuoviLibro(@RequestParam String nomeLibro) {
        return ResponseEntity.status(HttpStatus.OK).body(libroService.rimuoviLibro(nomeLibro));
    }

    /**
     * Metodo GET che ritorna una lista di tutti i libri disponibili per il ritiro in libreria.
     * @return Una lista filtrata di ViewLibroResponse, in base alla disponibilità del libro. Ritorna soltanto i libri disponibili al ritiro.
     */

    @GetMapping("/visualizza-libri-disponibili")
    public ResponseEntity<?> listaLibri() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libroService.listaLibri());
    }

    /**
     * Metodo GET che, preso in input un nome di un libro, controlla se esiste e se il libro esistente non è stato già ritirato.
     * Nel caso in cui il libro non è stato ritirato ed esiste all'interno della libreria, viene ritornato un oggetto StatoResponse con un valore, all'interno della mappa, come true,
     * altrimenti viene ritornato un false.
     * @param nomeLibro - Parametro contenente il nome del libro da eliminare.
     * @return Se il libro non è stato ritirato ed esiste all'interno della libreria, viene ritornato un oggetto StatoResponse con un valore, all'interno della Map, come true,
     *      * altrimenti viene ritornato un false.
     */

    @GetMapping("/richiedi-libro")
    public ResponseEntity<?> richiediLibro(@RequestParam String nomeLibro) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libroService.richiediLibro(nomeLibro));
    }

    /**
     * Metodo GET che, preso in input un nome di un libro, controlla se esiste e se il libro esistente è stato ritirato.
     * Nel caso in cui sia stato ritirato, esso viene restituito alla libreria.
     * @param nomeLibro
     * @return Se il libro è stato ritirato e dev'essere restituito, l'API ritorna un oggetto StatoResponse con valore, all'interno della Map, come true.
     * Altrimenti viene ritornato un false.
     */
    @GetMapping("/restituisci-libro")
    public ResponseEntity<?> restituisciLibro(@RequestParam String nomeLibro) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libroService.restituisciLibro(nomeLibro));
    }
}
