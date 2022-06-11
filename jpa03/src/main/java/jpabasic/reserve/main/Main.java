package jpabasic.reserve.main;

import jpabasic.reserve.domain.User;
import jpabasic.reserve.jpa.EMF;
import jpabasic.reserve.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {

    private static UserService userService = new UserService();
    public static void main(String[] args) throws IOException {
        EMF.init();

        try(var br = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                System.out.println("명령어를 입력하세요:");
                var line = br.readLine();
                if(line==null || line.equals("")) {
                    break;
                }
                if(line.startsWith("new ")) {
                    handleNew(line);
                } else if (line.startsWith("get ")) {
                    handleGet(line);
                } else if (line.startsWith("change name ")) {
                    handleChangeName(line);
                } else if (line.startsWith("remove ")) {
                    handleRemove(line);
                } else if(line.startsWith("exit")) {
                    break;
                }
            }
        } finally {
            EMF.close();
        }
    }
    static void handleNew(String line) {
        var v = line.substring(4).split(" ");
        try {
            userService.save(new User(v[0], v[1], LocalDateTime.now()));
        } catch (Exception e) {
            System.out.println("사용자 추가 실패");
        }
    }
    static void handleGet(String line) {
        try {
            userService.getUser(line.substring(4));
        } catch (Exception e) {
            System.out.println("사용자 조회 실패");
        }
    }
    static void handleChangeName(String line) {
        var v = line.substring(12).split(" ");

        try {
            userService.update(v[0], v[1]);
        } catch (Exception e) {
            System.out.println("사용자 이름 변경 실패");
        }
    }
    static void handleRemove(String line) {
        try {
            userService.delete(line.substring(7));
        } catch (Exception e) {
            System.out.println("사용자 삭제 실패");
        }
    }
}
