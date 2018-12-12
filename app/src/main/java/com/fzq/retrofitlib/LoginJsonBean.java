package com.fzq.retrofitlib;

/**
 * 作者: Created by fzq on 2018/12/12 10:20
 * 邮箱: 15997123593@163.com
 */
public class LoginJsonBean {

    /**
     * code : 200
     * message : 操作成功
     * result : {"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJnb2RDaGlzaiIsInNjb3BlIjpbIlwiKlwiIl0sImxvZ2luTmFtZSI6ImdvZENoaXNqIiwiZXhwIjoxNTQ0NTg4MzgyLCJqdGkiOiJkYmM3ZmE3ZC0xMDE1LTQ3Y2ItOWJmMi0zYjBhOTk4N2RlOGMiLCJjbGllbnRfaWQiOiJjbW55LWNsaWVudC11YWMiLCJ0aW1lc3RhbXAiOjE1NDQ1ODExODIwMjJ9.lgOeOxvgAtSADLDsVeuBVBneP58B3rveW-R8_Z3Dhzo","token_type":"bearer","refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJnb2RDaGlzaiIsInNjb3BlIjpbIlwiKlwiIl0sImxvZ2luTmFtZSI6ImdvZENoaXNqIiwiYXRpIjoiZGJjN2ZhN2QtMTAxNS00N2NiLTliZjItM2IwYTk5ODdkZThjIiwiZXhwIjoxNTQ3MTczMTgyLCJqdGkiOiI2MWU0OTAzMy04YjAxLTRiZDQtYTcxNy1kZDJmNTA5MmVmZDgiLCJjbGllbnRfaWQiOiJjbW55LWNsaWVudC11YWMiLCJ0aW1lc3RhbXAiOjE1NDQ1ODExODIwMjJ9.oMbxKe_wuqmu0ePVRI6kGNAfKtxxMzOoZYwei-jZfM0","expires_in":7199,"scope":"\"*\"","timestamp":1544581182022,"loginName":"godChisj","jti":"dbc7fa7d-1015-47cb-9bf2-3b0a9987de8c"}
     */

    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * access_token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJnb2RDaGlzaiIsInNjb3BlIjpbIlwiKlwiIl0sImxvZ2luTmFtZSI6ImdvZENoaXNqIiwiZXhwIjoxNTQ0NTg4MzgyLCJqdGkiOiJkYmM3ZmE3ZC0xMDE1LTQ3Y2ItOWJmMi0zYjBhOTk4N2RlOGMiLCJjbGllbnRfaWQiOiJjbW55LWNsaWVudC11YWMiLCJ0aW1lc3RhbXAiOjE1NDQ1ODExODIwMjJ9.lgOeOxvgAtSADLDsVeuBVBneP58B3rveW-R8_Z3Dhzo
         * token_type : bearer
         * refresh_token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJnb2RDaGlzaiIsInNjb3BlIjpbIlwiKlwiIl0sImxvZ2luTmFtZSI6ImdvZENoaXNqIiwiYXRpIjoiZGJjN2ZhN2QtMTAxNS00N2NiLTliZjItM2IwYTk5ODdkZThjIiwiZXhwIjoxNTQ3MTczMTgyLCJqdGkiOiI2MWU0OTAzMy04YjAxLTRiZDQtYTcxNy1kZDJmNTA5MmVmZDgiLCJjbGllbnRfaWQiOiJjbW55LWNsaWVudC11YWMiLCJ0aW1lc3RhbXAiOjE1NDQ1ODExODIwMjJ9.oMbxKe_wuqmu0ePVRI6kGNAfKtxxMzOoZYwei-jZfM0
         * expires_in : 7199
         * scope : "*"
         * timestamp : 1544581182022
         * loginName : godChisj
         * jti : dbc7fa7d-1015-47cb-9bf2-3b0a9987de8c
         */

        private String access_token;
        private String token_type;
        private String refresh_token;
        private int expires_in;
        private String scope;
        private long timestamp;
        private String loginName;
        private String jti;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getJti() {
            return jti;
        }

        public void setJti(String jti) {
            this.jti = jti;
        }
    }
}
