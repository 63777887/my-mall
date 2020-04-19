package club.banyuan.zgMallMgt.dto;

public class AdminLoginResp {
        /**
         * tokenHead : Bearer
         * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE1ODY5NTYzNzAxMDIsImV4cCI6MTU4NzU2MTE3MH0.T58Ea1BypVDfGN0oDdKKeVx3QVHY1WiAIQJZfbw2tdaNU35EJt66KVbJJ6xEBPC3VYbgFJ12t-0Kh2wC8x9J_g
         */

        private String tokenHead;
        private String token;

        public String getTokenHead() {
            return tokenHead;
        }

        public void setTokenHead(String tokenHead) {
            this.tokenHead = tokenHead;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;

    }
}
