package luisf.dev.ProjetoBancoDeSangue.Agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Long> {
    boolean existsByDoadorId(Long doadorId);
}
