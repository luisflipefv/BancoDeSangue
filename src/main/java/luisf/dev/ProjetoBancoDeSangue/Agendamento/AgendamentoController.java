package luisf.dev.ProjetoBancoDeSangue.Agendamento;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    //post
    @PostMapping
    public ResponseEntity<AgendamentoModel> criaAgendamento(@RequestBody AgendamentoModel agendamento){
        AgendamentoModel novoAgendamento =  agendamentoService.criarAgendamento(agendamento);
        URI location = URI.create("/agendamentos/" + novoAgendamento.getId());
        return ResponseEntity.created(location).body(novoAgendamento);
    }

    //get - listar todos
    @GetMapping
    public ResponseEntity<List<AgendamentoModel>> listarTodosAgendamentos(){
        List<AgendamentoModel> lista = agendamentoService.listarTodosAgendamentos();
        return ResponseEntity.ok(lista);
    }

    //get - listar por id
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoModel> listarAgendamentoPorId(@PathVariable Long id){
        return agendamentoService.consultaAgendamentoPorId(id).map(agendamentoEncontrado -> {
            return ResponseEntity.ok(agendamentoEncontrado);
        }).orElse(ResponseEntity.notFound().build());
    }

    //put - alterar agendamento
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoModel> alterarAgendamento(@PathVariable Long id,@RequestBody AgendamentoModel agendamento){
        return agendamentoService.consultaAgendamentoPorId(id).map(agendamentoEncontrado -> {
            AgendamentoModel agendamentoAtualizado = agendamentoService.alteraAgendamento(id, agendamento);
            return ResponseEntity.ok(agendamentoAtualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id){
        agendamentoService.deletaAgendamento(id);
        return ResponseEntity.noContent().build();
    }


}
