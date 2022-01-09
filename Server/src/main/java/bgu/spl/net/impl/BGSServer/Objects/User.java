package bgu.spl.net.impl.BGSServer.Objects;

import bgu.spl.net.impl.Commands.ClientToServer.PM;
import bgu.spl.net.impl.Commands.ClientToServer.Post;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class User {
    private String userName;
    private String password;
    private String birthday;
    private boolean loggedIn = false;
    private int connectionId;
    private Set<User> following = new HashSet<>();
    private Set<User> followers= new HashSet<>();
    private Set<User> blocking = new HashSet<>();
    private Set<User> blockers = new HashSet<>();
    private List<Post> posts = new LinkedList<>();
    private List<PM> messagesReceived = new LinkedList<>();

    public User(String userName, String password, String birthday) {
        this.userName = userName;
        this.password = password;
        this.birthday = birthday;
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void login(int connectionId){
        loggedIn = true;
        this.connectionId = connectionId;
    }

    public void logout(){
        loggedIn = false;
        this.connectionId = -1;
    }

    public boolean isFollowing(String userName){
        return following.stream().anyMatch(user -> user.getUserName().equals(userName));
    }

    public void addFollower(User user){
        following.add(user);
        user.followers.add(this);
    }

    public void removeFollower(User user){
        following.remove(user);
        user.followers.remove(this);
    }

    public void addPost(Post post){
        posts.add(post);
    }

    public void receivePrivateMessage(PM message){
        messagesReceived.add(message);
    }

    public short getNumberOfPosts(){
        return ((short) posts.size());
    }

    public short getNumberOfFollowings(){
        return ((short) following.size());
    }

    public short getNumberOfFollowers(){
        return ((short) followers.size());
    }

    public void addToBlockingList(User user){
        blocking.add(user);
    }

    public void addToBlockersList(User user){
        blockers.add(user);
    }

    public boolean isBlockedBy(User user){
        return blockers.contains(user);
    }

    public boolean isBlocking(User user){
        return blocking.contains(user);
    }



}
