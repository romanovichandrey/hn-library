package by.romanovich.it.service.service;

import by.romanovich.it.dao.AutorDao;
import by.romanovich.it.dao.Dao;
import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.Autor;
import by.romanovich.it.service.exeptions.ServiceErrorCode;
import by.romanovich.it.service.exeptions.ServiceExeption;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Implementing AutorService interface.
 * Processing business-logic for work with Autor.
 * @see by.romanovich.it.service.service.AutorService
 * @author Romanovich Angrei
 * @version 1.0
 */
public class AutorServiceImpl implements AutorService {

    private static Logger log = Logger.getLogger(AutorServiceImpl.class);

    private static AutorServiceImpl autorService = null;

    private Dao<Autor, Long> autorDao = null;

    private AutorServiceImpl() {
        autorDao = new AutorDao(Autor.class);
    }

    public static AutorServiceImpl getAutorService() {
        if(autorService == null) {
            autorService = new AutorServiceImpl();
        }
        return autorService;
    }

    @Override
    public Boolean saveAutor(Autor autor) throws ServiceExeption {
        try {
            autorDao.add(autor);
            log.info("Adding autor:" + autor);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_020);
        }
    }

    @Override
    public Boolean updateAutor(Autor autor) throws ServiceExeption {
        try {
            autorDao.update(autor);
            log.info("Updating autor:" + autor);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_016);
        }
    }

    @Override
    public Autor getAutorById(Long id) throws ServiceExeption {
        Autor autor = null;
        try {
            autor = autorDao.get(id);
            log.info("Getting autor:" + autor);
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_017);
        }
        return autor;
    }

    @Override
    public List<Autor> getAllAutors() throws ServiceExeption {
        List<Autor> autors;
        try {
            autors = autorDao.getAll();
            for(Autor autor : autors)
                log.info("Getting all autors:" + autor);
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_018);
        }
        return autors;
    }

    @Override
    public Boolean deleteAutor(Autor autor) throws ServiceExeption {
        try {
            autorDao.delete(autor);
            log.info("Deleting autor:" +autor);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_019);
        }
    }

}
