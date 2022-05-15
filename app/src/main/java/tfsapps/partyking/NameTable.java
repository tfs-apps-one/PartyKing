package tfsapps.partyking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by FURUKAWA on 2017/12/22.
 */


public class NameTable {

    private List<MyString> name1_maleList = new ArrayList<MyString>();
    private List<MyString> name2_maleList = new ArrayList<MyString>();
    private List<MyString> name1_femaleList = new ArrayList<MyString>();
    private List<MyString> name2_femaleList = new ArrayList<MyString>();

    final int MaxName1Male = 25;
    final int MaxName2Male = 25;
    final int MaxName1Female = 25;
    final int MaxName2Female = 25;

    private final Random rand = new Random(System.currentTimeMillis());

    static String[] name1_male = {
            //  スポーツ業界
            //  1-10
            "野球界の",
            "テニス界の",
            "相撲界の",
            "ゴルフ界の",
            "柔道界の",
            "体操界の",
            "社交ダンス界の",
            "お笑い界の",
            "歌舞伎界の",
            "ジャニーズ界の",
            //  11-20
            "ＩＴ界の",
            "政界の",
            "下町の",
            "永田町の",
            "都内の",
            "浪速の",
            "滋賀の",
            "報道界の",
            "サムライジャパンの",
            "ロック界の",
            //  21-30
            "ギャンブル界の",
            "ファンタジー界の",
            "ラーメン界の",
            "モデル界の",
            "リーマン界の"
    };
    static String[] name2_male = {
            //  1-10
            "イチロー",
            "キングカズ",
            "安倍さん（安倍晋三）",
            "松岡修造",
            "ハンカチ王子（斎藤佑樹）",
            "ハニカミ王子（石川遼）",
            "ウッチー（内田篤人）",
            "内村航平",
            "エアケイ（錦織圭）",
            "ゆづ（羽生結弦）",
            //  11-20
            "タモさん",
            "鶴瓶",
            "お兄ちゃん（若乃花）",
            "キムタク",
            "まっちゃん（松本人志）",
            "ゴジラ（松井秀喜）",
            "海老蔵",
            "ビートたけし",
            "いのっち（井ノ原快彦）",
            "ウッチャン（内村光良）",
            //  21-30
            "ルフィ（ワンピース）",
            "孫悟空（ドラゴンボール）",
            "のび太くん（どらえもん）",
            "パズー（天空の城ラピュタ）",
            "コナン君（名探偵コナン）"


            // 野球
            // 1-5
/*          "イチロー（野球）",
            "ゴジラ（野球）",
            "ダルビッシュ（野球）",
            "マー君（野球",
            "清宮幸太郎（野球）",
            //サッカー
            "キングカズ（サッカー）",
            "本田圭祐（サッカー）",
            "中田英寿（サッカー）",
            "香川真司（サッカー）",
            "長友佑都（サッカー）"

            "ハンカチ王子（野球選手）",
            "中田翔（野球選手）",
            "菊池雄星（野球選手）",
            "柳田悠岐（野球選手）",
            "筒香嘉智（野球選手）"*/
    };
    static String[] name1_female = {
            //  スポーツ業界
            //  1-10
            "フィギュア界の",
            "テニス界の",
            "レスリング界の",
            "ゴルフ界の",
            "バドミントン界の",
            "ヒップホップ界の",
            "バレーボール界の",
            "社交ダンス界の",
            "お笑い界の",
            "アイドル界の",
            //  11-20
            "ＩＴ界の",
            "政界の",
            "下町の",
            "永田町の",
            "都内の",
            "浪速の",
            "古都の",
            "報道界の",
            "なでしこジャパンの",
            "ロック界の",
            //  21-30
            "ギャンブル界の",
            "ファンタジー界の",
            "スイーツ界の",
            "モデル界の",
            "ＯＬ界の"
    };
    static String[] name2_female = {
            // フィギュア
            // 1-10
            "真央ちゃん（浅田真央）",
            "伊達公子",
            "霊長類最強（吉田沙保里）",
            "藍ちゃん（宮里藍）",
            "オグシオ（潮田玲子）",
            "愛ちゃん（福原愛）",
            "高梨沙羅",
            "Ｑちゃん（高橋尚子）",
            "ヤワラちゃん（谷亮子）",
            "サオリン（木村沙織）",
            //  11-20
            "しーちゃん（荒川静香）",
            "アッコ（和田アキ子）",
            "エビちゃん（蛯原友里）",
            "ともちん（板野友美）",
            "あやや（松浦亜弥）",
            "ミトちゃん（水卜麻美）",
            "ミキティ（藤本美貴）",
            "まゆゆ（渡辺麻友）",
            "かっすん（有村架純）",
            "ガッキー（新垣結衣）",
            //  21-30
            "しずかちゃん（どらえもん）",
            "ラムちゃん（うる星やつら）",
            "キキ（魔女の宅急便）",
            "アラレちゃん（DR.ｽﾗﾝﾌﾟ）",
            "サリー（魔法使いサリー）"
/*            "キム・ヨナ（フィギュア）",
            "荒川静香（フィギュア）",
            "宮原知子（フィギュア）",
            "本田真凛（フィギュア）",
            */
    };

    //コンストラクタ
    public NameTable() {
        int i;

        /* 男性　ニックネームリスト作成 */
        for (i = 0; i < MaxName1Male; i++) {
            MyString name = new MyString(name1_male[i]);
            name1_maleList.add(name);
        }
        for (i = 0; i < MaxName2Male; i++) {
            MyString name = new MyString(name2_male[i]);
            name2_maleList.add(name);
        }

        /* 女性　ニックネームリスト作成 */
        for (i = 0; i < MaxName1Female; i++) {
            MyString name = new MyString(name1_female[i]);
            name1_femaleList.add(name);
        }
        for (i = 0; i < MaxName2Female; i++) {
            MyString name = new MyString(name2_female[i]);
            name2_femaleList.add(name);
        }
    }

    //男性の検索
    public String MaleNameTableSearch()
    {
        int index1 = 0;
        int index2 = 0;
        int i;
        int j;
        String hitname = "";

        for(i=0;i<MaxName1Male;i++)
        {
            index1 = rand.nextInt(MaxName1Male);
            if (name1_maleList.get(index1).isAlive == false)
            {
                continue;
            }
            else
            {
                name1_maleList.get(index1).isAlive = false;
                break;
            }
        }

        for(j=0;j<MaxName2Male;j++)
        {
            index2 = rand.nextInt(MaxName2Male);
            if (name2_maleList.get(index2).isAlive == false)
            {
                continue;
            }
            else
            {
                name2_maleList.get(index2).isAlive = false;
                break;
            }
        }
        if(i== MaxName1Male || j==MaxName2Male)
        {
            hitname += "検索できませんでした";
        }
        else {
            hitname += name1_maleList.get(index1).mystr;
            hitname += name2_maleList.get(index2).mystr;
        }

        return hitname;
    }

    //女性の検索
    public String FemaleNameTableSearch()
    {
        int index1 = 0;
        int index2 = 0;
        int i;
        int j;
        String hitname = "";

        for(i=0;i<MaxName1Female;i++)
        {
            index1 = rand.nextInt(MaxName1Female);
            if (name1_femaleList.get(index1).isAlive == false)
            {
                continue;
            }
            else
            {
                name1_femaleList.get(index1).isAlive = false;
                break;
            }
        }

        for(j=0;j<MaxName2Female;j++)
        {
            index2 = rand.nextInt(MaxName2Female);
            if (name2_femaleList.get(index2).isAlive == false)
            {
                continue;
            }
            else
            {
                name2_femaleList.get(index2).isAlive = false;
                break;
            }
        }
        if(i== MaxName1Female || j== MaxName2Female)
        {
            hitname += "検索できませんでした";
        }
        else
        {
            hitname += name1_femaleList.get(index1).mystr;
            hitname += name2_femaleList.get(index2).mystr;
        }

        return hitname;
    }

}