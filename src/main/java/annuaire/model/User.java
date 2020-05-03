package annuaire.model;

import annuaire.model.Person;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component()
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean isConnected = false;

    private boolean isBadPassword = false;

    private Person person;

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean isconnected) {
        this.isConnected = isconnected;
    }

    public boolean isBadPassword() {
        return isBadPassword;
    }

    public void setBadPassword(boolean badPassword) {
        isBadPassword = badPassword;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "User{" +
                "isConnected=" + isConnected +
                ", person=" + person +
                '}';
    }
}
