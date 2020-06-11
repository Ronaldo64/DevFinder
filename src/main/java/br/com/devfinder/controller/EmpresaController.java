package br.com.devfinder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.devfinder.model.Desafio;
import br.com.devfinder.model.Empresa;
import br.com.devfinder.model.Endereco;
import br.com.devfinder.model.ids.DesafioId;
import br.com.devfinder.service.DesafioHabilidadeService;
import br.com.devfinder.service.DesafioService;
import br.com.devfinder.service.EmpresaService;

/**
 * @author Ronaldo Costa
 *
 */
@Controller
public class EmpresaController {

	@Autowired
	private EmpresaService service;

	@Autowired
	private DesafioService serviceD;

	@Autowired
	private DesafioHabilidadeService serviceDH;
	
	
	@GetMapping("/inicioEmpresa/{emailEmpresa}")
	public String Inicio(Model model, @PathVariable String emailEmpresa) {
		model.addAttribute("empresa", service.getEmpresaById(emailEmpresa));
		return "empInicio";
	}
	
	@GetMapping("/formEmpresa")
	public String addForm(Model model) {
		model.addAttribute("empresa", new Empresa());
		model.addAttribute("endereco", new Endereco());
		
		return "formEmpresa";
	}
	
	@PostMapping("/addEmpresa")
	public String addEmpresa(@ModelAttribute Empresa empresa, 
			@ModelAttribute Endereco endereco,
			Model model) {
		
		empresa.setEndereco(endereco);
		service.saveEmpresa(empresa);
		return "empInicio";
		//
		//service.saveEmpresa(empresa);
		//return "foi";
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
	public String findEmpresaById(@PathVariable String email, Model model) {
		model.addAttribute("empresa", service.getEmpresaById(email));
		model.addAttribute("desafios", serviceD.getDesafios(email));
		model.addAttribute("service", serviceDH);
		return "perfilEmpresa";
	}

	@GetMapping("/empresaByNomeFantasia/{nomeFantasia}")
	public Empresa findEmpresatByNomeFantasia(@PathVariable String nomeFantasia) {
		return service.getEmpresaByNomeFantasia(nomeFantasia);
	}

	@PutMapping("/updateEmpresa")
	public Empresa updateEmpresa(@RequestBody Empresa empresa) {
		return service.updateEmpresa(empresa);
	}

	@GetMapping("/deleteEmpresa/{email}")
	public String deleteEmpresa(@PathVariable String email) {
		return service.deleteEmpresa(email);
		
	}
}
