
package br.senac.tads.dsw.projetocontatos;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author fernando.tsuda
 */
public interface ContatoService {

    void deleteById(int id);

    List<ContatoDto> findAll();

    ContatoDto findById(int id);

    Optional<ContatoDto> findByIdComOptional(int id);

    void save(ContatoDto c);
    
}
