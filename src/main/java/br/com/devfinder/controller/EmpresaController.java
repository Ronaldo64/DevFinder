package br.com.devfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.devfinder.model.Desafio;
import br.com.devfinder.model.Empresa;
import br.com.devfinder.service.EmpresaService;

/**
 * @author Ronaldo Costa
 *
 */
@Controller
public class EmpresaController {

	@Autowired
	private EmpresaService service;

	@GetMapping("/")
	public String home(Model model) {
		//model.addAttribute("empresa", service.getEmpresaById("aaa@gmail.com"));
		//.addAttribute("desafio", new Desafio());
		//model.addAttribute("habilidade", new String());
		return "homepage";
	}
	
	@GetMapping("/inicioEmpresa/{emailEmpresa}")
	public String Inicio(Model model, @PathVariable String emailEmpresa) {
		model.addAttribute("empresa", service.getEmpresaById(emailEmpresa));
		return "empInicio";
	}
	
	@PostMapping("/addEmpresa")
	public Empresa addEmpresa(@RequestBody Empresa empresa) {
		return service.saveEmpresa(empresa);
	}

	@PostMapping("/addEmpresas")
	public List<Empresa> addEmpresas(@RequestBody List<Empresa> empresas) {
		return service.saveEmpresas(empresas);
	}

	@GetMapping("/empresas")
	public List<Empresa> findAllEmpresas() {
		return service.getEmpresas();
	}

	@GetMapping("/empresaById/{email}")
	public Empresa findEmpresaById(@PathVariable String email) {
		return service.getEmpresaById(email);
	}

	@GetMapping("/empresaByNomeFantasia/{nomeFantasia}")
	public Empresa findEmpresatByNomeFantasia(@PathVariable String nomeFantasia) {
		return service.getEmpresaByNomeFantasia(nomeFantasia);
	}

	@PutMapping("/updateEmpresa")
	public Empresa updateEmpresa(@RequestBody Empresa empresa) {
		return service.updateEmpresa(empresa);
	}

	@DeleteMapping("/deleteEmpresa/{email}")
	public String deleteEmpresa(@PathVariable String email) {
		return service.deleteEmpresa(email);
	}
}
