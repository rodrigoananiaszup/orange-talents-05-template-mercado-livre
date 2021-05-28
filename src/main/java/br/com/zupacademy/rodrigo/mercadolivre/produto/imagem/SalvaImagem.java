package br.com.zupacademy.rodrigo.mercadolivre.produto.imagem;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SalvaImagem implements UploadImagem {

	public Set<String> enviaImagem(List<MultipartFile> imagens) {

		return imagens.stream().map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}
}
