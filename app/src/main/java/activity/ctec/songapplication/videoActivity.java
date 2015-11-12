package activity.ctec.songapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.*;
import android.net.Uri;
import android.content.Intent;
import java.util.Random;

public class videoActivity extends Activity
{
    private VideoView videoView;
    private Button homeButton;
    private MediaController myVideoController;
    private Uri videoLocation;
    private int winner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = (VideoView) findViewById(R.id.videoView);
        homeButton = (Button) findViewById(R.id.homeButton);

        videoLocation = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.poker);
        myVideoController = new MediaController(this);
        //prepare the video
        setUpMedia();
        setUpListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
        return true;
    }

    private void setUpMedia()
    {
        videoView.setMediaController(myVideoController);
        videoView.setVideoURI(videoLocation);
    }

    private void setUpListeners()
    {
        homeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View currentView)
            {

                winner = new Random().nextInt((25 - 1) +1);

                if(winner == 25)
                {
                    
                }
                else
                {
                    Intent myIntent = new Intent(currentView.getContext(), SoundActivity.class);
                    startActivityForResult(myIntent, 0);
                }

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
