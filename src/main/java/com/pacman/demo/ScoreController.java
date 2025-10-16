package com.pacman.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "*") // 開発中は許可。運用では適切に制限すること
public class ScoreController {
    private final List<Score> scores = new CopyOnWriteArrayList<>();

    @PostMapping
    public Score submitScore(@RequestBody Score score){
        scores.add(score);
        return score;
    }

    @GetMapping
    public List<Score> getScores(){
        // 降順ソートして返す（コピーしてソート）
        List<Score> copy = new ArrayList<>(scores);
        copy.sort((a,b) -> Integer.compare(b.getScore(), a.getScore()));
        return copy;
    }
}