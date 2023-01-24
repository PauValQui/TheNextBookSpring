package com.paula.thenextbook;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paula.thenextbook.model.Libro;
import com.paula.thenextbook.repository.LibroRepository;

@SpringBootApplication
public class ThenextbookApplication implements CommandLineRunner{

	@Autowired
	private LibroRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(ThenextbookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private void guardar() {
		Libro libro = new Libro();
		libro.setTitulo("El duque y yo");
		libro.setSinopsis("Todos parecían divertirse en aquel baile que reunía a lo más selecto de la sociedad londinense. Todos, excepto ellos dos. Daphne, una hermosa joven agobiada por su madre, y Simon, el huraño nuevo duque de Hastings, tenían el mismo problema: la continua presión para que encontraran pareja. Al conocerse, se les ocurrió el plan perfecto: fingir un compromiso que los liberara de más agobios. Pero no sería sencillo, ya que el hermano de Daphne, amigo de Simon, no es fácil de engañar, ni tampoco lo son las avezadas damas de la alta sociedad. Aunque lo que complicará de verdad las cosas será la aparición de un elemento que no estaba previsto en este juego a dos bandas: el amor.\n"
				+ "\n"
				+ "            IDEARON UN PLAN PERFECTO EN EL QUE EL AMOR NO TENÍA CABIDA...\n"
				+ "            Desde que fue presentada en sociedad, Daphne no tiene un momento de respiro. La culpa es de su madre, a la que adora, pero que está obsesionada con encontrarle un marido cuanto antes. Lo peor del caso es que los hombres razonablemente deseables no están interesados, y los que sí lo están son unos incansables pesados de los que tiene que librarse... incluso a golpes. Por eso acepta encantada la idea del duque de Hastings de fingir un noviazgo que ahuyente a los pretendientes. Aunque quizá también tenga algo que ver el hecho de que el joven duque comienza a resultarle cada vez más seductor.\n"
				+ "            \n"
				+ "            PERO HAY COSAS DE LAS QUE ES IMPOSIBLE ESCAPAR\n"
				+ "            Marcado por una infancia llena de soledad y resentimiento, Simon Basset, el nuevo duque de Hastings, no quiere saber nada de la vida social de Londres ni, desde luego, de los intentos de las elegantes damas de \\\"cazarlo\\\" como marido para sus hijas. Cuando conoce a Daphne, cree haber encontrado el plan perfecto: un compromiso ficticio que mantenga alejadas a las pretendientes que lo agobian. Y cuando la atracción fingida comienza a convertirse en algo demasiado real, Simon deberá enfrentarse a los fantasmas del pasado que le impiden disfrutar la felicidad que el destino pone al alcance de su mano.\n"
				+ "            ");
		libro.setPrecio(15.00);
		libro.setImagen("Img\\FotosLibros\\libro1.jpg");
		libro.setIdCategoria(1);
		libro.setIdAutor(5);
		
		repo.save(libro);
		
	}
	
	private void buscarPorId() {
		Optional<Libro> optional = repo.findById(1);
		if(optional.isPresent()) {
			System.out.println(optional.get());
		}else {
			System.out.println("Libro no encontrado");
		}
	}
	
	private void modificar() {
		Optional<Libro> optional = repo.findById(1);
		if(optional.isPresent()) {
			Libro libroTmp = optional.get();
			
			libroTmp.setPrecio(18.9);
			
			repo.save(libroTmp); 
//Lo que hace es actualizar el libro que se selecciona y se guarda pero en verdad hace un update.
			System.out.println(optional.get());
		}else {
			System.out.println("Libro no encontrado");
		}
	}
	
	private void eliminar() {
		repo.deleteById(1);
	}
	
	private void conteo() {
		long count = repo.count();
		
		System.out.print(count);
	}

}










