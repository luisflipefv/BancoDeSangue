package luisf.dev.ProjetoBancoDeSangue.Doacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<DoacaoModel, Long> {
    List<DoacaoModel> findByDoadorId(Long doadorId);
    boolean existsByDoadorId(Long doadorId);
}
