package br.com.zup.mercadolivre.validators;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class UnicoValidator implements ConstraintValidator<Unico, Object>{

	private String atributo;
	private Class<?> classe;
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public void initialize(Unico params) {
		atributo = params.atributo();
		classe = params.classe();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from "+classe.getName()+" where "+atributo+"=:value");
		query.setParameter("value", value);
		List<?> lista = query.getResultList();
		Assert.state(lista.size() <=1, "Foi encontrado mais de um valor");
		return lista.isEmpty();
	}

}
