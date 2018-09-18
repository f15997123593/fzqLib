package com.fzq.retrofitlib;

import java.util.List;

/**
 * 作者: Created by fzq on 2018/9/17 15:57
 * 邮箱: 15997123593@163.com
 */
public class SoceList {


    /**
     * date : 20170224
     * stories : [{"images":["http://pic3.zhimg.com/12adf810be0546f5646a0a3978f687fe.jpg"],"type":0,"id":9238989,"ga_prefix":"022422","title":"小事 · 碰到「聋哑人」乞讨的正确做法"},{"images":["http://pic3.zhimg.com/6aa2eded8c40f557d0ecc059609f7076.jpg"],"type":0,"id":9247023,"ga_prefix":"022421","title":"奥斯卡 · 摄影师和工程师看到的《降临》好不一样啊"},{"images":["http://pic4.zhimg.com/ba661c74845cffe7a13cfc41aa2343d7.jpg"],"type":0,"id":9247913,"ga_prefix":"022420","title":"用一道无水焗鱼头，我们来说说「焗」这个做法"},{"images":["http://pic4.zhimg.com/bb039f00b2537148eaeb0a7528f6e65b.jpg"],"type":0,"id":9247594,"ga_prefix":"022420","title":"如何整理冰箱？"},{"title":"为什么现在的手机都趋向于使用不可更换电池？","ga_prefix":"022418","images":["http://pic2.zhimg.com/31d1c614c000a4419785e3e528594eb5.jpg"],"multipic":true,"type":0,"id":9247619},{"title":"很大很贵的苹果新总部，是乔布斯发布的最后一个产品","ga_prefix":"022417","images":["http://pic4.zhimg.com/440db03e2b265036d7045fedc199ee8b.jpg"],"multipic":true,"type":0,"id":9247561},{"images":["http://pic4.zhimg.com/261cf5c1f3f9a6ce94fc9a8a9360d887.jpg"],"type":0,"id":9247490,"ga_prefix":"022416","title":"跟抑郁症患者恋爱是什么感受？"},{"images":["http://pic2.zhimg.com/92221d5886bba8b7e0a3b69d9e2e4cf9.jpg"],"type":0,"id":9247423,"ga_prefix":"022415","title":"全球航运业诞生有史以来最大的破产案，是偶然也是必然"},{"images":["http://pic3.zhimg.com/04ab6cfa73d527063eb9523f97c0efb2.jpg"],"type":0,"id":9247310,"ga_prefix":"022414","title":"一场博弈之后，国际石油市场正从寡头垄断走向完全竞争"},{"title":"只要方法得当，和 3 岁的儿子一起看「枯燥」的科普书，也能看得停不下来","ga_prefix":"022413","images":["http://pic3.zhimg.com/8991e8128e1976d2c9f6c17cecb11b12.jpg"],"multipic":true,"type":0,"id":9241375},{"images":["http://pic2.zhimg.com/6e7dff5a91baaab717a5669ca5c03381.jpg"],"type":0,"id":9244048,"ga_prefix":"022412","title":"大误 · 《冰火演义》VS《黎明与黄昏之歌》"},{"title":"为什么「我们的穿山甲是人工饲养的」根本站不住脚？","ga_prefix":"022411","images":["http://pic1.zhimg.com/ffba1db649b998bb0532d90b55ac6fd0.jpg"],"multipic":true,"type":0,"id":9246977},{"images":["http://pic3.zhimg.com/6c3e156f5121dd96c61ac5eae3df457e.jpg"],"type":0,"id":9246149,"ga_prefix":"022410","title":"在财大气粗地「买买买」的公司，可能财务状况并不好"},{"images":["http://pic1.zhimg.com/7b36e89f1717d3723c284722625aaec8.jpg"],"type":0,"id":9245969,"ga_prefix":"022409","title":"孕妇如何正确地系安全带？不是孕妇也值得记在心里"},{"images":["http://pic4.zhimg.com/1861338918eac0c99b39b55a1f5467ff.jpg"],"type":0,"id":9243959,"ga_prefix":"022408","title":"混合动力的汽油车见过挺多，怎么很少听说柴油混动车？"},{"title":"逃避可耻但有用，咖喱好吃却长胖","ga_prefix":"022407","images":["http://pic1.zhimg.com/972c4bed38a5b1079d24b7c34497daa4.jpg"],"multipic":true,"type":0,"id":9246129},{"images":["http://pic2.zhimg.com/b0f76577e4c5911a250978132e5dce79.jpg"],"type":0,"id":9245906,"ga_prefix":"022407","title":"顺丰都自己建机场了，一个快递公司这么做有必要吗？"},{"images":["http://pic3.zhimg.com/dd9e805ea3ac1d9bc8331342d7470486.jpg"],"type":0,"id":9239420,"ga_prefix":"022406","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic3.zhimg.com/12adf810be0546f5646a0a3978f687fe.jpg"]
         * type : 0
         * id : 9238989
         * ga_prefix : 022422
         * title : 小事 · 碰到「聋哑人」乞讨的正确做法
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
