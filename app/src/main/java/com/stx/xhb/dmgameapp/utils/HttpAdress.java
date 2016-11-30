package com.stx.xhb.dmgameapp.utils;

/**
 * Created by xhb on 2016/1/19.
 * 网络请求地址工具类
 */
public class HttpAdress {
    //3DMGame网站地址
    public static final String DMGEAME_URL = "http://www.3dmgame.com";
    //服务器接口地址
    public static final String API_URL = "http://www.3dmgame.com/sitemap/api.php";
    //新闻列表接口地址
    public static final String YUN_API_CHANNEL_URL = "http://59.110.23.172/api/channels";
    public static final String YUN_API_NEWS_URL = "http://59.110.23.172/api/articles/channel/6";
    public static final String YUN_API_NEWS_CONTENT = "http://59.110.23.172/api/articles/show/1";

    public static final String NEWS_URL = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=1&paging=1&page=%s";
    //文章列表接口地址
    //public static final String ARTICLE_URL = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=%s&paging=1&page=%s";
    public static final String ARTICLE_URL = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=%s&paging=1&page=1";
    //文章详情的接口地址
    public static final String ChapterContent_URL = "http://59.110.23.172/articles/%s";
    //评论列表接口
    public static final String COMMENT_URL = "http://www.3dmgame.com/sitemap/api.php?type=1&aid=%s&pageno=%s";
    //评论提交接口
    public static final String COMMENT_COMMIT_URL = "http://www.3dmgame.com/sitemap/api.php?type=2";
    //游戏列表获取接口
    public static final String GAME_URL = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=%s&paging=1&page=%s";

    //userlogin && register
    public static final String LOGIN_URL ="https://passportforapp.skykiwi.com/v2/login/logging.do?username=%s&password=%s";
    public static final String USER_URL = "https://passportforapp.skykiwi.com/v2/member/self.do?uid=%s&token=%s";

    public static final String REG_INIT_URL="https://passportforapp.skykiwi.com/v2/register/init.do?v=1.0&c=Android&cv=4.4&device_id=123456781234";
    public static final String REG_PIC_VERIFY_URL="https://passportforapp.skykiwi.com/v2/register/picVerifycode.do?rndUid=%s&v=1.0&c=Android&cv=4.4&device_id=123456781234";
    public static final String REG_EMAIL_VERIFY_URL="https://passportforapp.skykiwi.com/v2/register/sendmail.do?email=%s&username=%s&rndUid=%s&picVerifyCode=%s";
    public static final String REG_URL = "https://passportforapp.skykiwi.com/v2/register/done.do?emailVerifyCode=%s&email=%s&username=%s&password=%s";

    //频道
    public static final String CHANNEL_URL = "http://59.110.23.172/api/channels";
    //头条
    public static final String TOPLINE_URL = "http://59.110.23.172/api/sort_links/with_photos?page=%s";
    //其他频道
    public static final String OTHER_LIST_URL = "http://59.110.23.172/api/articles/channel/%S";


    /*
 登录接口：
https://passportforapp.skykiwi.com/v2/login/logging.do

1\ 会话初始化
curl "https://passportforapp.skykiwi.com/v2/register/init.do"

f9DMUsvBj45u0VfWgB8Ivl0tcWMZsdsn
Srs6guQmc2AUeiy9wjQaNaLOuLjOEGCP

2、图片验证码：
curl “https://passportforapp.skykiwi.com/v2/register/picVerifycode.do?mdUid=Srs6guQmc2AUeiy9wjQaNaLOuLjOEGCP”
－－返回图片

3、邮箱验证码：
curl “https://passportforapp.skykiwi.com/v2/register/sendmail.do?email=pingwang@sina.cn&username=hasty2016&mdUid=f9DMUsvBj45u0VfWgB8Ivl0tcWMZsdsn&picVerifyCode=
--倒计时

{
    "signal": 100090,
    "msg": "",
    "errors": {
        "email": "邮件账户已经存在，请您换一个邮件账户注册。"
    },
    "data": []
}
{
    "signal": 2170,
    "msg": "错误。",
    "errors": {
        "username": "昵称已经存在，请换一个。"
    },
    "data": []
}
4、注册：
curl “https://passportforapp.skykiwi.com/v2/register/done.do?emailVerifyCode=&email=pingwang@sina.cn&username=hasty2016&password=antilei01"
     {"signal":100081,"msg":"","errors":{"password":"\u767b\u5f55\u5bc6\u7801\u957f\u5ea6\u4e0d\u7b26\u5408\u89c4\u8303\u3002\u8bf7\u4fdd\u6301\u57286\u4e2a\u5b57\u7b26\u523032\u4e2a\u5b57\u7b26\u4e4b\u95f4\u3002"},"data":[]}


     "errors":{"emailVerifyCode":"\u90ae\u7bb1\u9a8c\u8bc1\u7801\u9519\u8bef\u3002\u8bf7\u60a8\u91cd\u65b0\u53d1\u9001\u90ae\u7bb1\u9a8c\u8bc1\u7801\u3002"},"data":[]}
     {"signal":1,"msg":"","errors":[],"data":{"uid":"10045145"}}
     */
}
