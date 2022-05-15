package tfsapps.partyking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by FURUKAWA on 2017/12/23.
 */

public class TalkTable {
    private List<MyString> Talk_List = new ArrayList<MyString>();

    final int MaxTalk = 40;
    private final Random rand = new Random(System.currentTimeMillis());

    static String[] talk = {
            //  1-10
            "小学校の給食で印象に残っているﾒﾆｭｰは？",
            "学生の修学旅行はどこに？",
            "体育祭(運動会)で印象に残っている事は？",
            "学園祭(文化祭)で印象に残っている事は？",
            "学校内で流行ったことは？",
            "学生の頃に流行ったアニメは？",
            "学生の頃に流行ったマンガは？",
            "学生の頃に苦手だった教科は？",
            "子供の頃に好きだった駄菓子は？",
            "子供の頃に好きだった食べ物は？",
            //  11-20
            "学生の頃部活は何をしてましたか？",
            "休日は何をしますか？",
            "趣味は何ですか？",
            "最近興味を持っていることは？",
            "兄弟姉妹は何人いますか？",
            "動物は飼っていますか？",
            "血液型は？",
            "好きな芸能人は？",
            "好きな映画は？",
            "今まで一番印象に残った旅行は？",
            //  21-30
            "好きなスイーツは？",
            "好きな音楽(ｱｰﾃｨｽﾄ)は？",
            "出身地は何処ですか？",
            "異性の気になる仕草は何ですか？",
            "何フェチですか？",
            "職業は何ですか？",
            "車は乗りますか？",
            "実家にはよく帰りますか？",
            "料理しますか？",
            "初対面でまず目のいく所は？",
            // 31-40
            "好きな本(作家)は？",
            "目玉焼きに使う調味料は？",
            "天ぷらに使う調味料は？",
            "とんかつに使う調味料は？",
            "好きなorよく行く電気屋さんは？",
            "好きなorよく行く洋服屋さんは？",
            "好きなorよく行くスーバーは？",
            "好きなorよく行く百貨店は？",
            "好きなorよく行くお出かけ先は？",
            "好きなorよく行くﾃｰﾏﾊﾟｰｸは？",
    };

    //コンストラクタ
    public TalkTable() {
        int i;

        for (i = 0; i < MaxTalk; i++) {
            MyString mess = new MyString(talk[i]);
            Talk_List.add(mess);
        }

    }
    public String TalkTableSearch()
    {
        int index1 = 0;
        int i;
        String hitname = " ";

        for(i=0;i<MaxTalk;i++)
        {
            index1 = rand.nextInt(MaxTalk);
            if (Talk_List.get(index1).isAlive == false)
            {
                continue;
            }
            else
            {
                Talk_List.get(index1).isAlive = false;
                break;
            }
        }
        if(i== MaxTalk)
        {
            hitname += "検索できませんでした";
        }
        else {
            hitname += Talk_List.get(index1).mystr;
        }
        return hitname;
    }

}
