package tfsapps.partyking;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity{
    private NameTable nameTable;
    private TalkTable talkTable;
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

    String nowname = "";
    String namelist = "";
    String nowtalk = "";
    String talklist = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTable = new NameTable();
        talkTable = new TalkTable();

        //  広告
        mAdview = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
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
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
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
    }


    /*----------------------------------------------------------
        以下、サブ画面関連の処理	ニックネーム
    -----------------------------------------------------------*/
    private void setScreenName() {
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

}