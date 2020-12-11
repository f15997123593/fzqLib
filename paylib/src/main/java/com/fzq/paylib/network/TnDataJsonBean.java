package com.fzq.paylib.network;

/**
 * 作者: Created by fzq on 2020/5/27 14:14
 * 邮箱: 15997123593@163.com
 */
public class TnDataJsonBean {


    /**
     * result : {"tn":"887279747894739561020"}
     * code : 200
     * message : 操作成功
     */

    private ResultBean result;
    private int code;
    private String message;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * tn : 887279747894739561020
         */

        private String tn;

        public String getTn() {
            return tn;
        }

        public void setTn(String tn) {
            this.tn = tn;
        }
    }
}
