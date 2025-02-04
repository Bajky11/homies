package com.friends.friends.Controller;

import com.friends.friends.Entity.Friend;
import com.friends.friends.Entity.User;
import com.friends.friends.Repository.FriendRepository;
import com.friends.friends.Repository.UserRepository;
import com.friends.friends.dto.FriendDTO;
import com.friends.friends.dto.FriendResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public FriendController(FriendRepository friendRepository, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public List<FriendResponseDTO> getFriends(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        return friendRepository.findByUser(user)
                .stream()
                .map(FriendResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/{userId}/add")
    public String addFriend(@PathVariable Long userId, @RequestBody FriendDTO friendDTO) {
        User user = userRepository.findById(userId).orElseThrow();
        Friend friend = new Friend(user, friendDTO);
        friendRepository.save(friend);
        return "Friend added successfully";
    }

    @DeleteMapping("/{friendId}")
    public String removeFriend(@PathVariable Long friendId) {
        friendRepository.deleteById(friendId);
        return "Friend removed successfully";
    }

    @PutMapping("/{friendId}/increase-score")
    public String increaseFriendScore(@PathVariable Long friendId, @RequestParam  int points) {
        Friend friend = friendRepository.findById(friendId).orElseThrow();
        friend.setScore(friend.getScore() + points);
        friendRepository.save(friend);
        return "Friend score increased";
    }

    @PutMapping("/{friendId}/decrease-score")
    public String decreaseFriendScore(@PathVariable Long friendId, @RequestParam  int points) {
        Friend friend = friendRepository.findById(friendId).orElseThrow();
        friend.setScore(friend.getScore() - points);
        friendRepository.save(friend);
        return "Friend score decreased";
    }
}
