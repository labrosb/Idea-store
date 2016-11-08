 package services;
 
 
 import java.util.Collection;
 
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 import org.springframework.util.Assert;
 
 import domain.Idea;
 import domain.Comment;
 import domain.Label;
 
 import repositories.IdeaRepository;
 import repositories.LabelRepository;
 import repositories.CommentRepository;
 
 
 @Transactional
 @Service
 public class IdeaService {
 
 	// Managed repository-----------------------
 
 		@Autowired
 		private IdeaRepository ideaRepository;
		@Autowired
		private LabelRepository labelRepository;
		@Autowired
		private CommentRepository commentRepository;
		
 
 		// Supporting services -----------------
 
 		// Constructors --------------------------
 		public IdeaService() {
 			super();
 		}
 		
 		// Simple CRUD methods -----------------
 		/**
 		 * Constructor por defecto de IdeaService
 		 * 
 		 * @return Idea idea
 		 */
 		public Idea create() {
 			Idea idea = new Idea();
 
 			return idea;
 		}
 
 		/**
 		 * Devuelve una colecci�n con todos los objetos de tipo Idea
 		 * 
 		 * @return Collection<Idea> ideas
 		 */
 		public Collection<Idea> findAll() {
 //			Assert.isTrue(userService.IAmAnAdmin());
 			return ideaRepository.findAll();
 		}
 		
 		
 		/**
 		 * Devuelve una instancia de un objetos de tipo Idea En caso de no
 		 * encontrarse, devuelve null
 		 * 
 		 * @return Idea idea
 		 */
 		public Idea findOne(int ideaId) {
 			return ideaRepository.findOne(ideaId);
 		}
 
 		/**
 		 * Persiste (guarda o crea) el objeto de tipo Idea en la base de datos a
 		 * trav�s del repositorio IdeaRepository
 		 * 
 		 * @return void
 		 */
 		public void save(Idea idea) {
 			// TODO Restricciones de Save 
 			//WE HAVE TO PUT HERE THE USER ROLE (IF ITS ADMIN OR NOT TO DO THE ACTION)
 
 			ideaRepository.save(idea);
 		}
 
 		/**
 		 * Elimina el objeto de tipo Idea de la base de datos a trav�s del
 		 * repositorio IdeaRepository
 		 * 
 		 * @return void
 		 */
 		public void delete(Idea idea) {
 			Assert.notNull(idea);
 			// TODO Restricciones de Borrado

			
			for (Label label : idea.getLabels()) {
			    labelRepository.delete(label);
			}
			
			for (Comment comment : idea.getComments()) {
			    commentRepository.delete(comment);
			}
 			ideaRepository.delete(idea);
 		}
 
 }