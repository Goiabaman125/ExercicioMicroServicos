package com.aula.pos.msproduto.service;

import com.aula.pos.msproduto.dto.ProdutoDto;
import com.aula.pos.msproduto.dto.ProdutoRequestDto;
import com.aula.pos.msproduto.model.Produto;
import com.aula.pos.msproduto.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ProdutoDto> findAll(Pageable pagination) {
        return produtoRepository.findAll(pagination)
                .map(ProdutoDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ProdutoDto findById(Long id) {
        return produtoRepository.findById(id)
                .map(ProdutoDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    @Override
    @Transactional
    public ProdutoDto save(ProdutoRequestDto request) {
        Produto produto = new Produto();
        produto.setNome(request.nome());
        produto.setQuantidade(request.quantidade());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        return new ProdutoDto(produtoRepository.save(produto));
    }

    @Override
    @Transactional
    public ProdutoDto update(Long id, ProdutoRequestDto request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produto.setNome(request.nome());
        produto.setQuantidade(request.quantidade());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        return new ProdutoDto(produtoRepository.save(produto));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public List<ProdutoDto> findByIds(List<Long> ids) {
        return produtoRepository.findAllById(ids)
                .stream()
                .map(ProdutoDto::new)
                .collect(java.util.stream.Collectors.toList());
    }


}