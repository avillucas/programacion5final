package ar.com.avillucas.tpfinaltusi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.com.avillucas.tpfinaltusi.model.Obra;
import java.util.List;

@Repository
public interface ObraRepository extends CrudRepository<Obra, Long> {

	List<Obra> findAllByNombre(String nombre);

	@Query("select obras from  Obra o  inner join o.artista a  where a.nombre =:nombre ")
	List<Obra> buscarObrasPorNombreArtista(String nombre);

	@Query(nativeQuery = true, value = "SELECT * FROM obras WHERE precio >= :precio")
	List<Obra> buscarObrasPorPrecioMaximo(Float precio);
}
