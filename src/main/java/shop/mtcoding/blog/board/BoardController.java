//package shop.mtcoding.blog.board;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import shop.mtcoding.blog.user.User;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Controller
//public class BoardController {
//    private final BoardRepository boardRepository;
//    private final HttpSession session;
//
//    //http://localhost:8080?page=0
//    @GetMapping({ "/", "/board" })
//    public String index(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) { //null로 초기화할 수 있다
//        //System.out.println("페이지 : "+page);
//        List<Board> boardList = boardRepository.findAll(page);
//        request.setAttribute("boardList", boardList); //가방에담는다
//
//        int currentPage = page;
//        int nextPage = currentPage+1;
//        int prevPage=currentPage-1;
//        request.setAttribute("nextpage", nextPage);
//        request.setAttribute("prevPage", prevPage);
//
//        boolean first = currentPage == 0 ? true:false;
//
//        //int totalCount=4;
//        //=totalCount/3+1;
//        //boolean last =true;
//        request.setAttribute("first", first);
//
//
//        return "index";
//    }
//
//    @GetMapping("/board/saveForm")
//    public String saveForm() {
//        return "board/saveForm";
//    }
//
//    @GetMapping("/board/1")
//    public String detail() {
//        return "board/detail";
//    }
//}
package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.blog._core.PagingUtil;
import shop.mtcoding.blog.user.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;

    // http://localhost:8080?page=0
    @GetMapping({ "/", "/board"})
    public String index(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {
        //System.out.println("페이지: " +page);
        List<Board> boardList = boardRepository.findAll(page);
        request.setAttribute("boardList", boardList); // 가방에 담아서 뷰에서 쓸 수 있게 해줌

        int currentPage = page;
        int nextPage = currentPage+1;
        int prevPage = currentPage-1;
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("prevPage", prevPage);
        // 이것만 담으면 디세이브를 못한다.

        // 현재 페이지가 퍼스트인지 라스트인지 만든다.




//        int totalCount = boardRepository.totalCount(); // db 조회해서 카운트 가져오기
//        int paging = 3;
//        int totalPage;
//        totalPage=(totalCount/paging)+(totalCount%paging>0 ? 1:0);
//        //boolean last = true;
//        System.out.println(totalPage);
//        request.setAttribute("totalPage", totalPage);

        boolean first = PagingUtil.isFirst(currentPage);
        boolean last = PagingUtil.isLast(currentPage,boardRepository.totalCount());



        request.setAttribute("first", first);
        request.setAttribute("last", last);


        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/1")
    public String detail() {
        return "board/detail";
    }
}