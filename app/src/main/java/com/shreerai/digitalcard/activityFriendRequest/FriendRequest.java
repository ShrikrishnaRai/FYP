package com.shreerai.digitalcard.activityFriendRequest;

public interface FriendRequest {
    void acceptFriend(String id,
                      String firstName,
                      String lastName,
                      String company,
                      String position,
                      String image);

    void deleteFriendRequest(String firstName);

    void displayMessage(String message);

    void onSuceed();


}
