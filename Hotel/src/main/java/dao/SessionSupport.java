package dao;

import dao.impl.Singleton;

public interface SessionSupport {

    Singleton getSession();

    void setSession(Singleton session);

}
