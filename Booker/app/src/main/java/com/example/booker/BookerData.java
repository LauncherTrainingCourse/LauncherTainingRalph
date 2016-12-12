package com.example.booker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ralph_Chao on 2016/12/7.
 */

public class BookerData extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BookerDB.db";
    private String myBookTable = "myBookTable";
    private String newBookTable = "vewBookTable";
    ArrayList<BookContent> myBookData = new ArrayList<>();

    public BookerData(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("DataBase", "Create");
        String SQL = "CREATE TABLE IF NOT EXISTS "+ myBookTable +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title VARCHAR(50), " +
                "author VARCHAR(30), " +
                "synopsis TEXT, " +
                "image TEXT)";
        db.execSQL(SQL);
        initialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String SQL = "DROP TABLE IF EXISTS " + myBookTable;
        db.execSQL(SQL);
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + myBookTable );
    }

    public void initialData(SQLiteDatabase db) {
        insertData();
        ContentValues values = new ContentValues();
        for(int i = 0; i < myBookData.size(); i++) {
            values.put("title", myBookData.get(i).getTitle());
            values.put("author", myBookData.get(i).getAuthor());
            values.put("synopsis", myBookData.get(i).getSynopsis());
            db.insert(myBookTable, null, values);
        }
    }

    public void insertData() {
        myBookData.add(new BookContent("哈利波特(1)：神秘的魔法石", "J.K羅琳", "在世界的另一個角落裡，有一個神秘的國度，裡面住滿了巫師，貓頭鷹是他們的信差，飛天掃帚是交通工具，西洋棋子會思考，幽靈頑皮鬼滿天飛，畫像裡的人還會跑出來串門子。\n" +
                "\n" +
                "　　　十一歲的哈利波特，從小被阿姨一家當成怪胎，經常得滿屋子躲避表哥達力的追打。他一直以為自己只是個平凡的小男孩，直到一封又一封神秘的信，將他帶入這個充滿神奇魔法的巫師世界，而他的身世之謎與魔法石的秘密也將同時解開。\n" +
                "\n" +
                "　　　「哈利波特」已成為一種蔓延全世界的閱讀現象。"));
        myBookData.add(new BookContent("哈利波特(2)：消失的密室", "J.K羅琳", "『密室已經打開了。傳人的仇敵們，當心了……』\n" +
                "\n" +
                "　　這到底是什麼樣的預警？密室究竟在哪裡？\n" +
                "\n" +
                "　才一開學，魔法學校裡卻怪事連連：先是管理員飛七的貓，不知道為什麼竟然被『石化』！然後學校裡的學生，也是一個接一個慘遭毒手……難道這些慘劇都和密室的預警有關？而傳言中『毀滅麻種』的恐怖事件，是否正在秘密進行？為了解開這一連串駭人聽聞的謎團，哈利波特勢必得找到密室，但他也正一步步接近致命的危機....。　\n" +
                "\n" +
                "　　繼《哈利波特——神秘的魔法石》，J.K.羅琳再度展她說故事的長才，以《哈利波特——消失的密室》在全球掀起一股更強大的閱讀熱潮！無論是大人還是小孩，都因為《哈利波特》而有了共同的話題。《哈利波特》帶給讀者的，不僅是一個充滿想像力的魔法世界，還有人們原本深藏心底的夢想！"));
        myBookData.add(new BookContent("哈利波特(3)：阿茲卡班的逃犯", "J.K羅琳", "「佛地魔的僕人已被束縛了十二年之久，今晚午夜以前，他將幫助佛地魔東山再起……」\n" +
                "\n" +
                "　　哈利波特升上三年級了，占卜學教授卻對他發出可怕的預言，到底「佛地魔的僕人」指的是誰？\n" +
                "\n" +
                "　　暑假才剛要結束，阿茲卡班監獄就發生了空前的越獄事件！傳言逃犯天狼星‧布萊克就是十三年前出賣了詹姆和莉莉的人！現在他將矛頭指向哈利波特，因此連一向安全的霍格華茲學校，也進駐了魔法界最令人膽寒的獄卒「催狂魔」負責守護，校園裡一片風聲鶴唳！\n" +
                "\n" +
                "　　除了要防範天狼星‧布萊克的偷襲行動，更讓哈利波特覺得丟臉的是，他一碰到全身罩著斗篷、陰森恐部的「催狂魔」就會昏倒！同時寒意竄入他的胸膛、腦袋裡不斷出現莫名的尖叫聲，並吸去他所有快樂的感覺。哈利波特要如何對抗「催狂魔」的威脅，並趕在午夜時限到達之前，找出潛伏的佛地魔僕人，阻止一場大災難的發生呢？"));
        myBookData.add(new BookContent("哈利波特(4)：火盃的考驗", "J.K羅琳", "才一開學，霍格華茲魔法學校的師生們就為了即將舉行的『三巫鬥法大賽』而興奮不已，但是當『火盃』選出哈利波特成為第四位鬥士時，卻引起全校的不諒解。憑什麼才14歲的哈利波特跨越『17歲』的年齡限制，而將名字投入『火盃』中呢？\n" +
                "\n" +
                "百口莫辯的哈利波特，只好承受所有人的猜疑，努力去挑戰鬥法大賽的三項艱鉅任務。但是，『佛地魔的僕人』早已盯上了哈利波特，暗中虎視眈眈的準備獵取『仇人之血』……這場『三巫鬥法大賽』背後究竟隱藏了什麼駭人聽聞的詭計？哈利波特又是否能順利通過『火盃』的考驗呢？……"));
        myBookData.add(new BookContent("哈利波特(5)：鳳凰會的密令", "J.K羅琳", "（上）\n" +
                "\n" +
                "　　一個酷熱的暑假夜晚，催狂魔竟然出現攻擊達力和哈利，情急之下，哈利不得不打破未成年巫師使用魔法的限制規定，發出護法咒擊退了催狂魔，但卻也因此面臨被學校開除的危機…。另一方面，與魔法部分道揚鑣的鄧不利多，決定重新啟動多年前為對抗佛地魔所成立的地下組織──鳳凰會，秘密的進行多項任務……\n" +
                "（下）\n" +
                "\n" +
                "　　正當霍格華茲所有五年級學生都在參加最重要的『普等巫測』時，哈利卻恍恍惚惚的進入了詭譎的夢境。突然之間，哈利從桌上跌落地面，醒了過來，卻仍舊不停的叫喊著！他的傷疤著火了，在他四週，整個大廳也跟著爆發混亂…。就在這一片紛紛擾擾之際，鄧不利多被解除了校長職務，連麥教授和海格也走了，取而代之的是魔法部的勢力箝制。難道，分類帽的預言真的來臨了？"));
        myBookData.add(new BookContent("哈利波特(7)：死神的聖物", "J.K羅琳", "年滿十七歲的哈利即將失去母親的保護咒， \n" +
                "鳳凰會利用『七個波特』戰術來分散食死人的注意， \n" +
                "但真正的哈利還是被發現了！ \n" +
                "哈利是否能逃過『那個人』的魔掌？\n" +
                "\n" +
                "阿不思．博知維．巫服利．布萊恩．鄧不利多最後的遺囑 \n" +
                "將我的熄燈器送給榮恩．畢利亞．衛斯理，希望他在使用時想起我。\n" +
                "\n" +
                "　　我把我手頭的一本《吟遊詩人皮陀故事集》送給妙麗．珍．格蘭傑小姐，希望她覺得這本書既有趣又有啟發性。\n" +
                "\n" +
                "　　我送給哈利．詹姆．波特先生，他在霍格華茲參加的第一場魁地奇比賽中抓到的金探子，紀念靠著毅力與技巧獲得的回報。\n" +
                "\n" +
                "　　被魔法部長昆爵扣留的鄧不利多遺物，在他死後一個月，終於交到了哈利、妙麗和榮恩的手上。但是為什麼鄧不利多要留下這些東西給他們？這三樣東西可以幫助哈利摧毀佛地魔的分靈體嗎？而被扣留的第四樣遺物──高錐客．葛來分多的寶劍，又流落何方？\n" +
                "\n" +
                "　　這一切讓哈利感到困惑：難道去年他跟鄧不利多的多次長談中，他錯過了什麼？鄧不利多是否期待他應該會懂？\n" +
                "\n" +
                "　　但是，死去的校長再也不會給他答案了，這一次，他得自己去尋找……"));
        myBookData.add(new BookContent("哈利波特（8）被詛咒的孩子", "J.K羅琳", "十九年過去了，哈利波特從一個勇敢的男孩蛻變為三個孩子的父親。即使如此，他依然深陷於往事的惡夢中，他夢見父母，夢見犧牲的夥伴，思念和歉疚緊緊抓著他不放。當他的閃電疤痕隱隱作痛，哈利開始懷疑：「那個人」難道還沒有放過他？\n" +
                "\n" +
                "　　最讓哈利感到憂心的，是他與小兒子阿不思降至冰點的關係。如果可以的話，阿不思真不願意身為哈利波特的兒子，彷彿必須要成為另一則「傳奇」，才配得上他的父親。\n" +
                "\n" +
                "　　阿不思的困擾也許只有天蠍能懂，天蠍有個惡名昭彰的父親──跩哥馬份，魔法學校甚至流傳著謠言：天蠍是佛地魔之子！於是，兩個男孩成為無話不談的好友，他們都無法選擇，無法選擇自己的父親，無法選擇去過一個自由自在的人生。\n" +
                "\n" +
                "　　然而，他們卻在無意間發現了一個秘密：魔法部還留有一個時光器。阿不思和天蠍第一次為自己做了選擇，他們要回到過去，改變這個不完美的世界。但他們不知道，就像人類不可能達到完美，魔法也一樣……"));
    }
}
