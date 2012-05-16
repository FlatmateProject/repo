package manager;

import service.AddApplicationContext;
import model.AddSesion;
import model.dictionary.WordEntity;
import exception.ServiceException;

public interface ServiceManager extends AddSesion, AddApplicationContext {

	WordEntity invokeFindWord(String wordName, long wordId) throws ServiceException;

}
