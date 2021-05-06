package br.com.zup.mercadolivre.dto;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.zup.mercadolivre.model.Opiniao;

public class Opinioes {

		private Set<Opiniao> opinioes;
		
		public Opinioes(Set<Opiniao> opinioes) {
			this.opinioes = opinioes;
		}
		
		public <T> Set<T> mapeia(Function<Opiniao, T> funcao){
			return this.opinioes.stream().map(funcao).collect(Collectors.toSet());
		}
		
		public double media() {
			Set<Integer> notas = mapeia(opiniao -> opiniao.getNota());
			OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
		return possivelMedia.orElse(0.0);
		}

		public Set<Opiniao> getOpinioes() {
			return opinioes;
		}

		public Integer qntNotas() {
			Set<Integer> notas = mapeia(opiniao -> opiniao.getNota());
			return notas.size();
		}


	
}



