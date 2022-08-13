package tfsapps.partyking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private NameTable nameTable = null;
    private TalkTable talkTable = null;
    public MyOpenHelper helper;        //DBアクセス
    private int db_isopen = 0;         //DB使用したか
    private int db_point = 0;          //DBポイント

    /*
    private AdView mAdView1;
    private AdRequest adRequest1;
    static private AdView mAdView2;
    static private AdRequest adRequest2;
    static private AdView mAdView3;
    static private AdRequest adRequest3;
    */
    //  広告
    private AdView mAdview;
    // リワード広告
    private RewardedVideoAd mRewardedVideoAd;
    // テストID
//    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";
    // テストID(APPは本物でOK)
//    private static final String APP_ID = "ca-app-pub-4924620089567925~3973011636";
    //本物
    private static final String AD_UNIT_ID = "ca-app-pub-4924620089567925/9979001916";
    //本物
    private static final String APP_ID = "ca-app-pub-4924620089567925~3973011636";

    String nowname = "";
    String namelist = "";
    String nowtalk = "";
    String talklist = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  広告
        mAdview = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

        // リワード広告
        MobileAds.initialize(this, APP_ID);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }
    /**
     リワード広告処理
     */
    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(AD_UNIT_ID,new AdRequest.Builder().build());
    }

    @Override
    public void onRewarded(RewardItem reward) {
        // Reward the user.
        int tmp = db_point;
        db_point = db_point + 10;
        //ユーザーレベルアップ
        Toast.makeText(this, "ポイントGET!：" + (tmp) + "  → " + (db_point), Toast.LENGTH_SHORT).show();
        AppDBUpdated();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
//        Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
//        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad err="+errorCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "報酬動画の準備完了!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
//        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
//        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
//        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }


    /* TIPS */
    public void onTips(View view) {
        AlertDialog.Builder guide = new AlertDialog.Builder(this);
        TextView vmessage = new TextView(this);
        int level = 0;
        String pop_message = "";
        String btn_yes = "";
        String btn_no = "";

        pop_message += "\n\n 動画を視聴してポイントをGETしますか？" +
                "\n\n（ポイントをGETするとあだ名が増えます）" +
                "\n　例）男性・・・しょうへい（大谷翔平）等" +
                "\n　　　女性・・・まいやん　（白石麻衣）等" +
                "\n 　現在のポイント「"+db_point+"」→「"+(db_point+10)+"」"+"\n \n\n\n";

        btn_yes += "視聴";
        btn_no += "中止";

        //メッセージ
        vmessage.setText(pop_message);
        vmessage.setBackgroundColor(Color.DKGRAY);
        vmessage.setTextColor(Color.WHITE);
        //タイトル
        guide.setTitle("TIPS");
        guide.setIcon(R.drawable.present);
        guide.setView(vmessage);

        guide.setPositiveButton(btn_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
                //test_make
//                db_point = db_point+10;
            }
        });
        guide.setNegativeButton(btn_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setContentView(R.layout.activity_main);
            }
        });

        guide.create();
        guide.show();
    }

    /* ニックネーム */
    public void onName(View view) {
        //サブ画面へ
//        if (mAdView1 != null)   mAdView1.pause();
        setScreenName();
    }
    /* 話題 */
    public void onTalk(View view) {
        //サブ画面へ
//        if (mAdView1 != null)   mAdView1.pause();
        setScreenTalk();
    }
    /*----------------------------------------------------------
        以下、イベント関連の処理	EVENT
    -----------------------------------------------------------*/
    @Override
    public void onStart() {
        super.onStart();

        //DBのロード
        /* データベース */
        helper = new MyOpenHelper(this);
        AppDBInitRoad();
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
        //  DB更新
        AppDBUpdated();
    }
    /*
    @Override
    public void onUserLeaveHint(){
        //ホームボタンが押された時や、他のアプリが起動した時に呼ばれる
        //戻るボタンが押された場合には呼ばれない
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                //戻るボタンが押された時の処理。
                return true;
        }
        return false;
    }
    */

    @Override
    public void onDestroy(){
        super.onDestroy();
        //  DB更新
        AppDBUpdated();
    }


    /*----------------------------------------------------------
        以下、サブ画面関連の処理	ニックネーム
    -----------------------------------------------------------*/
    private void setScreenName() {
        if (nameTable != null ) nameTable = null;
        nameTable = new NameTable(db_point);

        setContentView(R.layout.activity_name);
        /*
        if (mAdView2 == null) {
            mAdView2 = (AdView) findViewById(R.id.adView2);
            adRequest2 = new AdRequest.Builder().build();
            mAdView2.loadAd(adRequest2);
        }
        else
        {
            mAdView2.loadAd(adRequest2);
        }
         */
        NameDisp(-1);
    }
    /* ニックネーム表示処理 */
    public void NameDisp(int mode) {
        TextView text = (TextView) findViewById(R.id.HitName);
        if (nowname.isEmpty()) {
            text.setText("検索ボタンを押して下さい");
        }
        else
        {
            text.setText(nowname);
        }
        if (mode == 0)
        {
            text.setTextColor(Color.BLUE);
        }
        else if (mode == 1)
        {
            text.setTextColor(Color.RED);
        }
        else
        {
            text.setTextColor(Color.DKGRAY);
        }

        TextView list = (TextView) findViewById(R.id.name_list);
        list.setText(namelist);

    }
    /* 名前の検索 */
    public void NameSearch(int mode)
    {
        nowname = "";
        //男性テーブル検索
        if (mode == 0)
        {
            nowname = nameTable.MaleNameTableSearch();
            namelist += " 男性：";
            namelist += nowname;
            namelist += "\n";
        }
        //女性テーブル検索
        else
        {
            nowname = nameTable.FemaleNameTableSearch();
            namelist += " 女性：";
            namelist += nowname;
            namelist += "\n";
        }
        //検索終了後は、履歴にセット

    }
    /* 男性ネーム */
    public void onMaleName(View view) {
        //検索スタート
        NameSearch(0);
        NameDisp(0);
    }
    /* 女性ネーム */
    public void onFemaleName(View view) {
        //検索スタート
        NameSearch(1);
        NameDisp(1);
    }
    /* メニュー */
    public void onMenu1(View view) {
//        if (mAdView2 != null)   mAdView2.pause();
        setContentView(R.layout.activity_main);
//        if (mAdView1 != null)   mAdView1.loadAd(adRequest1);
    }
    /* 話題 */
    public void onTalkDisp(View view) {
//        if (mAdView2 != null)   mAdView2.pause();
        setScreenTalk();
    }

    /*----------------------------------------------------------
        以下、サブ画面関連の処理	話題
    -----------------------------------------------------------*/
    private void setScreenTalk() {
        if (talkTable != null ) talkTable = null;
        talkTable = new TalkTable();

        setContentView(R.layout.activity_talk);
        /*
        if (mAdView3 == null) {
            mAdView3 = (AdView) findViewById(R.id.adView3);
            adRequest3 = new AdRequest.Builder().build();
            mAdView3.loadAd(adRequest3);
        }
        else
        {
            mAdView3.loadAd(adRequest3);
        }
         */
        TalkDisp(-1);
    }
    /* 話題表示処理 */
    public void TalkDisp(int mode) {

        TextView text = (TextView) findViewById(R.id.HitTalk);
        if (nowtalk.isEmpty())
        {
            text.setText("検索ボタンを押して下さい");
        }
        else
        {
            text.setText(nowtalk);
        }
        if (mode == 0)
        {
            text.setTextColor(Color.rgb(233, 163, 38));
        }
        else
        {
            text.setTextColor(Color.DKGRAY);
        }


        TextView list = (TextView) findViewById(R.id.talk_list);
        list.setText(talklist);
    }
    /* 名前の検索 */
    public void TalkSearch(int mode)
    {
        nowtalk = talkTable.TalkTableSearch();
        talklist += nowtalk;
        talklist += "\n";
    }
    /* 話題 */
    public void onTalkStart(View view) {
        //検索スタート
        TalkSearch(0);
        TalkDisp(0);
    }
    /* メニュー */
    public void onMenu2(View view) {
//        if (mAdView3 != null)   mAdView3.pause();
        setContentView(R.layout.activity_main);
        //       if (mAdView1 != null)   mAdView1.loadAd(adRequest1);
    }
    /* 話題 */
    public void onNameDisp(View view) {
//        if (mAdView3 != null)   mAdView3.pause();
        setScreenName();
    }


    /* **************************************************
        DB初期ロードおよび設定
    ****************************************************/
    public void AppDBInitRoad() {
        SQLiteDatabase db = helper.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append(" isopen");
        sql.append(" ,point");
        sql.append(" FROM appinfo;");
        try {
            Cursor cursor = db.rawQuery(sql.toString(), null);
            //TextViewに表示
            StringBuilder text = new StringBuilder();
            if (cursor.moveToNext()) {
                db_isopen = cursor.getInt(0);
                db_point = cursor.getInt(1);
            }
        } finally {
            db.close();
        }

        db = helper.getWritableDatabase();
        if (db_isopen == 0) {
            long ret;
            /* 新規レコード追加 */
            ContentValues insertValues = new ContentValues();
            insertValues.put("isopen", 1);
            insertValues.put("point", 0);
            insertValues.put("data1", 0);
            insertValues.put("data2", 0);
            insertValues.put("data3", 0);
            insertValues.put("data4", 0);
            insertValues.put("data5", 0);
            insertValues.put("data6", 0);
            insertValues.put("data7", 0);
            insertValues.put("data8", 0);
            insertValues.put("data9", 0);
            insertValues.put("data10", 0);
            try {
                ret = db.insert("appinfo", null, insertValues);
            } finally {
                db.close();
            }
            db_isopen = 1;
            db_point = 0;
            /*
            if (ret == -1) {
                Toast.makeText(this, "DataBase Create.... ERROR", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "DataBase Create.... OK", Toast.LENGTH_SHORT).show();
            }
             */
        } else {
            /*
            Toast.makeText(this, "Data Loading...  interval:" + db_interval, Toast.LENGTH_SHORT).show();
             */
        }
    }

    /* **************************************************
        DB更新
    ****************************************************/
    public void AppDBUpdated() {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues insertValues = new ContentValues();
        insertValues.put("isopen", db_isopen);
        insertValues.put("point", db_point);
        int ret;
        try {
            ret = db.update("appinfo", insertValues, null, null);
        } finally {
            db.close();
        }
        /*
        if (ret == -1) {
            Toast.makeText(this, "Saving.... ERROR ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Saving.... OK "+ "op=0:"+db_isopen+" interval=1:"+db_interval+" brightness=2:"+db_brightness, Toast.LENGTH_SHORT).show();
        }
         */
    }
}