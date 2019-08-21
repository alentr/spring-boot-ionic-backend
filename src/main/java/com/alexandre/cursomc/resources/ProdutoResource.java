package com.alexandre.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexandre.cursomc.domain.Produto;
import com.alexandre.cursomc.dto.ProdutoDTO;
import com.alexandre.cursomc.resources.utils.URL;
import com.alexandre.cursomc.services.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/produtos")
@Api(value="API REST produtos")
//Informe que qualquer domínio pode acessar a API
@CrossOrigin(origins="*")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoSerivice;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ApiOperation(value="Retorna um produto")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = produtoSerivice.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value="Retorna todos os produtos paginados")
	public ResponseEntity<Page<ProdutoDTO>> findPage(
		@RequestParam(value="nome", defaultValue="") String nome, 
		@RequestParam(value="categorias", defaultValue="") String categorias, 
		@RequestParam(value="page", defaultValue="0") Integer page, 
		@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
		@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
		@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		//Decodifica caracteres especiais e espaço em branco
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = produtoSerivice.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
}
