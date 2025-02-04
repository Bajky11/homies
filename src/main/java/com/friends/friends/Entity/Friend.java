package com.friends.friends.Entity;

import com.friends.friends.dto.FriendDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "friends")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "friend_name", nullable = false)
    private String name;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "score", nullable = false)
    private int score = 0;

    public Friend(User user, FriendDTO friendDTO) {
        this.user = user;
        this.name = friendDTO.getName();
        this.birthday = friendDTO.getBirthday();
    }
}
