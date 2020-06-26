package andoid.example.mypiano;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ArrayList<Music> arrayList;
    private Custommusicadapter adapter;
    private ListView songList;

    InterstitialAd mInterstitialAd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        MobileAds.initialize(this,"ca-app-pub-8788357612169852~2767608576");

        songList = (ListView) findViewById(R.id.songList);
        arrayList = new ArrayList<>();
        arrayList.add(new Music("Friends theme", "", R.raw.friendstheme));
        arrayList.add(new Music("Game of thrones", "", R.raw.gameofthrones));
        arrayList.add(new Music("Harry Potter", "", R.raw.harrypotter));
        arrayList.add(new Music("Flower of forest", "", R.raw.flowersoftheforest));
        arrayList.add(new Music("Chopin Nocturne", "", R.raw.chopinnocturne));
        arrayList.add(new Music("Serenade", "", R.raw.serenadepiano));
        arrayList.add(new Music("Sherlock Holmes", "", R.raw.sherlocktheme));





        adapter = new Custommusicadapter(this, R.layout.custom_music_file, arrayList);
        songList.setAdapter(adapter);

        mInterstitialAd= new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8788357612169852/4293029962");
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);


    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }
}
