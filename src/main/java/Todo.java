import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;

    private String todo;

    private String limit;

    public String getTodo() {
        return todo;
    }

    public void setATodo(String todo) {
        this.todo = todo;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
