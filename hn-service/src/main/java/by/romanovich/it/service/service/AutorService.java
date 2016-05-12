package by.romanovich.it.service.service;

import by.romanovich.it.pojos.Autor;
import by.romanovich.it.service.exeptions.ServiceExeption;

import java.util.List;

/**
 * Interface for AutorServiceImpl class.
 * @see by.romanovich.it.service.service.AutorServiceImpl
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface AutorService {

    /**
     * This method updating autor.
     * @param autor
     * @return true if autor successfully is updated.
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    Boolean updateAutor(Autor autor) throws ServiceExeption;

    /**
     * This method getting autor by id.
     * @param id Autor id
     * @return Autor
     * @throws ServiceExeption
     */
    Autor getAutorById(Long id) throws ServiceExeption;

    /**
     * This method getting all autors
     * @return list autors
     * @throws ServiceExeption
     */
    List<Autor> getAllAutors() throws ServiceExeption;

    /**
     * This method delete autor.
     * @param autor
     * @return true if autor successfully is delete.
     * @throws ServiceExeption
     */
    Boolean deleteAutor(Autor autor) throws ServiceExeption;

    /**
     * This method saving new autor.
     * @param autor
     * @return true if autor successfully is save.
     * @throws ServiceExeption
     */
    Boolean saveAutor(Autor autor) throws ServiceExeption;
}
