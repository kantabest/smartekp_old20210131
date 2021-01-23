package com.easyworks.smartekp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
 
    @GetMapping("board/noticsBoard")
    public String noticsBoard() {
        return "board/noticsBoard";
    }

    @GetMapping("board/deptBoard")
    public String deptBoard() {
        return "board/deptBoard";
    }

    @GetMapping("board/jobBoard")
    public String jobBoard() {
        return "board/jobBoard";
    }

    @GetMapping("board/freeBoard")
    public String freeBoard() {
        return "board/freeBoard";
    }

    @GetMapping("board/planBoard")
    public String planBoard() {
        return "board/planBoard";
    }

    @GetMapping("board/qnaBoard")
    public String qnaBoard() {
        return "board/qnaBoard";
    }

    @GetMapping("board/vocBoard")
    public String vocBoard() {
        return "board/vocBoard";
    }


}
