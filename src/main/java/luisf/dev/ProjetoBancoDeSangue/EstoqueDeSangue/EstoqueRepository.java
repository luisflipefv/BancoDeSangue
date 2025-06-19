package luisf.dev.ProjetoBancoDeSangue.EstoqueDeSangue;

import luisf.dev.ProjetoBancoDeSangue.Doador.TipoSanguineo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<EstoqueModel, TipoSanguineo> {
}
