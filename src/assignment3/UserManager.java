package assignment3;

import assignment2.InvalidStringException;
import assignment2.TradableDTO;

import java.util.HashMap;

// Singleton
public final class UserManager {
    private static UserManager instance;

    private HashMap<String, User> users;

    private UserManager() {
        users = new HashMap<String, User>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }

        return instance;
    }

    public void init(String[] usersIn) throws DataValidationException, InvalidStringException {
        for (String u : usersIn) {
            if (u == null) {
                throw new DataValidationException("Provided user string is null");
            }

            User user = new User(u);
            users.put(u, user);
        }
    }

    // Places each user in hashmap into array, which can be accessed with a random index
    public User getRandomUser() {
        if (users.isEmpty()) {
            return null;
        }

        String[] usersList = new String[users.size()];
        int index = 0;
        for (String u : users.keySet()) {
            usersList[index] = u;
            index += 1;
        }

        int rand = (int) (Math.random() * (usersList.length));
        return users.get(usersList[rand]);
    }

    public void addToUser(String userId, TradableDTO o) throws DataValidationException {
        if (userId == null) {
            throw new DataValidationException("Provided userId is null");
        }
        if (o == null) {
            throw new DataValidationException("Provided TradableDTO is null");
        }
        if (users.get(userId) == null) {
            throw new DataValidationException("Provided userId does not exist in hashmap");
        }

        users.get(userId).addTradable(o);
    }

    public User getUser(String id) throws DataValidationException {
        if (id == null) {
            throw new DataValidationException("Provided id is null");
        }

        // Returns null if id key is not found and returns null if id-matching user is null
        return users.get(id);
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();

        for (String u : users.keySet()) {
            msg.append(users.get(u).toString() + "\n");
        }

        return msg.toString();
    }
}
