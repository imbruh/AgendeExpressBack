package br.edu.ifpb.agendeexpress.AgendeExpress.Controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.agendeexpress.AgendeExpress.DTO.HorarioCadastrarDTO;
import br.edu.ifpb.agendeexpress.AgendeExpress.DTO.HorarioListarDTO;
import br.edu.ifpb.agendeexpress.AgendeExpress.Service.HorarioService;

@RestController
@RequestMapping(value = "/horario")
@CrossOrigin(origins = "https://agende-express-front.herokuapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
public class HorarioController {

	@Autowired
	private HorarioService horarioService;
	
	@PostMapping("/cadastrar")
	public Boolean cadastrar(@RequestBody HorarioCadastrarDTO dto) {
		return this.horarioService.cadastrar(dto);
	}
	
	@GetMapping("/listar")
	public List<HorarioListarDTO> listar(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHora, @RequestParam @NotNull Long idEmpresa){
		return this.horarioService.listar(dataHora, idEmpresa);
	}
	
	@GetMapping("/filtrar-horario")
	public List<String> filtrar(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data, @RequestParam @NotNull Long idEmpresa){
		return this.horarioService.filtrar(data, idEmpresa);
	}
	
	@GetMapping("/buscar-horario-por-cliente")
	public List<HorarioListarDTO> buscarHorarioPorCliente(@RequestParam @NotNull Long idCliente, @RequestParam @NotNull Long idEmpresa){
		return this.horarioService.buscarHorarioPorCliente(idCliente, idEmpresa);
	}
	
	@DeleteMapping("/apagar-horario")
	public List<HorarioListarDTO> apagarHorario(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHora, @RequestParam Long idCliente, @RequestParam Long idEmpresa ) {
		return this.horarioService.apagar(dataHora, idCliente, idEmpresa);
	}
	
}
