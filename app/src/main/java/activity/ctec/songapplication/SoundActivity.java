package activity.ctec.songapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.graphics.Color;

public class SoundActivity extends Activity implements Runnable
{
    private Button startButton;
    private Button stopButton;
    private Button pauseButton;
    private Button videoButton;
    private RelativeLayout backgroundLayout;
    private MediaPlayer soundPlayer;
    private SeekBar soundSeekBar;
    private Thread soundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        startButton = (Button) findViewById(R.id.playButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        backgroundLayout = (RelativeLayout) findViewById(R.id.backgroundLayout);
        videoButton = (Button) findViewById(R.id.videoButton);
        soundSeekBar = (SeekBar) findViewById(R.id.soundSeekBar);
        soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.poker2);

        setUpListeners();

        soundThread = new Thread(this);
        soundThread.start();
    }

    private void changeBackgroundColor()
    {
        int redColor;
        int greenColor;
        int blueColor;

        redColor = (int) (Math.random() * 256);
        greenColor = (int) (Math.random() * 256);
        blueColor = (int) (Math.random() * 256);

        backgroundLayout.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));


    }


    private void setUpListeners()
    {

        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                soundPlayer.start();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                soundPlayer.pause();
                changeBackgroundColor();
            }
        });


        stopButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View currentView)
            {
                soundPlayer.stop();
                soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.poker2);
            }
        });

        videoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View currentView)
            {
                Intent myIntent = new Intent(currentView.getContext(), videoActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });

        soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if (fromUser)
                {
                    soundPlayer.seekTo(progress);
                }
            }


        });
    }

    @Override
    public void run()
    {
        int currentPosition = 0;
        int soundTotal = soundPlayer.getDuration();
         soundSeekBar.setMax(soundTotal);

        while (soundPlayer != null && currentPosition < soundTotal)
        {
            try
            {
                Thread.sleep(300);
                currentPosition = soundPlayer.getCurrentPosition();
            }
            catch(InterruptedException soundException)
            {
                return;
            }
            catch(Exception otherException)
            {
                return;
            }
            soundSeekBar.setProgress(currentPosition);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sound, menu);
        return true;
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
