package luisf.dev.ProjetoBancoDeSangue.Agendamento;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {
    private AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    //post - cria agendamento
    public AgendamentoModel criarAgendamento(AgendamentoModel agendamentoRecebido){
        AgendamentoModel agendamentoCriado = new AgendamentoModel();
        if (agendamentoRecebido.getDataHoraAgendamento() == null || !agendamentoRecebido.getDataHoraAgendamento().isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Insira uma data válida");
        }
        agendamentoCriado.setDoador(agendamentoRecebido.getDoador());
        agendamentoCriado.setStatus(StatusAgendamento.AGENDADO);
        agendamentoCriado.setDataHoraAgendamento(agendamentoRecebido.getDataHoraAgendamento());
        return agendamentoRepository.save(agendamentoCriado);
    }

    //delete - dalate agendamento
    public void deletaAgendamento(Long id){
        if (agendamentoRepository.existsById(id)) {
            agendamentoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Agendamento não encontrado");
        }
    }

    //put - altera agendamento
    public AgendamentoModel alteraAgendamento(Long id, AgendamentoModel agendamentoAtualizado){
        return agendamentoRepository.findById(id).map(agendamentoExistente -> {
            if (agendamentoAtualizado.getDataHoraAgendamento() != null && !agendamentoAtualizado.getDataHoraAgendamento().isBefore(LocalDateTime.now())){
                agendamentoExistente.setDataHoraAgendamento(agendamentoAtualizado.getDataHoraAgendamento());
            }
            if (agendamentoAtualizado.getStatus() != null){
                agendamentoExistente.setStatus(agendamentoAtualizado.getStatus());
            }
            return agendamentoRepository.save(agendamentoExistente);
        }).orElseThrow(() -> new RuntimeException("Agendamento não encontrado " + id));
    }


    //get - consulta um agendamento por id
    public Optional<AgendamentoModel> consultaAgendamentoPorId(Long id){
        return agendamentoRepository.findById(id);
    }

    //get - retorna todos os agendamentos
    public List<AgendamentoModel> listarTodosAgendamentos(){
        return agendamentoRepository.findAll();
    }

}
