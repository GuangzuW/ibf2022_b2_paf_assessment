package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.Utils;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.services.TodoException;

// TODO: Task 3
@Repository
public class UserRepository {

    private static final String SQL_FIND_USER_BY_USERNAME = """
            select * from user where username = ?
            """;
    
    private static final String SQL_INSERTUSER = """
            insert into user (user_id, username, name) values (?, ?, ?)
            """;
    @Autowired
	private JdbcTemplate template;

    public Optional<User> findUserByUsername(String username) {
        SqlRowSet rs = template.queryForRowSet(SQL_FIND_USER_BY_USERNAME, username);
        if(!rs.next()) 
            return Optional.empty();
        return Optional.of(Utils.toUser(rs));
     }

     public String insertUser(User user) throws TodoException{
        String userId = UUID.randomUUID().toString().substring(0, 8);

        if(template.update(SQL_INSERTUSER, userId, user.getUsername(), user.getName())>0)
            return userId;
        else{
            throw new TodoException("User not inserted");
        }
     }


}
