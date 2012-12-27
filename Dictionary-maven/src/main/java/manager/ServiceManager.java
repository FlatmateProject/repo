package manager;

import exception.ServiceException;
import model.AddSesion;
import model.dictionary.WordEntity;
import service.AddApplicationContext;

public interface ServiceManager extends AddSesion, AddApplicationContext {

	WordEntity invokeFindWord(String wordName, long wordId) throws ServiceException;

}
