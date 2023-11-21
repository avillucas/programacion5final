package ar.com.avillucas.tpfinaltusi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.com.avillucas.tpfinaltusi.model.Artista;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends CrudRepository<Artista, Long> {
	List<Artista> findAllByNombre(String nombre);
	Optional<Artista> findById(Long id); 
	List<Artista> findAll();
}
