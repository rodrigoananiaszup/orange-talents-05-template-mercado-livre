package br.com.zupacademy.rodrigo.mercadolivre.produto.imagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;

public class ImagemDto {

	@Size(min = 1) @NotNull
	private List<MultipartFile> imagens = new ArrayList<>();
	

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	public ImagemDto(@Size(min = 1) List<MultipartFile> imagens) {
		super();
		this.imagens = imagens;
	}

	@Override
	public String toString() {
		return "ImagemDto [imagens=" + imagens + "]";
	}
	
	public List<Imagem> toModel (Produto produto, Set<String> links){
		
		List<Imagem> imagens = new ArrayList<Imagem>();
		links.forEach(url -> {
			Imagem imagem = new Imagem(url, produto);
			imagens.add(imagem);
		});
		
		return imagens;
		
	}
}
